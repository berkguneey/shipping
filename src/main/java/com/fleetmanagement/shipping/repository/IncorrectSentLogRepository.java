package com.fleetmanagement.shipping.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleetmanagement.shipping.model.IncorrectSentLog;

@Repository
public interface IncorrectSentLogRepository extends JpaRepository<IncorrectSentLog, UUID> {
	
}
