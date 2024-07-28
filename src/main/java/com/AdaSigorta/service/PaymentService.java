package com.AdaSigorta.service;

import com.AdaSigorta.entity.Payment;
import com.AdaSigorta.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment){
        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long policyNo){
        return paymentRepository.findById(policyNo).orElse(null);
    }
    public Payment updatePayment(Long policyNo,Payment updatePayment){
        if (paymentRepository.existsById(policyNo)){
            updatePayment.setPolicyNo(policyNo);
            return paymentRepository.save(updatePayment);
        }
        return null;
    }

    public void deletePayment(Long policyNo){
        paymentRepository.deleteById(policyNo);
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
