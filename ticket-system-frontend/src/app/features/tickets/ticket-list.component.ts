import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { TicketService } from '@core/services/ticket.service';
import { Ticket } from '@models/ticket.model'; // ajuste o alias se necessário

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [NgFor, NgIf, RouterModule],
  template: `
    <h2>📋 Lista de Tickets</h2>

    <div *ngIf="isLoading">🔄 Carregando tickets...</div>
    <div *ngIf="errorMessage" class="error">{{ errorMessage }}</div>

    <ul *ngIf="!isLoading && tickets.length">
      <li *ngFor="let ticket of tickets">
        📝 <strong>{{ ticket.title }}</strong> — <em>{{ ticket.status }}</em>
      </li>
    </ul>

    <div *ngIf="!isLoading && tickets.length === 0">
      ⚠️ Nenhum ticket encontrado.
    </div>

    <button (click)="irParaUsuarios()">👥 Ir para Usuários</button>
  `,
  styles: [`
    h2 {
      margin-bottom: 1rem;
    }
    ul {
      list-style: none;
      padding: 0;
    }
    li {
      margin-bottom: 0.5rem;
    }
    .error {
      color: red;
      font-weight: bold;
      margin-bottom: 1rem;
    }
    button {
      margin-top: 1rem;
      padding: 0.5rem 1rem;
      font-size: 1rem;
      cursor: pointer;
    }
  `]
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(
    private router: Router,
    private ticketService: TicketService
  ) {}

  ngOnInit(): void {
    // Chama o serviço com parâmetros de paginação e ordenação
    this.ticketService.getAll(0, 10, 'createdAt', 'desc').subscribe({
      next: (page) => {
        this.tickets = page.content ?? [];
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = '❌ Erro ao carregar tickets.';
        this.tickets = [];
        this.isLoading = false;
        console.error('Erro na requisição de tickets:', err);
      }
    });
  }

  irParaUsuarios(): void {
    this.router.navigate(['/users']);
  }
}
