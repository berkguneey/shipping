package com.fleetmanagement.shipping.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.service.BagService;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;
import com.fleetmanagement.shipping.service.PackageService;

@ExtendWith(MockitoExtension.class)
class TransferCenterValidationStrategyTest {

	@Mock
	PackageService packageService;
	@Mock
	BagService bagService;
	@Mock
	IncorrectSentLogService incorrectSentLogService;
	@InjectMocks
	TransferCenterValidationStrategy strategy;
	
	DeliveryPointDto deliveryPointDto1;
	DeliveryPointDto deliveryPointDto3;
	List<DeliveryDto> deliveryList;
	DeliveryDto deliveryWithBagBarcode1;
	DeliveryDto deliveryWithBagBarcode2;
	DeliveryDto delivery2;
	DeliveryDto delivery3;
	DeliveryDto delivery4;
	BagDto dBag1;
	BagDto dBag2;
	PackageDto dPackageForDelivery2;
	PackageDto dPackageForDelivery3;
	PackageDto dPackageForDelivery4;
	
	@BeforeEach
	public void setUp() {
		deliveryPointDto1 = new DeliveryPointDto();
		deliveryPointDto1.setId(1L);
		deliveryPointDto1.setName("Branch");
		deliveryPointDto1.setCreatedAt(LocalDateTime.now());
		
		deliveryPointDto3 = new DeliveryPointDto();
		deliveryPointDto3.setId(3L);
		deliveryPointDto3.setName("Transfer Center");
		deliveryPointDto3.setCreatedAt(LocalDateTime.now());
		
		deliveryWithBagBarcode1 = new DeliveryDto();
		deliveryWithBagBarcode1.setBarcode("C725797");
		deliveryWithBagBarcode1.setState(3);
		
		deliveryWithBagBarcode2 = new DeliveryDto();
		deliveryWithBagBarcode2.setBarcode("C725798");
		deliveryWithBagBarcode2.setState(3);
		
		delivery2 = new DeliveryDto();
		delivery2.setBarcode("P1111111112");
		delivery2.setState(3);
		
		delivery3 = new DeliveryDto();
		delivery3.setBarcode("P1111111113");
		delivery3.setState(3);
		
		delivery4 = new DeliveryDto();
		delivery4.setBarcode("P1111111114");
		delivery4.setState(3);
		
		dBag1 = new BagDto();
		dBag1.setId(UUID.randomUUID());
		dBag1.setBarcode("C725797");
		dBag1.setDeliveryPoint(deliveryPointDto1);
		dBag1.setCreatedAt(LocalDateTime.now());
		
		dBag2 = new BagDto();
		dBag2.setId(UUID.randomUUID());
		dBag2.setBarcode("C725797");
		dBag2.setDeliveryPoint(deliveryPointDto3);
		dBag2.setCreatedAt(LocalDateTime.now());
		
		dPackageForDelivery2 = new PackageDto();
		dPackageForDelivery2.setBarcode("P1111111116");
		dPackageForDelivery2.setDeliveryPoint(deliveryPointDto1);
		dPackageForDelivery2.setBag(null);
		
		dPackageForDelivery3 = new PackageDto();
		dPackageForDelivery3.setBarcode("P1111111117");
		dPackageForDelivery3.setDeliveryPoint(deliveryPointDto3);
		dPackageForDelivery3.setBag(null);
		
		dPackageForDelivery4 = new PackageDto();
		dPackageForDelivery4.setBarcode("P1111111118");
		dPackageForDelivery4.setDeliveryPoint(deliveryPointDto3);
		dPackageForDelivery4.setBag(new BagDto());
		dPackageForDelivery4.setState(4);
		
		deliveryList = new ArrayList<>(Arrays.asList(deliveryWithBagBarcode1, deliveryWithBagBarcode2, delivery2, delivery3, delivery4));
	}
	
	@Test
	public void testUnload() {
		when(bagService.getBagByBarcode(deliveryWithBagBarcode1.getBarcode())).thenReturn(dBag1);
		when(bagService.getBagByBarcode(deliveryWithBagBarcode2.getBarcode())).thenReturn(dBag2);
		when(packageService.getPackagesByBagId(dBag2.getId())).thenReturn(Arrays.asList(dPackageForDelivery3));
		when(packageService.getPackageByBarcode(delivery2.getBarcode())).thenReturn(dPackageForDelivery2);
		when(packageService.getPackageByBarcode(delivery3.getBarcode())).thenReturn(dPackageForDelivery3);
		when(packageService.getPackageByBarcode(delivery4.getBarcode())).thenReturn(dPackageForDelivery4);
		when(packageService.getPackagesByBagId(dPackageForDelivery4.getId())).thenReturn(Arrays.asList(dPackageForDelivery4));
		when(incorrectSentLogService.insert(any(IncorrectSentLogRequestDto.class))).thenReturn(new IncorrectSentLogDto());
		when(packageService.update(anyString(), any(PackageRequestDto.class))).thenReturn(new PackageDto());
		assertNotNull(strategy.unload(deliveryList, 3L));
	}

}
