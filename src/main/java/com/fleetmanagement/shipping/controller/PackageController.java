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

import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.service.PackageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Package Controller")
@RestController
@RequestMapping("/api/v0/packages")
public class PackageController {

	private final PackageService service;

	@Autowired
	public PackageController(PackageService service) {
		this.service = service;
	}

	@ApiOperation(value = "Get all packages")
	@GetMapping
	public ResponseEntity<List<PackageDto>> getPackages() {
		return new ResponseEntity<>(service.getAllPackages(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get package by using unique barcode data")
	@GetMapping("/{barcode}")
	public ResponseEntity<PackageDto> getPackageByBarcode(@PathVariable String barcode) {
		return new ResponseEntity<>(service.getPackageByBarcode(barcode), HttpStatus.OK);
	}

	@ApiOperation(value = "Create package")
	@PostMapping
	public ResponseEntity<PackageDto> createPackage(@RequestBody PackageRequestDto packageRequest) {
		return new ResponseEntity<>(service.insert(packageRequest), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update package")
	@PutMapping("/{barcode}")
	public ResponseEntity<PackageDto> updatePackage(@PathVariable String barcode, @RequestBody PackageRequestDto packageRequest) {
		return new ResponseEntity<>(service.update(barcode, packageRequest), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete package")
	@DeleteMapping("/{barcode}")
	public ResponseEntity<Long> deletePackage(@PathVariable String barcode) {
		return new ResponseEntity<>(service.delete(barcode), HttpStatus.OK);
	}

}
