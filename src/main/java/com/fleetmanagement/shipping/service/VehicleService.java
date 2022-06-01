package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;

public interface VehicleService {

	List<VehicleDto> getAllVehicles();

	VehicleDto getVehicleByLicensePlate(String licensePlate);

	VehicleDto insert(VehicleRequestDto vehicleRequest);

	Long delete(String licensePlate);

}
