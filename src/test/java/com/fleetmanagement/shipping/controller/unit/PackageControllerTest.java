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

import com.fleetmanagement.shipping.controller.PackageController;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.service.PackageService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PackageControllerTest {

	@Mock
	PackageService service;
	@Mock
	ModelMapper mapper;
	@InjectMocks
	PackageController controller;

	PackageRequestDto packageRequest;
	List<PackageDto> packageList;
	PackageDto package1;
	PackageDto package2;

	@BeforeEach
	public void setUp() {
		packageRequest = new PackageRequestDto();
		packageRequest.setBarcode("P7988000121");
		packageRequest.setDeliveryPointId(1L);
		packageRequest.setWeight(10);

		package1 = new PackageDto();
		package1.setId(UUID.randomUUID());
		package1.setBarcode("P7988000121");
		package1.setDeliveryPoint(new DeliveryPointDto());
		package1.setWeight(10);
		package1.setCreatedAt(LocalDateTime.now());

		package2 = new PackageDto();
		package2.setId(UUID.randomUUID());
		package2.setBarcode("P7988000122");
		package2.setDeliveryPoint(new DeliveryPointDto());
		package2.setWeight(10);
		package2.setCreatedAt(LocalDateTime.now());

		packageList = new ArrayList<>(Arrays.asList(package1, package2));
	}

	@Test
	public void testGetPackages() {
		when(service.getAllPackages()).thenReturn(packageList);
		assertNotNull(controller.getPackages());
	}

	@Test
	public void testGetPackageByBarcode() {
		when(service.getPackageByBarcode(any())).thenReturn(package1);
		assertNotNull(controller.getPackageByBarcode("P7988000121"));
	}

	@Test
	public void testCreatePackage() {
		when(service.insert(any(PackageRequestDto.class))).thenReturn(package1);
		assertNotNull(controller.createPackage(packageRequest));
	}
	
	@Test
	public void testUpdatePackage() {
		when(service.update(any(), any(PackageRequestDto.class))).thenReturn(package1);
		assertNotNull(controller.updatePackage("P7988000121", packageRequest));
	}

	@Test
	public void testDeletePackage() {
		when(service.delete(any())).thenReturn(1L);
		assertNotNull(controller.deletePackage("P7988000121"));
	}

}
