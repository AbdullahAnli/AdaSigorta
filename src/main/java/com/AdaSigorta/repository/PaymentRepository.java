package com.AdaSigorta.repository;

import com.AdaSigorta.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByPolicy_PolicyNo(Long policyNo);
}
