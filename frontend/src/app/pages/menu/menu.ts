import { Component, inject, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MenuService, MenuCategory } from '../../core/services/menu';

@Component({
  selector: 'app-menu',
  imports: [CommonModule],
  templateUrl: './menu.html',
  styleUrl: './menu.scss',
})
export class Menu implements OnInit {
  private menuService = inject(MenuService);
  private route = inject(ActivatedRoute);

  categories = signal<MenuCategory[]>([]);
  loading = signal(true);
  error = signal(false);

  ngOnInit() {
    this.menuService.getMenu().subscribe({
      next: (data) => {
        this.categories.set(data);
        this.loading.set(false);
        // After data renders, scroll to fragment if present
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
}
