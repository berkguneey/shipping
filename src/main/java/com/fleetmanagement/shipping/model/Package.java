package com.fleetmanagement.shipping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fleetmanagement.shipping.constant.BagStatus;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Package extends BaseModel {

	@NotNull
	@Column(nullable = false)
	private String barcode;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "deliveryPointId")
	private DeliveryPoint deliveryPoint;
	
	@ManyToOne()
	@JoinColumn(name = "bagId")
	private Bag bag;
	
	@Column(nullable = false)
	private Integer state;
	
	@NotNull
	@Column(nullable = false)
	private Integer weight;
	
	@PrePersist
	protected void onCreate() {
		super.onCreate();
		state = BagStatus.CREATED.getState();
	}

}
