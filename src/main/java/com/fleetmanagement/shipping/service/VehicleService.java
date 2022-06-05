package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;

public interface VehicleService {

	/**
	 * This method is used to get of all vehicles.
	 * 
	 * @return vehicle list
	 */
	List<VehicleDto> getAllVehicles();

	/**
	 * This method is used to get vehicle with using the entered license plate data.
	 * 
	 * @param licensePlate
	 * @return vehicle
	 */
	VehicleDto getVehicleByLicensePlate(String licensePlate);

	/**
	 * This method is used to insert a new vehicle to the system.
	 * 
	 * @param vehicleRequest (license plate, model)
	 * @return vehicle
	 */
	VehicleDto insert(VehicleRequestDto vehicleRequest);

	/**
	 * This method is used to delete a vehicle with using  the entered license plate data from the system.
	 * 
	 * @param licensePlate
	 * @return success(1)/fail(0)
	 */
	Long delete(String licensePlate);

}
