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

import io.swagger.annotations.Api;

@Api(tags = "Delivery Point Controller")
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

	@GetMapping("/{id}")
	public ResponseEntity<DeliveryPointDto> getDeliveryPointById(@PathVariable Long id) {
		return new ResponseEntity<>(service.getDeliveryPointById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DeliveryPointDto> createDeliveryPoint(
			@RequestBody DeliveryPointRequestDto deliveryPointRequest) {
		return new ResponseEntity<>(service.insert(deliveryPointRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteDeliveryPoint(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(1L, HttpStatus.OK);
	}

}
