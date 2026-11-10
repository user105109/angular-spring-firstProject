import {Component, effect, inject} from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatDivider} from '@angular/material/list';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell, MatHeaderCellDef, MatHeaderRow, MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable,
  MatTableDataSource
} from '@angular/material/table';
import {StudentPaymentService} from '../services/student-payment.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatButton} from '@angular/material/button';
import {MatToolbar} from '@angular/material/toolbar';

@Component({
  selector: 'app-student-payments',
  imports: [
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatDivider,
    MatTable,
    MatRowDef,
    MatRow,
    MatHeaderCell,
    MatCell,
    MatColumnDef,
    MatHeaderRow,
    MatCellDef,
    MatHeaderCellDef,
    MatHeaderRowDef,
    MatButton,
    MatToolbar,
  ],
  templateUrl: './student-payments.html',
  styleUrl: './student-payments.scss',
})
export class StudentPayments {
  private paymentService = inject(StudentPaymentService);
  private route = inject(ActivatedRoute);
  protected code = this.route.snapshot.paramMap.get('code');
  protected studentPayments = this.paymentService.getPaymentsByStudent(this.code!);
  public displayedColumns: string[] = ['id', 'date', 'amount', 'paymentType', 'paymentStatus', 'firstName'];
  public dataSource = new MatTableDataSource<any>();
  private router = inject(Router);

  constructor() {
    effect(() => {
      this.dataSource.data = this.studentPayments();
    });
  }

  AddNewPayment(code: string) {
    return this.router.navigateByUrl(`/admin/new-payment/${code}`);
  }
}
