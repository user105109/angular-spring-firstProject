import { Routes } from '@angular/router';
import {authGuard} from './guards/auth-guard';
import {authorizationGuard} from './guards/authorization-guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./login/login').then(m => m.Login)
  },
  {
    path: 'admin',
    loadComponent: () => import('./admin-template/admin-template').then(m => m.AdminTemplate), canActivate: [authGuard], children: [
      {
        path: 'profile',
        loadComponent: () => import('./profile/profile').then(m => m.Profile)
      },
      {
        path: 'home',
        loadComponent: () => import('./home/home').then(m => m.Home)
      },
      {
        path: 'dashboard',
        loadComponent: () => import('./dashboard/dashboard').then(m => m.Dashboard)
      },
      {
        path: 'load-payments',
        loadComponent: () => import('./load-payments/load-payments').then(m => m.LoadPayments), canActivate: [authorizationGuard], data: {roles: ['ADMIN']}
      },
      {
        path: 'load-students',
        loadComponent: () => import('./load-students/load-students').then(m => m.LoadStudents), canActivate: [authorizationGuard], data: {roles: ['ADMIN']}
      },
      {
        path: 'students',
        loadComponent: () => import('./students/students').then(m => m.Students)
      },
      {
        path: 'payments',
        loadComponent: () => import('./payments/payments').then(m => m.Payments)
      },
      {
        path: 'student-payments/:code',
        loadComponent: () => import('./student-payments/student-payments').then(m => m.StudentPayments)
      },
      {
        path: 'new-payment/:code',
        loadComponent: () => import('./new-payment/new-payment').then(m => m.NewPayment)
      }
    ]},
  // Optional: Add a wildcard redirect if a user hits an unmapped path
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  }
];
