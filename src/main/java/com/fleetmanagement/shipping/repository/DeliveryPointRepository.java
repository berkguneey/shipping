package com.fleetmanagement.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.DeliveryPoint;

@Repository
public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, Long> {
	boolean existsDeliveryPointByName(String name);
}
