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

@RestController
@RequestMapping("/api/v0/vehicles")
public class VehicleController {

	private final VehicleService service;

	@Autowired
	public VehicleController(VehicleService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<VehicleDto>> getVehicles() {
		return new ResponseEntity<List<VehicleDto>>(service.getAllVehicles(), HttpStatus.OK);
	}

	@GetMapping("/{licensePlate}")
	public ResponseEntity<VehicleDto> getVehicleByLicensePlate(@PathVariable String licensePlate) {
		return new ResponseEntity<VehicleDto>(service.getVehicleByLicensePlate(licensePlate), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleRequestDto vehicleRequest) {
		return new ResponseEntity<VehicleDto>(service.insert(vehicleRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{licensePlate}")
	public ResponseEntity<String> deleteVehicle(@PathVariable String licensePlate) {
		return new ResponseEntity<String>(service.delete(licensePlate), HttpStatus.OK);
	}

}
