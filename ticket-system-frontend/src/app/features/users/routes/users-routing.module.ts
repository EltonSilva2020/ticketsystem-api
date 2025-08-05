import { Routes } from '@angular/router';

// 🧑‍💼 Componentes de usuários
import { UsersTableComponent } from '../components/users-table/users-table.component';
import { UserFormComponent } from '../components/user-form/user-form.component';

// 🎫 Componentes de tickets
import { TicketListComponent } from '@features/tickets/ticket-list.component';

// ❌ (Opcional) Componente de página não encontrada
// import { NotFoundComponent } from '../components/not-found/not-found.component';

export const routes: Routes = [
  // 🔁 Redirecionamento padrão para 'users'
  { path: '', redirectTo: 'users', pathMatch: 'full' },

  // 🧑‍💼 Rotas de gerenciamento de usuários
  { path: 'users', component: UsersTableComponent },
  { path: 'users/create', component: UserFormComponent },
  { path: 'users/edit/:id', component: UserFormComponent },

  // 🎫 Rotas de gerenciamento de tickets
  { path: 'tickets', component: TicketListComponent },

  // ❌ Rota de fallback para URLs inválidas
  // Se quiser um componente de erro, descomente a linha abaixo e crie o NotFoundComponent
  // { path: '**', component: NotFoundComponent }

  // Ou redirecione para 'users' como fallback simples
  { path: '**', redirectTo: 'users' }
];
