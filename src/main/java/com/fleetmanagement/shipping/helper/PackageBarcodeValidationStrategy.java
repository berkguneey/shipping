package com.fleetmanagement.shipping.helper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;

@Component
@Qualifier("PackageBarcodeValidation")
public class PackageBarcodeValidationStrategy implements ValidationStrategy {

	@Override
	public void validate(String value) {
		if (ObjectUtils.isEmpty(value) || !value.startsWith("P") || value.length() != 11) {
			throw new BusinessException(ErrorConstants.PACKAGE_BARCODE_FORMAT_INVALID);
		}
		
	}

}
