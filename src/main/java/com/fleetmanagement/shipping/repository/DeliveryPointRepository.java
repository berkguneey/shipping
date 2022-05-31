package com.fleetmanagement.shipping.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.DeliveryPoint;

@Repository
public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, UUID> {
	DeliveryPoint findDeliveryPointByValue(Integer value);
	String deleteDeliveryPointByValue(Integer value);
}
