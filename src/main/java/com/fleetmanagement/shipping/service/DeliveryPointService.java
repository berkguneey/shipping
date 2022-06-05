package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;

public interface DeliveryPointService {
	
	/**
	 * This method is used to get of all delivery points.
	 * 
	 * @return delivery point list
	 */
	List<DeliveryPointDto> getAllDeliveryPoints();

	/**
	 * This method is used to get delivery point with using the entered id data.
	 * 
	 * @param id
	 * @return delivery point
	 */
	DeliveryPointDto getDeliveryPointById(Long id);

	/**
	 * This method is used to insert a new delivery point to the system.
	 * 
	 * @param deliveryPointRequest (name)
	 * @return delivery point
	 */
	DeliveryPointDto insert(DeliveryPointRequestDto deliveryPointRequest);

	/**
	 * This method is used to delete a delivery point with using  the entered id data from the system.
	 * 
	 * @param id 
	 */
	void delete(Long id);

}
