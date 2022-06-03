package com.fleetmanagement.shipping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.service.VehicleService;

@RestController
@RequestMapping("/api/v0/shipments")
public class ShipmentController {

	private final VehicleService service;

	@Autowired
	public ShipmentController(VehicleService service) {
		this.service = service;
	}

	@PostMapping("/load")
	public ResponseEntity<VehicleDto> load(@RequestBody VehicleRequestDto vehicleRequest) {
		return new ResponseEntity<>(service.insert(vehicleRequest), HttpStatus.OK);
	}
	
	@PostMapping("/unload")
	public ResponseEntity<VehicleDto> unload(@RequestBody VehicleRequestDto vehicleRequest) {
		return new ResponseEntity<>(service.insert(vehicleRequest), HttpStatus.OK);
	}

}
