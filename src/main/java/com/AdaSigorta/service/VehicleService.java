package com.AdaSigorta.service;
import com.AdaSigorta.entity.Vehicle;
import com.AdaSigorta.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle >getVehicleById(Long id){
        return vehicleRepository.findById(id);
    }

    public Vehicle updateVehicle(Long id,Vehicle updatedVehicle){
        if (vehicleRepository.existsById(id)){
            updatedVehicle.setId(id);
            return vehicleRepository.save(updatedVehicle);
        }
        return null;
    }
    public void deleteVehicle(Long id){
        vehicleRepository.deleteById(id);
    }
    public List<Vehicle> searchVehicle(String vehicleBrand, String vehicleModel, Integer vehicleModelYear) {
        return vehicleRepository.searchVehicle(vehicleBrand, vehicleModel, vehicleModelYear);
    }



}
