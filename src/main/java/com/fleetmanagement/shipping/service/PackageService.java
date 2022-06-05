package com.fleetmanagement.shipping.service;

import java.util.List;
import java.util.UUID;

import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;

public interface PackageService {

	/**
	 * This method is used to get of all packages.
	 * 
	 * @return package list
	 */
	List<PackageDto> getAllPackages();

	/**
	 * This method is used to get package with using the entered barcode data.
	 * 
	 * @param barcode
	 * @return package
	 */
	PackageDto getPackageByBarcode(String barcode);

	/**
	 * This method is used to insert a new package to the system.
	 * 
	 * @param packageRequest (barcode, delivery point id, weight)
	 * @return package
	 */
	PackageDto insert(PackageRequestDto packageRequest);
	
	/**
	 * This method is used to update a package with using the entered barcode data.
	 * 
	 * @param barcode
	 * @param packageRequest (delivery point id, bag barcode, state)
	 * @return
	 */
	PackageDto update(String barcode, PackageRequestDto packageRequest);
	
	/**
	 * This method is used to get packages with using the entered bag id data.
	 * Retrieves the packages contained in the bag.
	 * 
	 * @param bagId
	 * @return package list
	 */
	List<PackageDto> getPackagesByBagId(UUID bagId);

	/**
	 * This method is used to delete a package with using  the entered barcode data from the system.
	 * 
	 * @param barcode
	 * @return success(1)/fail(0)
	 */
	Long delete(String barcode);

}
