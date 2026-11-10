import { Component } from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatDivider} from '@angular/material/list';

@Component({
  selector: 'app-home',
  imports: [
    MatCard,
    MatCardTitle,
    MatCardHeader,
    MatDivider,
    MatCardContent
  ],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {}
