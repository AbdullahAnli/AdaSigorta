package com.AdaSigorta.service;

import com.AdaSigorta.dto.PaymentRequest;
import com.AdaSigorta.entity.Payment;
import com.AdaSigorta.entity.Policy;
import com.AdaSigorta.repository.PaymentRepository;
import com.AdaSigorta.repository.PolicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyService policyService;

    public boolean processPayment(PaymentRequest paymentRequest) {
        return true;
    }


    public Optional<Payment> getPayment(Long policyNo) {
        return paymentRepository.findByPolicy_PolicyNo(policyNo);
    }





    public Payment recordPayment(PaymentRequest paymentRequest) {
        Policy policy = policyService.getPolicy(paymentRequest.getPolicyNo());
        if (policy == null) {
            throw new RuntimeException("Policy not found for policyNo: " + paymentRequest.getPolicyNo());
        }

        Payment payment = new Payment();
        payment.setPolicyNo(policy.getPolicyNo());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentDate(LocalDateTime.now());

        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setCardHolderName(paymentRequest.getCardHolderName());
        payment.setExpirationDate(paymentRequest.getExpiryDate());
        payment.setCvv(paymentRequest.getCvv());

        payment.setPolicy(policy);

        Payment savedPayment = paymentRepository.save(payment);



        return savedPayment;
    }


    public List<Payment>searchPayment(Double minAmount, Double maxAmount, LocalDate startDate,
                                      LocalDate endDate){
        List<Payment>allPayments=paymentRepository.findAll();
        return allPayments.stream()
                .filter(p->minAmount==null||p.getAmount()>=minAmount)
                .filter(p->maxAmount==null|| p.getAmount()<=maxAmount)
                .filter(p->startDate==null|| !p.getPaymentDate().toLocalDate().isBefore(startDate))
                .filter(p->endDate==null|| !p.getPaymentDate().toLocalDate().isAfter(endDate))
                .collect(Collectors.toList());

    }
}
