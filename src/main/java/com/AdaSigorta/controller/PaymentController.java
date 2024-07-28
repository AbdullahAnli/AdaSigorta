package com.AdaSigorta.controller;

import com.AdaSigorta.entity.Payment;
import com.AdaSigorta.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment>savePayment(@RequestBody Payment payment){
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }

    @GetMapping("/{policyNo}")
    public ResponseEntity<Payment>getPayment(@PathVariable Long policyNo){
        Payment payment=paymentService.getPayment(policyNo);
        if (payment!=null){
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{policyNo}")
    public ResponseEntity<Payment>updatePayment(@PathVariable Long policyNo,@RequestBody Payment payment){
        Payment updatePayment = paymentService.updatePayment(policyNo, payment);
        if (updatePayment != null){
            return ResponseEntity.ok(updatePayment);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{policyNo}")
    public ResponseEntity<Void>deletePayment(@PathVariable Long policyNo){
        paymentService.deletePayment(policyNo);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Payment>> searchPaymentInfo(
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(paymentService.searchPayment(minAmount, maxAmount, startDate, endDate));
    }


}
