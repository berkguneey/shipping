package com.fleetmanagement.shipping.service.unit;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.helper.ValidationStrategy;
import com.fleetmanagement.shipping.model.Bag;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.repository.BagRepository;
import com.fleetmanagement.shipping.service.impl.BagServiceImpl;
import com.fleetmanagement.shipping.service.impl.DeliveryPointServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BagServiceTest {

	@Mock
	BagRepository repository;
	@Mock
	DeliveryPointServiceImpl deliveryPointServiceImpl;
	@Qualifier("BagBarcodeValidation")
	@Mock
	ValidationStrategy validationStrategy;
	@Spy
	ModelMapper mapper = new ModelMapper();
	@InjectMocks
	BagServiceImpl service;
	
	DeliveryPointDto deliveryPoint;
	BagRequestDto bagRequest;
	List<Bag> bagList;
	Bag bag1;
	Bag bag2;

	@BeforeEach
	public void setUp() {
		deliveryPoint = new DeliveryPointDto();
		deliveryPoint.setId(UUID.randomUUID());
		deliveryPoint.setName("Branch");
		deliveryPoint.setCreatedAt(LocalDateTime.now());
		
		bagRequest = new BagRequestDto();
		bagRequest.setBarcode("C725797");
		bagRequest.setDeliveryPointId(UUID.randomUUID());
		
		bag1 = new Bag();
		bag1.setId(UUID.randomUUID());
		bag1.setBarcode("C725797");
		bag1.setDeliveryPoint(new DeliveryPoint());
		bag1.setCreatedAt(LocalDateTime.now());
		
		bag2 = new Bag();
		bag2.setId(UUID.randomUUID());
		bag2.setBarcode("C725798");
		bag2.setDeliveryPoint(new DeliveryPoint());
		bag2.setCreatedAt(LocalDateTime.now());
		
		bagList = new ArrayList<>(Arrays.asList(bag1, bag2));
	}
	
	@Test
	public void testGetBags() {
		when(repository.findAll()).thenReturn(bagList);
		assertNotNull(service.getAllBags());
	}

	@Test
	public void testGetBagByBarcode() {
		when(repository.findBagByBarcode(any())).thenReturn(Optional.of(bag1));
		assertNotNull(service.getBagByBarcode("C725797"));
	}
	
	@Test
	public void testGetBagByBarcode_ReturnNoDataFoundException() {
		when(repository.findBagByBarcode(any())).thenReturn(Optional.empty());
		assertThrows(NoDataFoundException.class, () -> service.getBagByBarcode("C725797"));
	}

	@Test
	public void testInsert() {
		when(repository.existsBagByBarcode(anyString())).thenReturn(false);
		when(deliveryPointServiceImpl.getDeliveryPointById(any())).thenReturn(deliveryPoint);
		when(repository.save(any(Bag.class))).thenReturn(bag1);
		assertNotNull(service.insert(bagRequest));
	}
	
	@Test
	public void testInsert_ReturnBusinessException() {
		when(repository.existsBagByBarcode(anyString())).thenReturn(true);
		when(repository.save(any(Bag.class))).thenReturn(bag1);
		assertThrows(BusinessException.class, () -> service.insert(bagRequest));
	}
	
	@Test
	public void testDeleteBag() {
		when(repository.save(any())).thenReturn(1L);
		assertNotNull(service.delete("C725797"));
	}

}
