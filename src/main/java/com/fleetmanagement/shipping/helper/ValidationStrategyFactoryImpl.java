package com.fleetmanagement.shipping.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;

@Component
public class ValidationStrategyFactoryImpl implements ValidationStrategyFactory {

	private final BranchValidationStrategy branchValidationStrategy;
	private final DistributionCenterValidationStrategy distributionCenterValidationStrategy;
	private final TransferCenterValidationStrategy transferCenterValidationStrategy;

	@Autowired
	public ValidationStrategyFactoryImpl(BranchValidationStrategy branchValidationStrategy,
			DistributionCenterValidationStrategy distributionCenterValidationStrategy,
			TransferCenterValidationStrategy transferCenterValidationStrategy) {
		this.branchValidationStrategy = branchValidationStrategy;
		this.distributionCenterValidationStrategy = distributionCenterValidationStrategy;
		this.transferCenterValidationStrategy = transferCenterValidationStrategy;
	}

	@Override
	public DeliveryPointValidationStrategy getStrategy(String deliveryPointId) {
		switch (deliveryPointId) {
		case "1":
			return branchValidationStrategy;
		case "2":
			return distributionCenterValidationStrategy;
		case "3":
			return transferCenterValidationStrategy;
		default:
			throw new BusinessException(ErrorConstants.DELIVERY_POINT_INVALID);
		}
	}

}
