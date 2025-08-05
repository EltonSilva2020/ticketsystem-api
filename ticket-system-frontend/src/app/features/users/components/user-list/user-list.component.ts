import { ChangeDetectorRef } from '@angular/core';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '@services/user.service';
import { User } from '@models/user.model';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '@shared/components/confirm-dialog/confirm-dialog.component';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-user-list',
  standalone: true,
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDialogModule,
    MatChipsModule,
    MatTooltipModule,
    FormsModule
  ]
})
export class UserListComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['name', 'email', 'role', 'actions'];
  dataSource = new MatTableDataSource<User>();
  allUsers: User[] = [];
  users: User[] = [];
  selectedRole = '';
  loading = true;

  constructor(private userService: UserService, private router: Router, private dialog: MatDialog,  private cdRef: ChangeDetectorRef) {}

    ngOnInit(): void {
      this.loading = true;

      this.userService.getUsers().subscribe({
        next: users => {
          this.allUsers = users;
          this.users = users;
          this.dataSource.data = [...users];
          this.loading = false;
        },
        error: err => {
          console.error('Erro ao carregar usuários:', err);
          this.loading = false;
        }
      });

      this.dataSource.filterPredicate = (user: User, filter: string) =>
        user.name.toLowerCase().includes(filter) ||
        user.email.toLowerCase().includes(filter) ||
        user.role.toLowerCase().includes(filter);
    }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.cdRef.detectChanges();
  }

  applyFilter(event: Event): void {
    const value = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = value;
  }

  filterUsers(): void {
    this.users = this.selectedRole
      ? this.allUsers.filter(user => user.role === this.selectedRole)
      : [...this.allUsers];
    this.dataSource.data = this.users;
  }

  getRoleColor(role: string): 'primary' | 'accent' | 'warn' {
    switch (role) {
      case 'Admin': return 'warn';
      case 'Editor': return 'accent';
      default: return 'primary';
    }
  }

  getRoleTooltip(role: string): string {
    switch (role) {
      case 'Admin': return 'Pode gerenciar tudo';
      case 'Editor': return 'Pode editar conteúdos';
      default: return 'Apenas visualização';
    }
  }

  edit(user: User): void {
    console.log('Editando usuário:', user);
  }

  editUser(id: number): void {
    this.router.navigate(['/users/edit', id]);
  }

  createUser(): void {
    this.router.navigate(['/users/create']);
  }

  deleteUser(user: User): void {
    console.log('Deletando usuário:', user);
  }

  delete(user: User): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'confirm') {
        console.log('Usuário deletado:', user);
      }
    });
  }
exportCSV(): void {
  const header = ['Nome', 'Email', 'Papel'];
  const rows = this.users.map(u => [u.name, u.email, u.role]);

  let csv = header.join(',') + '\n';
  csv += rows.map(r => r.join(',')).join('\n');

  const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
  const link = document.createElement('a');
  link.href = URL.createObjectURL(blob);
  link.download = 'usuarios.csv';
  link.click();
}
applyGlobalFilter(event: Event): void {
  const value = (event.target as HTMLInputElement).value.trim().toLowerCase();
  this.dataSource.filterPredicate = (user: User, filter: string) =>
    user.name.toLowerCase().includes(filter) ||
    user.email.toLowerCase().includes(filter) ||
    user.role.toLowerCase().includes(filter);
  this.dataSource.filter = value;
}

}



