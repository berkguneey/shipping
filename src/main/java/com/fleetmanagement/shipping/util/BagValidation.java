package com.fleetmanagement.shipping.util;

import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;

public class BagValidation {
	
	public static void isValid(String value) {
		if (ObjectUtils.isEmpty(value) || !value.startsWith("C") || value.length() != 7) {
			throw new BusinessException(ErrorConstants.BAG_BARCODE_FORMAT_INVALID);
		}
		
	}

}
