package com.fleetmanagement.shipping.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IncorrectSentLog extends BaseModel {

	@NotNull
	@Column(nullable = false)
	private UUID deliveryPointId;

	@NotNull
	@Column(nullable = false)
	private String barcode;

}
