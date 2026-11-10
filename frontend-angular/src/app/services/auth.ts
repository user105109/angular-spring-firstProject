import { Injectable, inject } from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  public users: any = {
    admin: {password: '1234', roles: ['STUDENT', 'ADMIN']},
    user1: {password: '123', roles: ['STUDENT']}
  }

  router = inject(Router);

  username : any;
  isAuthenticated = false;
  roles : string[] = [];

  public login(username: string, password: string): boolean {
    this.username = username;
    this.isAuthenticated = true;
    this.roles = this.users[username]['roles'];
    return this.users[username]?.password === password;
  }

  public logout() {
    this.username = '';
    this.isAuthenticated = false;
    this.roles = [];
    this.router.navigateByUrl("/login");
  }
}
