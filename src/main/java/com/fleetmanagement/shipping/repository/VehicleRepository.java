package com.fleetmanagement.shipping.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
	Optional<Vehicle> findVehicleByLicensePlate(String licencePlate);
	
	@Transactional
	Long deleteVehicleByLicensePlate(String licencePlate);
	
	boolean existsVehicleByLicensePlate(String licensePlate);
}
