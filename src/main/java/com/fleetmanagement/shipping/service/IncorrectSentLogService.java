package com.fleetmanagement.shipping.service;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;

public interface IncorrectSentLogService {

	VehicleDto insert(VehicleRequestDto vehicleRequest);

}
