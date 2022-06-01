package com.fleetmanagement.shipping.exception;

public class AlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5966584640501810871L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}
