package com.fleetmanagement.shipping.model;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fleetmanagement.shipping.exception.BusinessException;

import lombok.Data;

@Data
public class ErrorResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	private Long code;
	private String message;
	private StringBuffer path;

	public ErrorResponse(BusinessException ex, HttpServletRequest request) {
		timestamp = LocalDateTime.now();
		code = ex.getError().getCode();
		message = ex.getError().getMessage();
		path = request.getRequestURL();
	}

}
