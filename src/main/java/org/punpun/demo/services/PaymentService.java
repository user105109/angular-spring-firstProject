package org.punpun.demo.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.punpun.demo.dtos.NewPaymentDTO;
import org.punpun.demo.entities.Payment;
import org.punpun.demo.entities.PaymentStatus;
import org.punpun.demo.entities.PaymentType;
import org.punpun.demo.entities.Student;
import org.punpun.demo.repository.PaymentRepository;
import org.punpun.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public Payment updatePaymentStatus(Long id, PaymentStatus paymentStatus) {
        Payment payment = paymentRepository.findById(id).get();
        payment.setPaymentStatus(paymentStatus);
        return paymentRepository.save(payment);
    }

    public Payment addPayment(NewPaymentDTO newPaymentDTO, MultipartFile file) throws IOException {
        Path folderPath = Path.of(
                System.getProperty("user.home"),
                "inset",
                "data",
                "payment"
        );

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString() + ".pdf";
        Path filePath = folderPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        Student student = studentRepository.findByCode(newPaymentDTO.getStudentCode());
        Payment payment = Payment.builder()
                .id(null)
                .date(newPaymentDTO.getDate())
                .amount(newPaymentDTO.getAmount())
                .paymentType(newPaymentDTO.getPaymentType())
                .paymentStatus(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .student(student)
                .build();

        return paymentRepository.save(payment);
    }

    public byte[] getPaymentFile(Long id) throws IOException {
        Payment payment = paymentRepository.findById(id).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
