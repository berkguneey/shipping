package com.fleetmanagement.shipping.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		deliveryPointRequest = DeliveryPointRequestDto.builder().deliveryPoint("Branch").point(1).build();
		deliveryPoint1 = DeliveryPointDto.builder().id(UUID.randomUUID()).deliveryPoint("Branch").point(1).createdAt(new Date()).build();
		deliveryPoint2 = DeliveryPointDto.builder().id(UUID.randomUUID()).deliveryPoint("Distribution Center").point(2).createdAt(new Date()).build();
		deliveryPointList = new ArrayList<>(Arrays.asList(deliveryPoint1, deliveryPoint2));
	}
	
	@Test
	public void testGetDeliveryPoints() {
		when(service.getAllDeliveryPoints()).thenReturn(deliveryPointList);
		assertNotNull(controller.getDeliveryPoints());
	}
	
	@Test
	public void testGetDeliveryPointByPoint() {
		when(service.getDeliveryPointByPoint(any())).thenReturn(deliveryPoint1);
		assertNotNull(controller.getDeliveryPointByPoint(1));
	}
	
	@Test
	public void testCreateVehicle() {
		when(service.insert(any(DeliveryPointRequestDto.class))).thenReturn(deliveryPoint1);
		assertNotNull(controller.createDeliveryPoint(deliveryPointRequest));
	}
	
	@Test
	public void testDeleteVehicle() {
		when(service.delete(any())).thenReturn(deliveryPoint1.getPoint());
		assertNotNull(controller.deleteDeliveryPoint(1));
	}

}
