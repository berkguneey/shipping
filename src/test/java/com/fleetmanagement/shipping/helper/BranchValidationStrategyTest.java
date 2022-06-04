package com.fleetmanagement.shipping.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;
import com.fleetmanagement.shipping.service.PackageService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BranchValidationStrategyTest {

	@Mock
	PackageService packageService;
	@Mock
	IncorrectSentLogService incorrectSentLogService;
	@InjectMocks
	BranchValidationStrategy strategy;
	
	DeliveryPointDto deliveryPointDto1;
	DeliveryPointDto deliveryPointDto2;
	List<DeliveryDto> deliveryList;
	DeliveryDto deliveryWithBagBarcode;
	DeliveryDto delivery2;
	DeliveryDto delivery3;
	DeliveryDto delivery4;
	PackageDto dPackageForDelivery2;
	PackageDto dPackageForDelivery3;
	PackageDto dPackageForDelivery4;
	
	@BeforeEach
	public void setUp() {
		deliveryPointDto1 = new DeliveryPointDto();
		deliveryPointDto1.setId(1L);
		deliveryPointDto1.setName("Branch");
		deliveryPointDto1.setCreatedAt(LocalDateTime.now());
		
		deliveryPointDto2 = new DeliveryPointDto();
		deliveryPointDto2.setId(2L);
		deliveryPointDto2.setName("Distribution Center");
		deliveryPointDto2.setCreatedAt(LocalDateTime.now());
		
		deliveryWithBagBarcode = new DeliveryDto();
		deliveryWithBagBarcode.setBarcode("C725797");
		deliveryWithBagBarcode.setState(3);
		
		delivery2 = new DeliveryDto();
		delivery2.setBarcode("P1111111112");
		delivery2.setState(3);
		
		delivery3 = new DeliveryDto();
		delivery3.setBarcode("P1111111113");
		delivery3.setState(3);
		
		delivery4 = new DeliveryDto();
		delivery4.setBarcode("P1111111114");
		delivery4.setState(3);
		
		dPackageForDelivery2 = new PackageDto();
		dPackageForDelivery2.setDeliveryPoint(deliveryPointDto2);
		dPackageForDelivery2.setBag(null);
		
		dPackageForDelivery3 = new PackageDto();
		dPackageForDelivery3.setDeliveryPoint(deliveryPointDto1);
		dPackageForDelivery3.setBag(new BagDto());
		
		dPackageForDelivery4 = new PackageDto();
		dPackageForDelivery4.setDeliveryPoint(deliveryPointDto1);
		dPackageForDelivery4.setBag(null);
		
		deliveryList = new ArrayList<>(Arrays.asList(deliveryWithBagBarcode, delivery2, delivery3, delivery4));
	}
	
	@Test
	public void testUnload() {
		when(packageService.getPackageByBarcode(delivery2.getBarcode())).thenReturn(dPackageForDelivery2);
		when(packageService.getPackageByBarcode(delivery3.getBarcode())).thenReturn(dPackageForDelivery3);
		when(packageService.getPackageByBarcode(delivery4.getBarcode())).thenReturn(dPackageForDelivery4);
		when(incorrectSentLogService.insert(any(IncorrectSentLogRequestDto.class))).thenReturn(new IncorrectSentLogDto());
		when(packageService.update(anyString(), any(PackageRequestDto.class))).thenReturn(new PackageDto());
		assertNotNull(strategy.unload(deliveryList, 1L));
	}

}
