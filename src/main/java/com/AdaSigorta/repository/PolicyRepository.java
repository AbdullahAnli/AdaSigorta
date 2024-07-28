package com.AdaSigorta.repository;

import com.AdaSigorta.entity.Customer;
import com.AdaSigorta.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy , Long> {
    Policy findByPolicyNo(Long policyNo);
    List<Policy>findByCustomerId(Long customerId);
    List<Policy> findByCustomer(Customer customer);
    List<Policy> findByCustomer_Id(Long customerId);
    List<Policy> findByVehicle_Id(Long vehicleId);

}
