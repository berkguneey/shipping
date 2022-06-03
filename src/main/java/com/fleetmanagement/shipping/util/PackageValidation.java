package com.fleetmanagement.shipping.util;

import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.model.Bag;
import com.fleetmanagement.shipping.model.Package;

public class PackageValidation {
	
	public static void haveSameDeliveryPoint(Package mPackage, Bag mBag) {
		if (!mPackage.getDeliveryPoint().getId().equals(mBag.getDeliveryPoint().getId())) {
			throw new BusinessException(ErrorConstants.DELIVERY_POINT_DISMATCH);
		}
	}
	
	public static void isValid(String value) {
		if (ObjectUtils.isEmpty(value) || !value.startsWith("P") || value.length() != 11) {
			throw new BusinessException(ErrorConstants.PACKAGE_BARCODE_FORMAT_INVALID);
		}
		
	}

}
