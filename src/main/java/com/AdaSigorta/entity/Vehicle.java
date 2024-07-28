package com.AdaSigorta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provinceCode;
    private String plateCode;
    private String vehicleBrand;
    private String vehicleModel;
    private Integer vehicleModelYear;
    private String engineNumber;
    private String chassisNumber;

    public Vehicle(){}

    public Vehicle(Long id, String provinceCode, String plateCode,
                   String vehicleBrand, String vehicleModel, Integer vehicleModelYear,
                   String engineNumber, String chassisNumber) {
        this.id = id;
        this.provinceCode = provinceCode;
        this.plateCode = plateCode;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleModelYear = vehicleModelYear;
        this.engineNumber = engineNumber;
        this.chassisNumber = chassisNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getVehicleModelYear() {
        return vehicleModelYear;
    }

    public void setVehicleModelYear(Integer vehicleModelYear) {
        this.vehicleModelYear = vehicleModelYear;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }
}