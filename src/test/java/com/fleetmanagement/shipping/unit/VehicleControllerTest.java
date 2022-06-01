package com.fleetmanagement.shipping.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.fleetmanagement.shipping.controller.VehicleController;
import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.service.VehicleService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VehicleControllerTest {

	@Mock
	VehicleService service;
	@Mock
	ModelMapper mapper;
	@InjectMocks
	VehicleController controller;
	
	VehicleRequestDto vehicleRequest;
	List<VehicleDto> vehicleList;
	VehicleDto vehicle1;
	VehicleDto vehicle2;
	
	@BeforeEach
	public void setUp() {
		vehicleRequest = VehicleRequestDto.builder().licensePlate("34XX444").model("HONDA").build();
		vehicle1 = VehicleDto.builder().id(UUID.randomUUID()).licensePlate("34XX444").model("HONDA").createdAt(new Date()).build();
		vehicle2 = VehicleDto.builder().id(UUID.randomUUID()).licensePlate("34YY555").model("SCANIA").createdAt(new Date()).build();
		vehicleList = new ArrayList<>(Arrays.asList(vehicle1, vehicle2));
	}
	
	@Test
	public void testGetVehicles() {
		when(service.getAllVehicles()).thenReturn(vehicleList);
		assertNotNull(controller.getVehicles());
	}
	
	@Test
	public void testGetVehicleByLicensePlate() {
		when(service.getVehicleByLicensePlate(anyString())).thenReturn(vehicle1);
		assertNotNull(controller.getVehicleByLicensePlate("34XX444"));
	}
	
	@Test
	public void testCreateVehicle() {
		when(service.insert(any(VehicleRequestDto.class))).thenReturn(vehicle1);
		assertNotNull(controller.createVehicle(vehicleRequest));
	}
	
	@Test
	public void testDeleteVehicle() {
		when(service.delete(anyString())).thenReturn(vehicle1.getLicensePlate());
		assertNotNull(controller.deleteVehicle("34XX444"));
	}

}
