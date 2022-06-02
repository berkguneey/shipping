package com.fleetmanagement.shipping.service.unit;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.model.Vehicle;
import com.fleetmanagement.shipping.repository.DeliveryPointRepository;
import com.fleetmanagement.shipping.service.impl.DeliveryPointServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeliveryPointServiceTest {

	@Mock
	DeliveryPointRepository repository;
	@Spy
	ModelMapper mapper = new ModelMapper();
	@InjectMocks
	DeliveryPointServiceImpl service;
	
	VehicleRequestDto vehicleRequest;
	List<Vehicle> vehicleList;
	Vehicle vehicle1;
	Vehicle vehicle2;
	
	DeliveryPointRequestDto deliveryPointRequest;
	List<DeliveryPoint> deliveryPointList;
	DeliveryPoint deliveryPoint1;
	DeliveryPoint deliveryPoint2;

	@BeforeEach
	public void setUp() {
		deliveryPointRequest = new DeliveryPointRequestDto();
		deliveryPointRequest.setName("Branch");

		deliveryPoint1 = new DeliveryPoint();
		deliveryPoint1.setId(UUID.randomUUID());
		deliveryPoint1.setName("Branch");
		deliveryPoint1.setCreatedAt(LocalDateTime.now());

		deliveryPoint2 = new DeliveryPoint();
		deliveryPoint2.setId(UUID.randomUUID());
		deliveryPoint2.setName("Distribution Center");
		deliveryPoint2.setCreatedAt(LocalDateTime.now());

		deliveryPointList = new ArrayList<>(Arrays.asList(deliveryPoint1, deliveryPoint2));
	}

	@Test
	public void testGetAllDeliveryPoints() {
		when(repository.findAll()).thenReturn(deliveryPointList);
		assertNotNull(service.getAllDeliveryPoints());
	}

	@Test
	public void testGetDeliveryPointById() {
		when(repository.findById(any())).thenReturn(Optional.of(deliveryPoint1));
		assertNotNull(service.getDeliveryPointById(UUID.randomUUID()));
	}
	
	@Test
	public void testGetDeliveryPointById_ReturnNoDataFound() {
		when(repository.findById(any())).thenReturn(Optional.empty());
		assertThrows(NoDataFoundException.class, () -> service.getDeliveryPointById(UUID.randomUUID()));
	}

	@Test
	public void testInsert() {
		when(repository.existsDeliveryPointByName(anyString())).thenReturn(false);
		when(repository.save(any(DeliveryPoint.class))).thenReturn(deliveryPoint1);
		assertNotNull(service.insert(deliveryPointRequest));
	}
	
	@Test
	public void testInsert_ReturnAlreadyExists() {
		when(repository.existsDeliveryPointByName(anyString())).thenReturn(true);
		when(repository.save(any(DeliveryPoint.class))).thenReturn(deliveryPoint1);
		assertThrows(BusinessException.class, () -> service.insert(deliveryPointRequest));
	}

	@Test
	public void testDelete() {
		service.delete(UUID.randomUUID());
		verify(repository, times(1)).deleteById(any());
	}

}
