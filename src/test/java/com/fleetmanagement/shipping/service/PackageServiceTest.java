package com.fleetmanagement.shipping.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.model.Package;
import com.fleetmanagement.shipping.repository.PackageRepository;
import com.fleetmanagement.shipping.service.impl.BagServiceImpl;
import com.fleetmanagement.shipping.service.impl.DeliveryPointServiceImpl;
import com.fleetmanagement.shipping.service.impl.PackageServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PackageServiceTest {

	@Mock
	PackageRepository repository;
	@Mock
	DeliveryPointServiceImpl deliveryPointServiceImpl;
	@Mock
	BagServiceImpl bagServiceImpl;
	@Spy
	ModelMapper mapper = new ModelMapper();
	@InjectMocks
	PackageServiceImpl service;
	
	DeliveryPoint deliveryPoint;
	DeliveryPointDto deliveryPointDto;
	PackageRequestDto packageRequest;
	PackageRequestDto packageRequestWithBagId;
	List<Package> packageList;
	Package package1;
	Package package2;
	BagDto bag;

	@BeforeEach
	public void setUp() {
		deliveryPoint = new DeliveryPoint();
		deliveryPoint.setId(1L);
		deliveryPoint.setName("Branch");
		deliveryPoint.setCreatedAt(LocalDateTime.now());
		
		deliveryPointDto = new DeliveryPointDto();
		deliveryPointDto.setId(1L);
		deliveryPointDto.setName("Branch");
		deliveryPointDto.setCreatedAt(LocalDateTime.now());
		
		packageRequest = new PackageRequestDto();
		packageRequest.setBarcode("P7988000121");
		packageRequest.setDeliveryPointId(1L);
		packageRequest.setWeight(10);
		packageRequest.setState(1);
		
		packageRequestWithBagId = new PackageRequestDto();
		packageRequestWithBagId.setBarcode("P7988000121");
		packageRequestWithBagId.setWeight(10);
		packageRequestWithBagId.setState(1);
		packageRequestWithBagId.setBagBarcode("C725797");
		
		bag = new BagDto();
		bag.setId(UUID.randomUUID());
		bag.setBarcode("C725797");
		bag.setDeliveryPoint(deliveryPointDto);
		bag.setCreatedAt(LocalDateTime.now());

		package1 = new Package();
		package1.setId(UUID.randomUUID());
		package1.setBarcode("P7988000121");
		package1.setDeliveryPoint(deliveryPoint);
		package1.setWeight(10);
		package1.setCreatedAt(LocalDateTime.now());

		package2 = new Package();
		package2.setId(UUID.randomUUID());
		package2.setBarcode("P7988000122");
		package2.setDeliveryPoint(deliveryPoint);
		package2.setWeight(10);
		package2.setCreatedAt(LocalDateTime.now());

		packageList = new ArrayList<>(Arrays.asList(package1, package2));
	}
	
	@Test
	public void testGetPackages() {
		when(repository.findAll()).thenReturn(packageList);
		assertNotNull(service.getAllPackages());
	}

	@Test
	public void testGetPackageByBarcode() {
		when(repository.findPackageByBarcode(any())).thenReturn(Optional.of(package1));
		assertNotNull(service.getPackageByBarcode("P7988000121"));
	}
	
	@Test
	public void testGetPackageByBarcode_ReturnNoDataFoundException() {
		when(repository.findPackageByBarcode(any())).thenReturn(Optional.empty());
		assertThrows(NoDataFoundException.class, () -> service.getPackageByBarcode("P7988000121"));
	}

	@Test
	public void testInsert() {
		when(repository.existsPackageByBarcode(anyString())).thenReturn(false);
		when(deliveryPointServiceImpl.getDeliveryPointById(any())).thenReturn(deliveryPointDto);
		when(repository.save(any(Package.class))).thenReturn(package1);
		assertNotNull(service.insert(packageRequest));
	}
	
	@Test
	public void testInsert_ReturnBusinessException() {
		when(repository.existsPackageByBarcode(anyString())).thenReturn(true);
		when(repository.save(any(Package.class))).thenReturn(package1);
		assertThrows(BusinessException.class, () -> service.insert(packageRequest));
	}
	
	@Test
	public void testUpdate() {
		when(repository.findPackageByBarcode(anyString())).thenReturn(Optional.of(package1));
		when(deliveryPointServiceImpl.getDeliveryPointById(any())).thenReturn(deliveryPointDto);
		when(repository.save(any(Package.class))).thenReturn(package1);
		assertNotNull(service.update("P7988000121", packageRequest));
	}
	
	@Test
	public void testUpdate_WithAssignBagToPackage() {
		when(repository.findPackageByBarcode(anyString())).thenReturn(Optional.of(package1));
		when(bagServiceImpl.getBagByBarcode(any())).thenReturn(bag);
		when(repository.save(any(Package.class))).thenReturn(package1);
		assertNotNull(service.update("P7988000121", packageRequestWithBagId));
	}
	
	@Test
	public void testGetPackagesByBagId() {
		when(repository.findPackagesByBagId(any())).thenReturn(packageList);
		assertNotNull(service.getPackagesByBagId(UUID.randomUUID()));
	}
	
	@Test
	public void testDeletePackage() {
		when(repository.save(any())).thenReturn(1L);
		assertNotNull(service.delete("P7988000121"));
	}

}
