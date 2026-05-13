import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { CartService } from '../../core/services/cart';
import { OrderService } from '../../core/services/order';

declare var Stripe: any;

@Component({
  selector: 'app-checkout',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './checkout.html',
  styleUrl: './checkout.scss'
})
export class Checkout implements OnInit {
  cart = inject(CartService);
  orderService = inject(OrderService);
  router = inject(Router);

  step = signal<'form' | 'payment' | 'success'>('form');
  loading = signal(false);
  errorMsg = signal('');

  form = {
    customerName: '',
    customerEmail: '',
    customerPhone: '',
    pickupTime: '',
    specialInstructions: ''
  };

  private stripe: any;
  private elements: any;
  orderId = signal(0);
  private paymentIntentId: string = '';

  ngOnInit() {
    if (this.cart.items().length === 0) {
      this.router.navigate(['/menu']);
    }
    this.stripe = Stripe('pk_test_51TWOg4Azt6Eq5eXVQfUb40hIALVxcu85eg9WVmJMEmWyF9ZcnGj6k7UfGgnvXflYozyClvBhSHQ3gFW5PtZWCDdN00RUfiZfuQ');
  }

  itemTotal(item: any): number {
    let t = item.unitPrice * item.quantity;
    if (item.addOnPrice) t += item.addOnPrice * item.quantity;
    return t;
  }

  async submitForm() {
    if (!this.form.customerName || !this.form.customerEmail) {
      this.errorMsg.set('Please fill in your name and email.');
      return;
    }
    this.loading.set(true);
    this.errorMsg.set('');

    this.orderService.createOrder({
      ...this.form,
      items: this.cart.items()
    }).subscribe({
      next: async (res) => {
        this.orderId.set(res.orderId);
        this.paymentIntentId = res.clientSecret.split('_secret_')[0];
        this.step.set('payment');
        this.loading.set(false);

        setTimeout(() => {
          this.elements = this.stripe.elements({ clientSecret: res.clientSecret });
          const paymentElement = this.elements.create('payment');
          paymentElement.mount('#payment-element');
        }, 100);
      },
      error: () => {
        this.errorMsg.set('Could not create order. Please try again.');
        this.loading.set(false);
      }
    });
  }

  async pay() {
    this.loading.set(true);
    this.errorMsg.set('');

    const { error } = await this.stripe.confirmPayment({
      elements: this.elements,
      confirmParams: { return_url: window.location.origin + '/checkout' },
      redirect: 'if_required'
    });

    if (error) {
      this.errorMsg.set(error.message || 'Payment failed. Please try again.');
      this.loading.set(false);
      return;
    }

    this.orderService.confirmOrder(this.paymentIntentId).subscribe({
      next: () => {
        this.cart.clear();
        this.step.set('success');
        this.loading.set(false);
      },
      error: () => {
        this.cart.clear();
        this.step.set('success');
        this.loading.set(false);
      }
    });
  }
}
