package com.fleetmanagement.shipping.model;

import java.time.LocalDateTime;

import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;

	public ErrorResponse(RuntimeException ex, WebRequest request) {
		timestamp = LocalDateTime.now();
		message = ex.getMessage();
	}

}
