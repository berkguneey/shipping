package com.fleetmanagement.shipping.service;

import java.util.List;
import java.util.UUID;

import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;

public interface PackageService {

	List<PackageDto> getAllPackages();

	PackageDto getPackageByBarcode(String barcode);

	PackageDto insert(PackageRequestDto packageRequest);
	
	PackageDto update(String barcode, PackageRequestDto packageRequest);
	
	List<PackageDto> getPackagesByBagId(UUID bagId);

	Long delete(String barcode);

}
