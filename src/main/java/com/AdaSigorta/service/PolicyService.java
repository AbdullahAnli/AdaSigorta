package com.AdaSigorta.service;


import com.AdaSigorta.entity.Customer;
import com.AdaSigorta.entity.Policy;
import com.AdaSigorta.entity.Vehicle;
import com.AdaSigorta.enums.PolicyStatus;
import com.AdaSigorta.repository.CustomerRepository;
import com.AdaSigorta.repository.PolicyRepository;
import com.AdaSigorta.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PolicyService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public Policy createPolicy( Policy policy) {
        policy.setPolicyNo(generatePolicyNo());
        policy.setCreatedAt(LocalDateTime.now());
        policy.setStatus(PolicyStatus.TEKLIF);
        return policyRepository.save(policy);
    }

    public Policy savePolicy(Policy policy){
        return policyRepository.save(policy);
    }

    public Policy getPolicy(Long policyNo){
        return policyRepository.findByPolicyNo(policyNo);
    }
    public List<Policy>getAllPolicies() {
        return policyRepository.findAll();
    }
    public Policy updatePolicy(Long policyNo,Policy updatedPolicy){
        Policy existingPolicy=policyRepository.findByPolicyNo(policyNo);
        if (existingPolicy!=null){

            existingPolicy.setBranchCode(updatedPolicy.getBranchCode());
            existingPolicy.setPrim(updatedPolicy.getPrim());
            existingPolicy.setStartDate(updatedPolicy.getStartDate());
            existingPolicy.setEndDate(updatedPolicy.getEndDate());
            return policyRepository.save(existingPolicy);
        }
        return null;
    }
    public boolean deletePolicy(Long policyNo) {
        Policy policy = policyRepository.findByPolicyNo(policyNo);
        if (policy != null) {
            policyRepository.delete(policy);
            return true;
        }
        return false;
    }
    public Policy apprvePolicy(Long policyNo){
        Policy policy=policyRepository.findByPolicyNo(policyNo);
        if (policy!=null){
            policy.setStatus(PolicyStatus.KABUL);
            policy.setApprovedAt(LocalDateTime.now());
            return policyRepository.save(policy);
        }
        return null;
    }
    public List<Policy>getPoliciesByCustomer(Long customerId){
        return policyRepository.findByCustomerId(customerId);
    }



    private Long generatePolicyNo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return Long.parseLong(sb.toString());
    }

    public List<Policy> getPoliciesByCustomer(Customer customer) {
        return policyRepository.findByCustomer(customer);
    }

}
