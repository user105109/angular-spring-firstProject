import { Component } from '@angular/core';
import {MatDivider} from '@angular/material/list';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';

@Component({
  selector: 'app-profile',
  imports: [
    MatDivider,
    MatCardContent,
    MatCardTitle,
    MatCard,
    MatCardHeader
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.scss',
})
export class Profile {}
