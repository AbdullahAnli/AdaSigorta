package com.AdaSigorta.controller;

import com.AdaSigorta.dto.PaymentDTO;
import com.AdaSigorta.dto.PaymentRequest;
import com.AdaSigorta.entity.Payment;
import com.AdaSigorta.entity.Policy;
import com.AdaSigorta.enums.PolicyStatus;
import com.AdaSigorta.service.PaymentService;
import com.AdaSigorta.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PolicyService policyService;
    @Autowired
    private PaymentService paymentService;
    @PostMapping
    public ResponseEntity<String> pay(@RequestBody PaymentRequest paymentRequest) {
        try {
            boolean paymentSuccessful = paymentService.processPayment(paymentRequest);

            if (paymentSuccessful) {
                Payment payment = paymentService.recordPayment(paymentRequest);

                Policy policy = policyService.getPolicy(paymentRequest.getPolicyNo());
                if (policy != null && policy.getStatus() == PolicyStatus.TEKLIF) {
                    policy.setStatus(PolicyStatus.KABUL);
                    LocalDateTime now = LocalDateTime.now();
                    policy.setApprovedAt(now);
                    policy.setStartDate(now);
                    policy.setEndDate(now.plusYears(1));
                    policyService.savePolicy(policy);

                    return ResponseEntity.ok("Ödeme başarılı. Kayıt ID: " + payment.getId() + " PolicyNo: " + payment.getPolicyNo() + " Poliçe güncellendi.");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Geçersiz poliçe durumu.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Ödeme başarısız.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + e.getMessage());
        }
    }


    @GetMapping("/{policyNo}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long policyNo) {
        Optional<Payment> payment = paymentService.getPayment(policyNo);
        return payment.map(p -> {
            PaymentDTO dto = new PaymentDTO();
            dto.setId(p.getId());
            dto.setPolicyNo(p.getPolicyNo());
            dto.setAmount(p.getAmount());
            dto.setPaymentDate(p.getPaymentDate());
            dto.setCardNumber(p.getCardNumber());
            dto.setCardHolderName(p.getCardHolderName());
            dto.setExpirationDate(p.getExpirationDate());
            dto.setCvv(p.getCvv());
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public ResponseEntity<List<Payment>> searchPayments(
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        List<Payment> payments = paymentService.searchPayment(minAmount, maxAmount, startDate, endDate);
        return ResponseEntity.ok(payments);
    }
}
