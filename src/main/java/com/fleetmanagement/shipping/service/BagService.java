package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;

public interface BagService {

	/**
	 * This method is used to get of all bags.
	 * 
	 * @return bag list
	 */
	List<BagDto> getAllBags();

	/**
	 * This method is used to get bag with using the entered barcode data.
	 * 
	 * @param barcode
	 * @return bag
	 */
	BagDto getBagByBarcode(String barcode);

	/**
	 * This method is used to insert a new bag to the system.
	 * 
	 * @param bagRequest (barcode, delivery point id)
	 * @return bag
	 */
	BagDto insert(BagRequestDto bagRequest);

	/**
	 * This method is used to delete a bag with using  the entered barcode data from the system.
	 * 
	 * @param barcode
	 * @return success(1)/fail(0)
	 */
	Long delete(String barcode);

	/**
	 * 
	 * This method is used to update a bag with using the entered barcode data.
	 * 
	 * @param barcode
	 * @param bagRequest (delivery point id, state)
	 * @return bag
	 */
	BagDto update(String barcode, BagRequestDto bagRequest);

}
