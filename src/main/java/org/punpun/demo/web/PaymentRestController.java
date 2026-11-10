package org.punpun.demo.web;

import org.punpun.demo.dtos.NewPaymentDTO;
import org.punpun.demo.entities.Payment;
import org.punpun.demo.entities.PaymentStatus;
import org.punpun.demo.entities.PaymentType;
import org.punpun.demo.entities.Student;
import lombok.RequiredArgsConstructor;
import org.punpun.demo.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.punpun.demo.repository.PaymentRepository;
import org.punpun.demo.repository.StudentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PaymentRestController {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final PaymentService paymentService;

    @GetMapping(path = "/payments")
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/payments/{id}")
    public Payment findPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> findPaymentByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/status")
    public List<Payment> findPaymentByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    @GetMapping(path = "/payments/types")
    public List<Payment> findPaymentByType(@RequestParam PaymentType paymentType) {
        return paymentRepository.findByPaymentType(paymentType);
    }

    @PutMapping(path = "/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus paymentStatus, @PathVariable Long id) {
        return paymentService.updatePaymentStatus(id, paymentStatus);
    }

    @PostMapping(path = "/payments/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment addPayment(@ModelAttribute NewPaymentDTO newPaymentDTO,
                              @RequestParam("file") MultipartFile file) throws IOException {
        return paymentService.addPayment(newPaymentDTO, file);
    }

    @GetMapping(path = "/payments/files/{id}")
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {
        return paymentService.getPaymentFile(id);
    }

    @GetMapping(path = "/students")
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/code/{code}")
    public Student findStudentByCode(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/students/programs/{programId}")
    public List<Student> findStudentByProgramId(@PathVariable String programId) {
        return studentRepository.findByProgramId(programId);
    }
}
