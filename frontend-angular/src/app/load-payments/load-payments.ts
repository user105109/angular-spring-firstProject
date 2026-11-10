import { Component } from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatDivider} from '@angular/material/list';

@Component({
  selector: 'app-load-payments',
  imports: [
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatDivider
  ],
  templateUrl: './load-payments.html',
  styleUrl: './load-payments.scss',
})
export class LoadPayments {}
