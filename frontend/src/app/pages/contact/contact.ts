import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ContactService } from '../../core/services/contact';

@Component({
  selector: 'app-contact',
  imports: [FormsModule],
  templateUrl: './contact.html',
  styleUrl: './contact.scss',
})
export class Contact {
  private contactService = inject(ContactService);

  form = { name: '', email: '', phone: '', message: '' };
  submitting = signal(false);
  success = signal(false);
  errorMsg = signal('');

  submit() {
    this.submitting.set(true);
    this.errorMsg.set('');
    this.contactService.submitInquiry(this.form).subscribe({
      next: () => {
        this.success.set(true);
        this.submitting.set(false);
        this.form = { name: '', email: '', phone: '', message: '' };
      },
      error: () => {
        this.errorMsg.set('Something went wrong. Please call us at (505) 677-4394.');
        this.submitting.set(false);
      }
    });
  }
}
