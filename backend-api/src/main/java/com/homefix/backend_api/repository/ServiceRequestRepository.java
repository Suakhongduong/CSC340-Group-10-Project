package com.homefix.backend_api.repository;

import com.homefix.backend_api.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

// Handles database operations for service requests
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

}

