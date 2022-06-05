package com.fleetmanagement.shipping.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.fleetmanagement.shipping.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ValidationStrategyFactoryTest {

	@Mock
	BranchValidationStrategy branchValidationStrategy;
	@Mock
	DistributionCenterValidationStrategy distributionCenterValidationStrategy;
	@Mock
	TransferCenterValidationStrategy transferCenterValidationStrategy;
	
	List<DeliveryPointValidationStrategy> strategies;
	ValidationStrategyFactoryImpl factory;
	
	@BeforeEach
	public void setUp() {
		strategies = new ArrayList<>();
		strategies.add(branchValidationStrategy);
		strategies.add(distributionCenterValidationStrategy);
		strategies.add(transferCenterValidationStrategy);
		factory = new ValidationStrategyFactoryImpl(strategies);
	}
	
	@Test
	public void testGetStrategy() {
		assertEquals(branchValidationStrategy, factory.getStrategy(1L));
		assertEquals(distributionCenterValidationStrategy, factory.getStrategy(2L));
		assertEquals(transferCenterValidationStrategy, factory.getStrategy(3L));
		assertThrows(BusinessException.class, () -> factory.getStrategy(4L));
	}

}
