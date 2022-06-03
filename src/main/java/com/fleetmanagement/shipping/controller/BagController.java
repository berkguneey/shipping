package com.fleetmanagement.shipping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.service.BagService;

@RestController
@RequestMapping("/api/v0/bags")
public class BagController {

	private final BagService service;

	@Autowired
	public BagController(BagService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<BagDto>> getBags() {
		return new ResponseEntity<>(service.getAllBags(), HttpStatus.OK);
	}

	@GetMapping("/{barcode}")
	public ResponseEntity<BagDto> getBagByBarcode(@PathVariable String barcode) {
		return new ResponseEntity<>(service.getBagByBarcode(barcode), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BagDto> createBag(@RequestBody BagRequestDto bagRequest) {
		return new ResponseEntity<>(service.insert(bagRequest), HttpStatus.OK);
	}
	
	@PutMapping("/{barcode}")
	public ResponseEntity<BagDto> updateBag(@PathVariable String barcode, @RequestBody BagRequestDto bagRequest) {
		return new ResponseEntity<>(service.update(barcode, bagRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{barcode}")
	public ResponseEntity<Long> deleteBag(@PathVariable String barcode) {
		return new ResponseEntity<>(service.delete(barcode), HttpStatus.OK);
	}

}
