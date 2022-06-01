package com.fleetmanagement.shipping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bag extends BaseModel {

	@NotNull
	@Column(nullable = false)
	private String barcode;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "point")
	private DeliveryPoint deliveryPoint;

}
