package com.homefix.backend_api.controller;

import com.homefix.backend_api.entity.ServiceRequest;
import com.homefix.backend_api.service.ServiceRequestService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceRequestController {

    // Connect our controller to the service layer
    private final ServiceRequestService requestService;

    // Spring automatically provides the service
    public ServiceRequestController(ServiceRequestService requestService) {
        this.requestService = requestService;
    }

    // Get all service requests
    @GetMapping("/service-requests")
    public List<ServiceRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    // Find a specific request using its ID
    @GetMapping("/service-requests/{id}")
    public ResponseEntity<ServiceRequest> getRequestById(@PathVariable Long id) {

        // Return 200 if found, otherwise 404
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Let a customer submit a new service request
    @PostMapping("/customers/{customerId}/service-requests")
    public ResponseEntity<ServiceRequest> createRequest(
            @PathVariable Long customerId,
            @Valid @RequestBody ServiceRequest request) {

        // Save the request if the customer exists
        return requestService.createRequest(customerId, request)
                .map(saved -> ResponseEntity.status(201).body(saved))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a service request
    @DeleteMapping("/service-requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {

        // Return 204 if deleted, otherwise 404
        if (requestService.deleteRequest(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    
    // Update a service request
    @PutMapping("/service-requests/{id}")
    public ResponseEntity<ServiceRequest> updateRequest(
            @PathVariable Long id,
            @RequestBody ServiceRequest updatedRequest) {

        return requestService.updateRequest(id, updatedRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cancel a service request
    @PutMapping("/service-requests/{id}/cancel")
    public ResponseEntity<ServiceRequest> cancelRequest(
            @PathVariable Long id) {

        return requestService.cancelRequest(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

