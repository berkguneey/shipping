package com.fleetmanagement.shipping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.shipping.dto.ShipmentDto;
import com.fleetmanagement.shipping.service.ShipmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Shipment Controller")
@RestController
@RequestMapping("/api/v0/shipments")
public class ShipmentController {

	private final ShipmentService service;

	@Autowired
	public ShipmentController(ShipmentService service) {
		this.service = service;
	}

	@ApiOperation(value = "Delivery transfer simulation")
	@PostMapping("/transfer")
	public ResponseEntity<ShipmentDto> transfer(@RequestBody ShipmentDto shipment) {
		return new ResponseEntity<>(service.transfer(shipment), HttpStatus.OK);
	}

}
