import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
// Imports Material 3
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatButtonModule, MatIconButton} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {MatDrawer, MatDrawerContainer, MatDrawerContent} from '@angular/material/sidenav';
import {MatList, MatListItem} from '@angular/material/list';
import {RouterLink, RouterOutlet} from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';
import {AuthService} from '../services/auth';

@Component({
  selector: 'app-admin-template',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule, // Pour la barre supérieure
    MatButtonModule,  // Pour les boutons Rose-Red
    MatIconModule,
    MatIconModule,
    MatIconButton,
    MatMenuTrigger,
    MatMenu,
    MatMenuItem,
    MatDrawerContainer,
    MatDrawer,
    MatListItem,
    MatList,
    MatDrawerContent,
    RouterOutlet,
    RouterLink,
// Pour les icônes
  ],
  templateUrl: './admin-template.html',
  styleUrl: './admin-template.scss'
})
export class AdminTemplate {
  authService = inject(AuthService);

}
