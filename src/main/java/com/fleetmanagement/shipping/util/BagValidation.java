package com.fleetmanagement.shipping.util;

import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.CommonConstants;
import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;

public class BagValidation {
	
	public static void checkValid(String value) {
		if (ObjectUtils.isEmpty(value) || !value.startsWith(CommonConstants.BAG_FIRST_CHR) || value.length() != 7) {
			throw new BusinessException(ErrorConstants.BAG_BARCODE_FORMAT_INVALID);
		}
	}
	
	public static boolean isValid(String value) {
		if (!ObjectUtils.isEmpty(value) && value.startsWith(CommonConstants.BAG_FIRST_CHR) && value.length() == 7) {
			return true;
		}
		return false;
	}

}
