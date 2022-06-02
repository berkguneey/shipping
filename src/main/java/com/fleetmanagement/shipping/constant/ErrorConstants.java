package com.fleetmanagement.shipping.constant;

public enum ErrorConstants {
	
	VEHICLE_NOT_FOUND(1001L, "Vehicle not found. "),
	VEHICLE_ALREADY_EXISTS(1002L, "Vehicle already exists."),
	DELIVERY_POINT_NOT_FOUND(1003L, "Delivery point not found."),
	DELIVERY_POINT_ALREADY_EXISTS(1004L, "Delivery point already exists."),
	BAG_NOT_FOUND(1005L, "Bag not found."),
	BAG_ALREADY_EXISTS(1006L, "Bag already exists."),
	PACKAGE_NOT_FOUND(1007L, "Package not found."),
	PACKAGE_ALREADY_EXISTS(1008L, "Package already exists."),
	BAG_BARCODE_FORMAT_INVALID(1009L, "Bag barcode format is incorrect."),
	LICENSE_PLATE_FORMAT_INVALID(1010L, "License plate format is incorrect."),
	PACKAGE_BARCODE_FORMAT_INVALID(1011L, "Package barcode format is incorrect."),
	DELIVERY_POINT_DISMATCH(1012L, "The package loaded into a bag must have the same delivery point as the bag.");
	
	private Long code;
	private String message;
	
	private ErrorConstants(Long code, String message) {
		this.code = code;
		this.message = message;
	}

	public Long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
