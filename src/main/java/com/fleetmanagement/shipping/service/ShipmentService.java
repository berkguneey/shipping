package com.fleetmanagement.shipping.service;

import com.fleetmanagement.shipping.dto.ShipmentDto;

public interface ShipmentService {

	/**
	 * This method loads the packages onto the vehicle and unloads them to the respective delivery points in the system.
	 * This method provides a shipment simulation.
	 * 
	 * @param shipment (vehicle, routes, deliveries)
	 * @return shipment (vehicle, routes, deliveries and their states)
	 */
	ShipmentDto transfer(ShipmentDto shipment);

}
