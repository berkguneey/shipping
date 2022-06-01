package com.fleetmanagement.shipping.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.DeliveryPoint;

@Repository
public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, UUID> {
	Optional<DeliveryPoint> findDeliveryPointByPoint(Integer point);
	Integer deleteDeliveryPointByPoint(Integer point);
}
