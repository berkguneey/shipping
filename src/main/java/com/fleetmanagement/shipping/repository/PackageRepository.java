package com.fleetmanagement.shipping.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID> {
	Optional<Package> findPackageByBarcode(String barcode);
	
	List<Package> findPackagesByBagId(UUID bagId);

	@Transactional
	Long deletePackageByBarcode(String barcode);

	boolean existsPackageByBarcode(String barcode);
}
