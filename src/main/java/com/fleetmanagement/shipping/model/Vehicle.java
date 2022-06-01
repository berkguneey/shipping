package com.fleetmanagement.shipping.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehicle extends BaseModel {

	@NotNull
	@Column(nullable = false)
	private String licensePlate;

	@NotNull
	@Column(nullable = false)
	private String model;

}
