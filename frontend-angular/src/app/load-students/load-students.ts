import { Component } from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatDivider} from '@angular/material/list';

@Component({
  selector: 'app-load-students',
  imports: [
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatDivider,
    MatCardHeader
  ],
  templateUrl: './load-students.html',
  styleUrl: './load-students.scss',
})
export class LoadStudents {}
