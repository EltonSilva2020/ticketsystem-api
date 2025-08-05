import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '@models/user.model';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'https://api.exemplo.com/users';

  constructor(private http: HttpClient) {}

  // 🔍 Buscar todos os usuários
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  // 🔍 Buscar usuário por ID
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  // ➕ Criar um novo usuário
  create(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  // ✏️ Atualizar um usuário existente
  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, user);
  }

  // ❌ Deletar um usuário
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}


