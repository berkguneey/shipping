package com.fleetmanagement.shipping.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.Bag;

@Repository
public interface BagRepository extends JpaRepository<Bag, UUID> {
	Optional<Bag> findBagByBarcode(String barcode);

	@Transactional
	Long deleteBagByBarcode(String barcode);

	boolean existsBagByBarcode(String barcode);
}
