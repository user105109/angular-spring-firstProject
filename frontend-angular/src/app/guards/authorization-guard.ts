import {CanActivateFn, Router} from '@angular/router';
import {AuthService} from '../services/auth';
import {inject} from '@angular/core';

export const authorizationGuard: CanActivateFn =
  (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (!authService.isAuthenticated) {
    return router.parseUrl("/login");
  }

  const requiredRoles = route.data['roles'] as string[];
  const userRoles = authService.roles;
  const hasRole = userRoles.some(role => requiredRoles.includes(role))

    if (hasRole) {
      return true;
  }

    return router.parseUrl("/home");
};
