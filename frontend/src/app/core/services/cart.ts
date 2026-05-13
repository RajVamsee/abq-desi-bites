import { Injectable, computed, signal } from '@angular/core';

export interface CartItem {
  menuItemId: number;
  itemName: string;
  unitPrice: number;
  quantity: number;
  addOnId?: number;
  addOnName?: string;
  addOnPrice?: number;
}

@Injectable({ providedIn: 'root' })
export class CartService {
  items = signal<CartItem[]>([]);

  count = computed(() => this.items().reduce((sum, i) => sum + i.quantity, 0));

  subtotal = computed(() =>
    this.items().reduce((sum, i) => {
      let t = i.unitPrice * i.quantity;
      if (i.addOnPrice) t += i.addOnPrice * i.quantity;
      return sum + t;
    }, 0)
  );

  addItem(item: CartItem) {
    const idx = this.items().findIndex(
      i => i.menuItemId === item.menuItemId && i.addOnId === item.addOnId
    );
    if (idx >= 0) {
      this.items.update(items =>
        items.map((it, i) => i === idx ? { ...it, quantity: it.quantity + 1 } : it)
      );
    } else {
      this.items.update(items => [...items, { ...item, quantity: 1 }]);
    }
  }

  removeItem(index: number) {
    this.items.update(items => items.filter((_, i) => i !== index));
  }

  updateQuantity(index: number, qty: number) {
    if (qty <= 0) { this.removeItem(index); return; }
    this.items.update(items =>
      items.map((it, i) => i === index ? { ...it, quantity: qty } : it)
    );
  }

  clear() { this.items.set([]); }
}
