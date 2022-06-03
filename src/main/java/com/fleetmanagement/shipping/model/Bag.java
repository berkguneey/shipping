package com.fleetmanagement.shipping.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;

import com.fleetmanagement.shipping.constant.BagStatus;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bag extends BaseModel {
	
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
	
	@Column(nullable = false)
	private Integer state;
	
	@OneToMany(mappedBy = "bag", cascade = CascadeType.ALL)
    private Set<Package> packages = new HashSet<>();
	
	@PrePersist
	protected void onCreate() {
		super.onCreate();
		state = BagStatus.CREATED.getState();
	}

}
