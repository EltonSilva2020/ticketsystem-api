import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ticket } from '../../features/users/models/ticket.model';

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private apiUrl = '/api/tickets'; // ✅ URL correta para funcionar com proxy

  constructor(private http: HttpClient) {}

  getAll(
    page = 0,
    size = 20,
    orderBy = '',
    sortDirection = ''
  ): Observable<PageResponse<Ticket>> {
    let sortParam = '';
    if (orderBy && sortDirection) {
      sortParam = `${orderBy},${sortDirection}`;
    }

    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sortParam);

    return this.http.get<PageResponse<Ticket>>(this.apiUrl, { params });
  }

  getById(id: string): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.apiUrl}/${id}`);
  }

  create(ticket: Ticket): Observable<Ticket> {
    return this.http.post<Ticket>(this.apiUrl, ticket);
  }

  update(id: string, ticket: Ticket): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/${id}`, ticket);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
