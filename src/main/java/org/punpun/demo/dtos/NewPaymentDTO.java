package org.punpun.demo.dtos;

import lombok.Data;
import org.punpun.demo.entities.PaymentType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class NewPaymentDTO {
    private double amount;
    @DateTimeFormat(pattern = "d/M/yyyy")
    private LocalDate date;
    private PaymentType paymentType;
    private String studentCode;
}
