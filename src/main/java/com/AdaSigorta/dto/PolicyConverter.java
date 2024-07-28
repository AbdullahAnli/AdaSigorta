package com.AdaSigorta.dto;

import com.AdaSigorta.entity.Policy;

public class PolicyConverter {

    public static PolicyDTO convertToDTO(Policy policy) {
        return new PolicyDTO(
                policy.getId(),
                policy.getCustomer().getId(),
                policy.getVehicle().getId(),
                policy.getPolicyNo(),
                policy.getPrim()
        );
    }
}
