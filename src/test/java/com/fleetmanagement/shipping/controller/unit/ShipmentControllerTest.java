package com.fleetmanagement.shipping.controller.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fleetmanagement.shipping.controller.ShipmentController;
import com.fleetmanagement.shipping.dto.ShipmentDto;
import com.fleetmanagement.shipping.service.ShipmentService;

@ExtendWith(MockitoExtension.class)
class ShipmentControllerTest {

	@Mock
	ShipmentService service;
	@InjectMocks
	ShipmentController controller;
	
	ShipmentDto shipment;
	
	@BeforeEach
	public void setUp() {
		shipment = new ShipmentDto();
		shipment.setPlate("34XX444");
		shipment.setRoute(new ArrayList<>());
	}

	@Test
	public void testTransfer() {
		when(service.transfer(any(ShipmentDto.class))).thenReturn(shipment);
		assertNotNull(controller.transfer(shipment));
	}

}
