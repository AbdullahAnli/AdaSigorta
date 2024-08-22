package com.AdaSigorta.controller;
import com.AdaSigorta.entity.Vehicle;
import com.AdaSigorta.repository.VehicleRepository;
import com.AdaSigorta.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping
    public ResponseEntity<Vehicle>saveVehicle(@RequestBody Vehicle vehicle){
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicle));
    }
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicleOpt = vehicleService.getVehicleById(id);
        if (vehicleOpt.isPresent()) {
            return ResponseEntity.ok(vehicleOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle>updateVehcle(@PathVariable Long id,@RequestBody Vehicle vehicle){
        Vehicle updatedVehicle=vehicleService.updateVehicle(id,vehicle);
        if (updatedVehicle!=null){
            return ResponseEntity.ok(updatedVehicle);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Vehicle> searchVehicles(

            @RequestParam(required = false) String vehicleBrand,
            @RequestParam(required = false) String vehicleModel,
            @RequestParam(required = false) Integer vehicleModelYear) {
        return vehicleService.searchVehicle(vehicleBrand, vehicleModel, vehicleModelYear);
    }


}

