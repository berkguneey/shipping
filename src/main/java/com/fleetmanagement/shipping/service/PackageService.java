package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;

public interface PackageService {

	List<PackageDto> getAllPackages();

	PackageDto getPackageByBarcode(String barcode);

	PackageDto insert(PackageRequestDto packageRequest);

	Long delete(String barcode);

	PackageDto updateBagId(String barcode, PackageRequestDto packageRequest);

}
