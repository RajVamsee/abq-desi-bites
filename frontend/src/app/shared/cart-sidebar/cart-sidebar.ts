import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CartService } from '../../core/services/cart';

@Component({
  selector: 'app-cart-sidebar',
  imports: [CommonModule],
  templateUrl: './cart-sidebar.html',
  styleUrl: './cart-sidebar.scss'
})
export class CartSidebar {
  cart = inject(CartService);
  router = inject(Router);
  open = signal(false);

  toggle() { this.open.update(v => !v); }
  close() { this.open.set(false); }

  checkout() {
    this.close();
    this.router.navigate(['/checkout']);
  }

  itemTotal(item: any): number {
    let t = item.unitPrice * item.quantity;
    if (item.addOnPrice) t += item.addOnPrice * item.quantity;
    return t;
  }
}
