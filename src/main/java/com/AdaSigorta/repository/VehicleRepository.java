package com.AdaSigorta.repository;

import com.AdaSigorta.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query("SELECT v FROM Vehicle v WHERE "  +
            "(COALESCE(:vehicleBrand, '') = '' OR LOWER(v.vehicleBrand) = LOWER(:vehicleBrand)) AND " +
            "(COALESCE(:vehicleModel, '') = '' OR LOWER(v.vehicleModel) = LOWER(:vehicleModel)) AND " +
            "(COALESCE(:vehicleModelYear, '') = '' OR v.vehicleModelYear = :vehicleModelYear)")
    List<Vehicle> searchVehicle(@Param("vehicleBrand") String vehicleBrand,
                                @Param("vehicleModel") String vehicleModel,
                                @Param("vehicleModelYear") Integer vehicleModelYear);

}
