import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from './cart';

export interface OrderRequest {
  customerName: string;
  customerEmail: string;
  customerPhone: string;
  pickupTime: string;
  specialInstructions: string;
  items: CartItem[];
}

export interface OrderResponse {
  clientSecret: string;
  orderId: number;
  total: number;
}

@Injectable({ providedIn: 'root' })
export class OrderService {
  private http = inject(HttpClient);
  private base = 'https://abq-desi-bites-production.up.railway.app/api';

  createOrder(req: OrderRequest): Observable<OrderResponse> {
    return this.http.post<OrderResponse>(`${this.base}/orders`, req);
  }

  confirmOrder(paymentIntentId: string): Observable<void> {
    return this.http.post<void>(`${this.base}/orders/confirm`, { paymentIntentId });
  }
}
