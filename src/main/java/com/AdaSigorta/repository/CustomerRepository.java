package com.AdaSigorta.repository;

import com.AdaSigorta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {
    Optional<Customer> findByTcKimlikNo(String tcKimlikNo);
    List<Customer> findByName(String name);
}
