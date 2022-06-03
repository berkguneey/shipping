package com.fleetmanagement.shipping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeliveryPoint extends BaseModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(nullable = false)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "deliveryPoint", cascade = CascadeType.ALL)
    private Set<Bag> bags = new HashSet<>();
	
	@OneToMany(mappedBy = "deliveryPoint", cascade = CascadeType.ALL)
    private Set<Package> packages = new HashSet<>();

}
