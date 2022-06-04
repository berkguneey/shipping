package com.fleetmanagement.shipping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Vehicle Controller")
@RestController
@RequestMapping("/api/v0/vehicles")
public class VehicleController {

	private final VehicleService service;

	@Autowired
	public VehicleController(VehicleService service) {
		this.service = service;
	}

	@ApiOperation(value = "Get all vehicles")
	@GetMapping
	public ResponseEntity<List<VehicleDto>> getVehicles() {
		return new ResponseEntity<>(service.getAllVehicles(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get vehicle by using unique license plate data")
	@GetMapping("/{licensePlate}")
	public ResponseEntity<VehicleDto> getVehicleByLicensePlate(@PathVariable String licensePlate) {
		return new ResponseEntity<>(service.getVehicleByLicensePlate(licensePlate), HttpStatus.OK);
	}

	@ApiOperation(value = "Create vehicle")
	@PostMapping
	public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleRequestDto vehicleRequest) {
		return new ResponseEntity<>(service.insert(vehicleRequest), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete vehicle")
	@DeleteMapping("/{licensePlate}")
	public ResponseEntity<Long> deleteVehicle(@PathVariable String licensePlate) {
		return new ResponseEntity<>(service.delete(licensePlate), HttpStatus.OK);
	}

}
