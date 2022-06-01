package com.fleetmanagement.shipping.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import com.fleetmanagement.shipping.controller.DeliveryPointController;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;
import com.fleetmanagement.shipping.service.DeliveryPointService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeliveryPointControllerTest {

	@Mock
	DeliveryPointService service;
	@Mock
	ModelMapper mapper;
	@InjectMocks
	DeliveryPointController controller;
	
	DeliveryPointRequestDto deliveryPointRequest;
	List<DeliveryPointDto> deliveryPointList;
	DeliveryPointDto deliveryPoint1;
	DeliveryPointDto deliveryPoint2;
	
	@BeforeEach
	public void setUp() {
		deliveryPointRequest = new DeliveryPointRequestDto();
		deliveryPointRequest.setName("Branch");
		
		deliveryPoint1 = new DeliveryPointDto();
		deliveryPoint1.setId(UUID.randomUUID());
		deliveryPoint1.setName("Branch");
		deliveryPoint1.setCreatedAt(LocalDateTime.now());
		
		deliveryPoint2 = new DeliveryPointDto();
		deliveryPoint2.setId(UUID.randomUUID());
		deliveryPoint2.setName("Distribution Center");
		deliveryPoint2.setCreatedAt(LocalDateTime.now());
		
		deliveryPointList = new ArrayList<>(Arrays.asList(deliveryPoint1, deliveryPoint2));
	}
	
	@Test
	public void testGetDeliveryPoints() {
		when(service.getAllDeliveryPoints()).thenReturn(deliveryPointList);
		assertNotNull(controller.getDeliveryPoints());
	}
	/*
	@Test
	public void testGetDeliveryPointByPoint() {
		when(service.getDeliveryPointById(any())).thenReturn(deliveryPoint1);
		assertNotNull(controller.getDeliveryPointById(1));
	}
	*/
	@Test
	public void testCreateVehicle() {
		when(service.insert(any(DeliveryPointRequestDto.class))).thenReturn(deliveryPoint1);
		assertNotNull(controller.createDeliveryPoint(deliveryPointRequest));
	}
	/*
	@Test
	public void testDeleteVehicle() {
		when(service.delete(any())).thenReturn(1L);
		assertNotNull(controller.deleteDeliveryPoint(1));
	}
*/
}
