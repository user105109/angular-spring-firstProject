import { Component, inject } from '@angular/core';
import {MatCard, MatCardActions, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatDivider} from '@angular/material/list';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatDivider,
    MatFormField,
    MatInput,
    MatLabel,
    MatCardActions,
    MatButton,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  authService = inject(AuthService);
  router = inject(Router);

  login() {
    let username = this.loginForm.value.username ?? "";
    let password = this.loginForm.value.password ?? "";
    if (this.authService.login(username, password)) {
      this.router.navigateByUrl("/admin/home");
    }
  }
}
//
