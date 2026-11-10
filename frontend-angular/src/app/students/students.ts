import {Component, effect, inject, viewChild} from '@angular/core';
import {Router} from '@angular/router';
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
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-students',
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
    MatSortHeader,
    MatButton
  ],
  templateUrl: './students.html',
  styleUrl: './students.scss',
})
export class Students {
  private router = inject(Router);
  private studentService = inject(StudentPaymentService);
  protected students = this.studentService.students;

  public displayedColumns: string[] = ['id', 'firstName', 'lastName', 'code', 'programId', 'photo', 'actions'];
  public dataSource = new MatTableDataSource<any>();

  private paginator = viewChild(MatPaginator);
  private sort = viewChild(MatSort);

  constructor() {
    effect(() => {
      this.dataSource.data = this.students();
    });

    // Sync UI controllers when DOM loads
    effect(() => {
      this.dataSource.paginator = this.paginator();
      this.dataSource.sort = this.sort();
    });
  }

  studentPayments(code: string) {
    return this.router.navigateByUrl(`/admin/student-payments/${code}`)
  }
}
