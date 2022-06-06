package com.fleetmanagement.shipping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeliveryPoint extends BaseModel {

	@Id
	@GeneratedValue(generator = "custom-generator")
	@GenericGenerator(name = "custom-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "ID_SEQ"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
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
