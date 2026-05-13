import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MenuService, MenuCategory, MenuItem } from '../../core/services/menu';
import { CartService } from '../../core/services/cart';

@Component({
  selector: 'app-menu',
  imports: [CommonModule],
  templateUrl: './menu.html',
  styleUrl: './menu.scss',
})
export class Menu implements OnInit {
  private menuService = inject(MenuService);
  private route = inject(ActivatedRoute);
  cart = inject(CartService);

  categories = signal<MenuCategory[]>([]);
  loading = signal(true);
  error = signal(false);
  addedIndex = signal<string | null>(null);
  addOnSelected = signal<Record<string, boolean>>({});

  ngOnInit() {
    this.menuService.getMenu().subscribe({
      next: (data) => {
        this.categories.set(data);
        this.loading.set(false);
        this.route.fragment.subscribe(fragment => {
          if (fragment) {
            setTimeout(() => {
              document.getElementById(fragment)?.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }, 100);
          }
        });
      },
      error: () => { this.error.set(true); this.loading.set(false); }
    });
  }

  toggleAddOn(key: string) {
    this.addOnSelected.update(s => ({ ...s, [key]: !s[key] }));
  }

  addToCart(item: MenuItem) {
    const key = String(item.id);
    const withAddOn = this.addOnSelected()[key];
    this.cart.addItem({
      menuItemId: item.id,
      itemName: item.name,
      unitPrice: item.price,
      quantity: 1,
      addOnId: withAddOn ? 1 : undefined,
      addOnName: withAddOn ? 'Ghee / Butter' : undefined,
      addOnPrice: withAddOn ? 1.00 : undefined
    });
    this.addedIndex.set(key);
    setTimeout(() => this.addedIndex.set(null), 1200);
  }
}
