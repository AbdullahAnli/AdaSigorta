package com.AdaSigorta.repository;

import com.AdaSigorta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer , Long> {
    Optional<Customer> findByTcKimlikNo(String tcKimlikNo);
    List<Customer> findByName(String name);
}
