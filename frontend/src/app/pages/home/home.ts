import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  highlights = [
    { icon: '🍛', title: 'Authentic Recipes', desc: 'Traditional South Indian & Hyderabadi flavors made from scratch daily.' },
    { icon: '🚗', title: 'DoorDash & Pickup', desc: 'Order online for delivery or swing by to pick up your favorites.' },
    { icon: '⭐', title: 'Weekend Specials', desc: 'Exclusive Biryani, Momos and more every Friday, Saturday & Sunday.' },
    { icon: '🌶️', title: 'Made Fresh Daily', desc: 'Every dish prepared fresh — no shortcuts, just pure flavor.' },
  ];
}
