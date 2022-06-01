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

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;
import com.fleetmanagement.shipping.service.DeliveryPointService;

@RestController
@RequestMapping("/api/v0/delivery-points")
public class DeliveryPointController {

	private final DeliveryPointService service;

	@Autowired
	public DeliveryPointController(DeliveryPointService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<DeliveryPointDto>> getDeliveryPoints() {
		return new ResponseEntity<>(service.getAllDeliveryPoints(), HttpStatus.OK);
	}

	@GetMapping("/{value}")
	public ResponseEntity<DeliveryPointDto> getDeliveryPointByPoint(@PathVariable Integer point) {
		return new ResponseEntity<>(service.getDeliveryPointByPoint(point), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DeliveryPointDto> createDeliveryPoint(
			@RequestBody DeliveryPointRequestDto deliveryPointRequest) {
		return new ResponseEntity<>(service.insert(deliveryPointRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{value}")
	public ResponseEntity<Integer> deleteDeliveryPoint(@PathVariable Integer value) {
		return new ResponseEntity<>(service.delete(value), HttpStatus.OK);
	}

}
