package com.fleetmanagement.shipping.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IncorrectSentLog extends BaseModel {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@NotNull
	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column(nullable = false)
	private Long deliveryPointId;

	@NotNull
	@Column(nullable = false)
	private String barcode;
	
	@NotNull
	@Column(nullable = false)
	private String message;

}
