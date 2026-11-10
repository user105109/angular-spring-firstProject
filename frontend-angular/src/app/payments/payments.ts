import {Component, effect, inject, viewChild} from '@angular/core';
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
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, MatSortHeader} from '@angular/material/sort';
import {StudentPaymentService} from '../services/student-payment.service';

@Component({
  selector: 'app-payments',
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
    MatPaginator,
    MatSort,
    MatSortHeader
  ],
  templateUrl: './payments.html',
  styleUrl: './payments.scss',
})
export class Payments {
  private paymentService = inject(StudentPaymentService);
  protected payments = this.paymentService.payments;

  public displayedColumns: string[] = ['id', 'date', 'amount', 'paymentType', 'paymentStatus', 'firstName'];
  public dataSource = new MatTableDataSource<any>();

  private paginator = viewChild(MatPaginator);
  private sort = viewChild(MatSort);

  constructor() {
    effect(() => {
      this.dataSource.data = this.payments();
    });

    // Sync UI controllers when DOM loads
    effect(() => {
      this.dataSource.paginator = this.paginator();
      this.dataSource.sort = this.sort();
    });
  }
}
