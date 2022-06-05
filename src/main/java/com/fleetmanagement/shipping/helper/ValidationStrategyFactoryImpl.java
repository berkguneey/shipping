package com.fleetmanagement.shipping.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;

@Component
public class ValidationStrategyFactoryImpl implements ValidationStrategyFactory {
		
	private static final Map<Long, DeliveryPointValidationStrategy> strategiesCache = new HashMap<>();

	@Autowired
	public ValidationStrategyFactoryImpl(List<DeliveryPointValidationStrategy> strategies) {
		Long index = 1L;
		for(DeliveryPointValidationStrategy strategy : strategies) {
			strategiesCache.put(index, strategy);
			index++;
		}
	}

	@Override
	public DeliveryPointValidationStrategy getStrategy(Long deliveryPointId) {
		DeliveryPointValidationStrategy strategy = strategiesCache.get(deliveryPointId);
		if (ObjectUtils.isEmpty(strategy)) throw new BusinessException(ErrorConstants.DELIVERY_POINT_INVALID);
		return strategy;
	}

}
