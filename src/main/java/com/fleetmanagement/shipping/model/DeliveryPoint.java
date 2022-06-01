package com.fleetmanagement.shipping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeliveryPoint extends BaseModel {

	@NotNull
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "deliveryPoint", cascade = CascadeType.ALL)
    private Set<Bag> bags = new HashSet<>();

}
