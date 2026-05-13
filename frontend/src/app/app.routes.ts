import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Menu } from './pages/menu/menu';
import { About } from './pages/about/about';
import { Contact } from './pages/contact/contact';
import { Checkout } from './pages/checkout/checkout';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'menu', component: Menu },
  { path: 'about', component: About },
  { path: 'contact', component: Contact },
  { path: 'checkout', component: Checkout },
  { path: '**', redirectTo: '' }
];
