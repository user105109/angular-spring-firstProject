package org.punpun.demo.initializer;

import org.punpun.demo.entities.Payment;
import org.punpun.demo.entities.PaymentStatus;
import org.punpun.demo.entities.PaymentType;
import org.punpun.demo.entities.Student;
import org.punpun.demo.repository.PaymentRepository;
import org.punpun.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Configuration
public class ApplicationDataInitializer {
    @Bean
    CommandLineRunner start(
            StudentRepository studentRepository,
            PaymentRepository paymentRepository
    ) {
        return args -> {
            List<Student> students = List.of(
                    Student.builder()
                            .id(UUID.randomUUID().
                                    toString()).
                            firstName("Zouhair").
                            lastName("Messoudi").
                            code("1234").
                            programId("GI")
                            .build(),
                    Student.builder()
                            .id(UUID.randomUUID().
                                    toString()).
                            firstName("Mouad").
                            lastName("Soumir").
                            code("123").
                            programId("Indus")
                            .build(),
                    Student.builder()
                            .id(UUID.randomUUID().
                                    toString()).
                            firstName("Hakim").
                            lastName("Safih").
                            code("12").
                            programId("GTR")
                            .build()
            );

            if (studentRepository.count() == 0) {
                studentRepository.saveAll(students);
            }

            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();

            List<Payment> payments = new ArrayList<>();

            for (Student student : students) {
                for (int i = 0; i < 11; i++) {

                    payments.add(Payment.builder()
                            .amount(1000 + random.nextInt(2000))
                            .paymentType(
                                    paymentTypes[random.nextInt(paymentTypes.length)]
                            )
                            .paymentStatus(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(student)
                            .build());
                }
            }

            paymentRepository.saveAll(payments);
        };
    };
}

