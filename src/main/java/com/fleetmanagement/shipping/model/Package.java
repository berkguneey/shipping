package com.fleetmanagement.shipping.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;

import com.fleetmanagement.shipping.constant.BagStatus;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Package extends BaseModel {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@NotNull
	@Column(nullable = false, columnDefinition = "BINARY(16)")
	private UUID id;

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
