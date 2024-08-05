package com.AdaSigorta.controller;

import com.AdaSigorta.dto.PolicyRequest;
import com.AdaSigorta.entity.Customer;
import com.AdaSigorta.entity.Policy;
import com.AdaSigorta.repository.PolicyRepository;
import com.AdaSigorta.service.CustomerService;
import com.AdaSigorta.service.PolicyService;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        try {
            Policy createdPolicy = policyService.createPolicy(policy);
            return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPolicy() {
        try {
            List<Policy> policies = policyService.getAllPolicies();
            if (policies == null || policies.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(policies);
        } catch (Exception e) {
            // Loglama yapılabilir
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching policies");
        }
    }

    @GetMapping("/{policyNo}")
    public ResponseEntity<Policy>getPolicy(@PathVariable Long policyNo){

        Policy policy=policyService.getPolicy(policyNo);
        if (policy != null) {
            return ResponseEntity.ok(policy);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{policyNo}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long policyNo, @RequestBody Policy policy) {
        Policy updatedPolicy = policyService.updatePolicy(policyNo, policy);
        if (updatedPolicy != null) {
            return ResponseEntity.ok(updatedPolicy);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{policy}")
    public ResponseEntity<Void>deletePolicy(@PathVariable Long policyNo){
        if (policyService.deletePolicy(policyNo)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/approve/{policyNo}")
    public ResponseEntity<Policy>approvePolicy(@PathVariable Long policyNo){
        Policy apprvePolicy = policyService.apprvePolicy(policyNo);
        if (apprvePolicy!=null ){
            return ResponseEntity.ok(apprvePolicy);
        }
        return ResponseEntity.notFound().build();

    }



    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> getPoliciesByCustomer(@PathVariable Long customerId) {
        Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);

        if (optionalCustomer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = optionalCustomer.get();
        List<Policy> policies = policyService.getPoliciesByCustomer(customer);

        List<Map<String, Object>> response = policies.stream()
                .map(policy -> {
                    Map<String, Object> policyMap = new HashMap<>();
                    policyMap.put("policyNo", policy.getPolicyNo());
                    policyMap.put("status", policy.getStatus());
                    policyMap.put("createdAt", policy.getCreatedAt());
                    policyMap.put("approvedAt", policy.getApprovedAt());
                    policyMap.put("startDate", policy.getStartDate());
                    policyMap.put("endDate", policy.getEndDate());
                    policyMap.put("prim", policy.getPrim());
                    // Diğer gerekli alanları ekleyin
                    return policyMap;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }


}



