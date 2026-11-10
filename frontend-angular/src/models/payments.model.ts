import {PaymentTypes} from '../enums/paymentTypes';
import {PaymentStatus} from '../enums/paymentStatus';
import {StudentModel} from './students.model';

export interface Payment {
  date: string,
  amount: string,
  paymentType: PaymentTypes,
  file: string,
  student: string
}
