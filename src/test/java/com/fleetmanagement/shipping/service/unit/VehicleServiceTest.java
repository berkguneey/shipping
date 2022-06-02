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

import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.exception.AlreadyExistsException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.helper.ValidationStrategy;
import com.fleetmanagement.shipping.model.Vehicle;
import com.fleetmanagement.shipping.repository.VehicleRepository;
import com.fleetmanagement.shipping.service.impl.VehicleServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VehicleServiceTest {

	@Mock
	VehicleRepository repository;
	@Qualifier("LicensePlateValidation")
	@Mock
	ValidationStrategy validationStrategy;
	@Spy
	ModelMapper mapper = new ModelMapper();
	@InjectMocks
	VehicleServiceImpl service;
	
	VehicleRequestDto vehicleRequest;
	List<Vehicle> vehicleList;
	Vehicle vehicle1;
	Vehicle vehicle2;
	
	@BeforeEach
	public void setUp() {
		vehicleRequest = new VehicleRequestDto();
		vehicleRequest.setLicensePlate("34XX444");
		vehicleRequest.setModel("HONDA");
		
		vehicle1 = new Vehicle();
		vehicle1.setId(UUID.randomUUID());
		vehicle1.setLicensePlate("34XX444");
		vehicle1.setModel("HONDA");
		vehicle1.setCreatedAt(LocalDateTime.now());
		
		vehicle2 = new Vehicle();
		vehicle2.setId(UUID.randomUUID());
		vehicle2.setLicensePlate("34YY555");
		vehicle2.setModel("SCANIA");
		vehicle2.setCreatedAt(LocalDateTime.now());
		
		vehicleList = new ArrayList<>(Arrays.asList(vehicle1, vehicle2));
	}
	
	@Test
	public void testGetAllVehicles() {
		when(repository.findAll()).thenReturn(vehicleList);
		assertNotNull(service.getAllVehicles());
	}
	
	@Test
	public void testGetVehicleByLicensePlate() {
		when(repository.findVehicleByLicensePlate(anyString())).thenReturn(Optional.of(vehicle1));
		assertNotNull(service.getVehicleByLicensePlate("34XX444"));
	}
	
	@Test
	public void testGetVehicleByLicensePlate_ReturnNoDataFound() {
		when(repository.findVehicleByLicensePlate(anyString())).thenReturn(Optional.empty());
		assertThrows(NoDataFoundException.class, () -> service.getVehicleByLicensePlate("34XX444"));
	}
	
	@Test
	public void testInsert() {
		when(repository.existsVehicleByLicensePlate(anyString())).thenReturn(false);
		when(repository.save(any(Vehicle.class))).thenReturn(vehicle1);
		assertNotNull(service.insert(vehicleRequest));
	}
	
	@Test
	public void testInsert_ReturnAlreadyExists() {
		when(repository.existsVehicleByLicensePlate(anyString())).thenReturn(true);
		when(repository.save(any(Vehicle.class))).thenReturn(vehicle1);
		assertThrows(AlreadyExistsException.class, () -> service.insert(vehicleRequest));
	}
	
	@Test
	public void testDelete() {
		when(repository.deleteVehicleByLicensePlate(anyString())).thenReturn(1L);
		assertNotNull(service.delete("34XX444"));
	}

}
