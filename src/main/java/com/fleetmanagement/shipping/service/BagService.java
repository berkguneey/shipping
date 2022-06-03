package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;

public interface BagService {

	List<BagDto> getAllBags();

	BagDto getBagByBarcode(String barcode);

	BagDto insert(BagRequestDto bagRequest);

	Long delete(String barcode);

	BagDto update(String barcode, BagRequestDto bagRequest);

}
