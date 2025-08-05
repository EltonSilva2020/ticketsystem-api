import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { routes } from './routes/users-routing.module';
import { HttpClientModule } from '@angular/common/http';


// Componentes standalone
import { UsersTableComponent } from './components/users-table/users-table.component';
import { UserFormComponent } from './components/user-form/user-form.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    UsersTableComponent,
    HttpClientModule,
    UserFormComponent
  ],
  declarations: [

  ]
})
export class UsersModule {}
