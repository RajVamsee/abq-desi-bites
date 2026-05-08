import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ContactRequest {
  name: string;
  email: string;
  phone?: string;
  message: string;
}

@Injectable({ providedIn: 'root' })
export class ContactService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/contact';

  submitInquiry(request: ContactRequest): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(this.apiUrl, request);
  }
}
