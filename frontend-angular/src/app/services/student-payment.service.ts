import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {toSignal} from '@angular/core/rxjs-interop';
import {Payment} from '../../models/payments.model';

@Injectable({
  providedIn: 'root',
})
export class StudentPaymentService {
  private http = inject(HttpClient);

  payments = toSignal(
    this.http.get<any[]>('http://localhost:8083/payments'), {initialValue: []}
  );
  students = toSignal(
    this.http.get<any[]>( 'http://localhost:8083/students'), {initialValue: []}
  );

  getPaymentsByStudent(code: string) {
    return toSignal(
      this.http.get<any[]>(
        `http://localhost:8083/students/${code}/payments`), {initialValue: []}
    );
  }

  addPayment(formData: FormData) {
    for (const [key, value] of formData.entries()) {
      console.log(key, value);
    }

    return this.http.post<Payment>(
      `http://localhost:8083/payments/create`,
      formData
    );
  }
}
