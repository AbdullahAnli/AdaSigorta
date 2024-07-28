package com.AdaSigorta.dto;



    public class PolicyDTO {
        private Long id;
        private Long customerId;
        private Long vehicleId;
        private Long policyNo;
        private Double prim;

        public PolicyDTO() {}

        public PolicyDTO(Long id, Long customerId, Long vehicleId, Long policyNo, Double prim) {
            this.id = id;
            this.customerId = customerId;
            this.vehicleId = vehicleId;
            this.policyNo = policyNo;
            this.prim = prim;
        }

        // Getter ve Setter metodları
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public Long getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(Long vehicleId) {
            this.vehicleId = vehicleId;
        }

        public Long getPolicyNo() {
            return policyNo;
        }

        public void setPolicyNo(Long policyNo) {
            this.policyNo = policyNo;
        }

        public Double getPrim() {
            return prim;
        }

        public void setPrim(Double prim) {
            this.prim = prim;
        }
    }


