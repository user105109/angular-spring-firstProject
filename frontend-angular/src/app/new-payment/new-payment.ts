import {Component, inject} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatDivider} from "@angular/material/list";
import {MatInputModule, MatLabel} from "@angular/material/input";
import {StudentPaymentService} from '../services/student-payment.service';
import {PaymentTypes} from '../../enums/paymentTypes';
import {PaymentStatus} from '../../enums/paymentStatus';
import {MatOption, MatSelect} from '@angular/material/select';
import {ActivatedRoute} from '@angular/router';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {firstValueFrom} from 'rxjs';

@Component({
  selector: 'app-new-payment',
  imports: [
    FormsModule,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatDivider,
    MatFormFieldModule,
    MatInputModule,
    MatLabel,
    ReactiveFormsModule,
    MatSelect,
    MatOption,
    MatDatepickerModule,
    MatIconButton,
    MatIcon,
    MatCardActions,
    MatButton,
  ],
  templateUrl: './new-payment.html',
  styleUrl: './new-payment.scss',
})
export class NewPayment {
  private newPaymentService = inject(StudentPaymentService);
  protected route = inject(ActivatedRoute);
  protected paymentTypes = Object.keys(PaymentTypes).filter(key => isNaN(Number(key)));
  private fb = inject(FormBuilder);
  private studentCode = this.route.snapshot.paramMap.get('code');

  paymentForm = this.fb.group({
    date: ['', [Validators.required]],
    amount: ['', [Validators.required]],
    paymentType: [PaymentTypes.CASH, [Validators.required]],
    studentCode: [this.studentCode],
    fileName: ['', [Validators.required]],
    fileSource: ['', [Validators.required]]
  })

  onFileSelected(event: any): void {
    if (event.target.files && event.target.files.length > 0) {
      let selectedFile = event.target.files[0];
      this.paymentForm.patchValue({
        fileSource: selectedFile,
        fileName: selectedFile.name
      });
      // this.pdfFileUrl = window.URL.createObjectURL(selectedFile);
    }
  }

  async newPayment() {
    const formData = new FormData();
    const date = new Date(this.paymentForm.value.date ?? '');
    const formattedDate = `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;

    formData.set('date', formattedDate);
    formData.set('amount', this.paymentForm.value.amount?.toString() ?? '');
    formData.set('paymentType', this.paymentForm.value.paymentType?.toString() ?? '');
    formData.set('file', this.paymentForm.value.fileSource ?? '');
    formData.set('studentCode', this.studentCode ?? '');

    try {
      const payment = await firstValueFrom(
        this.newPaymentService.addPayment(formData)
      );

      console.log('Payment created:', payment);
      alert('Payment created successfully');
    } catch (error) {
      console.error('Payment creation failed:', error);
      alert('Payment creation failed');
    }
  }
}
