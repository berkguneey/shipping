package com.fleetmanagement.shipping.controller.unit;

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

import com.fleetmanagement.shipping.controller.BagController;
import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.service.BagService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BagControllerTest {

	@Mock
	BagService service;
	@Mock
	ModelMapper mapper;
	@InjectMocks
	BagController controller;
	
	BagRequestDto bagRequest;
	List<BagDto> bagList;
	BagDto bag1;
	BagDto bag2;
	
	@BeforeEach
	public void setUp() {
		bagRequest = new BagRequestDto();
		bagRequest.setBarcode("C725797");
		bagRequest.setDeliveryPointId(1L);
		
		bag1 = new BagDto();
		bag1.setId(UUID.randomUUID());
		bag1.setBarcode("C725797");
		bag1.setDeliveryPoint(new DeliveryPointDto());
		bag1.setCreatedAt(LocalDateTime.now());
		
		bag2 = new BagDto();
		bag2.setId(UUID.randomUUID());
		bag2.setBarcode("C725798");
		bag2.setDeliveryPoint(new DeliveryPointDto());
		bag2.setCreatedAt(LocalDateTime.now());
		
		bagList = new ArrayList<>(Arrays.asList(bag1, bag2));
	}
	
	@Test
	public void testGetBags() {
		when(service.getAllBags()).thenReturn(bagList);
		assertNotNull(controller.getBags());
	}

	@Test
	public void testGetBagByBarcode() {
		when(service.getBagByBarcode(any())).thenReturn(bag1);
		assertNotNull(controller.getBagByBarcode("C725797"));
	}

	@Test
	public void testCreateBag() {
		when(service.insert(any(BagRequestDto.class))).thenReturn(bag1);
		assertNotNull(controller.createBag(bagRequest));
	}
	
	@Test
	public void testUpdateBag() {
		when(service.update(any(), any(BagRequestDto.class))).thenReturn(bag1);
		assertNotNull(controller.updateBag("C725797", bagRequest));
	}
	
	@Test
	public void testDeleteBag() {
		when(service.delete(any())).thenReturn(1L);
		assertNotNull(controller.deleteBag("C725797"));
	}

}
