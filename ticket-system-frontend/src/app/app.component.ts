import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { UsersTableComponent } from './features/users/components/users-table/users-table.component';
import { RouterModule } from '@angular/router';
import { TicketListComponent } from '@features/tickets/ticket-list.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, RouterModule, TicketListComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent { }


