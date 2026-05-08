import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MenuItem {
  id: number;
  name: string;
  description: string;
  price: number;
  isAvailable: boolean;
  isWeekend: boolean;
}

export interface MenuCategory {
  id: number;
  name: string;
  description: string;
  items: MenuItem[];
}

@Injectable({ providedIn: 'root' })
export class MenuService {
  private http = inject(HttpClient);
  private apiUrl = 'https://abq-desi-bites-production.up.railway.app/api/menu';

  getMenu(): Observable<MenuCategory[]> {
    return this.http.get<MenuCategory[]>(this.apiUrl);
  }
}
