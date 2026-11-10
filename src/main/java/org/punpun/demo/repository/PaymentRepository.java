package org.punpun.demo.repository;

import org.punpun.demo.entities.Payment;
import org.punpun.demo.entities.PaymentStatus;
import org.punpun.demo.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentCode(String code);

    List<Payment> findByPaymentStatus(PaymentStatus status);

    List<Payment> findByPaymentType(PaymentType type);
}
