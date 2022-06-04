package com.fleetmanagement.shipping.util;

import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.CommonConstants;
import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.model.Bag;
import com.fleetmanagement.shipping.model.Package;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PackageValidation {

	public static void haveSameDeliveryPoint(Package mPackage, Bag mBag) {
		if (!mPackage.getDeliveryPoint().getId().equals(mBag.getDeliveryPoint().getId())) {
			throw new BusinessException(ErrorConstants.DELIVERY_POINT_DISMATCH);
		}
	}

	public static void checkValid(String value) {
		if (ObjectUtils.isEmpty(value) || !value.startsWith(CommonConstants.PACKAGE_FIRST_CHR) || value.length() != 11) {
			log.error(ErrorConstants.PACKAGE_BARCODE_FORMAT_INVALID.getMessage() + " Barcode is " + value);
			throw new BusinessException(ErrorConstants.PACKAGE_BARCODE_FORMAT_INVALID);
		}
	}

	public static boolean isValid(String value) {
		if (!ObjectUtils.isEmpty(value) && value.startsWith(CommonConstants.PACKAGE_FIRST_CHR) && value.length() == 11) {
			return true;
		}
		return false;
	}

}
