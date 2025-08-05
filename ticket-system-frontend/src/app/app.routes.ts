import { Routes } from '@angular/router';
import { TicketListComponent } from './features/tickets/ticket-list.component';
import { UsersTableComponent } from './features/users/components/users-table/users-table.component';


 // 🗺️ Definição das rotas da aplicação
 export const routes: Routes = [
   // Rota principal para a lista de tickets
   { path: 'tickets', component: TicketListComponent },

   // Redireciona a raiz ('') para '/tickets'
   { path: '', redirectTo: '/tickets', pathMatch: 'full' },

   // Redireciona qualquer rota não reconhecida para '/tickets'
   { path: '**', redirectTo: '/tickets' },
   { path: 'users', component: UsersTableComponent }

 ];
