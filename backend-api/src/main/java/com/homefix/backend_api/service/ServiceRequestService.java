package com.homefix.backend_api.service;

import com.homefix.backend_api.entity.Customer;
import com.homefix.backend_api.entity.ServiceRequest;
import com.homefix.backend_api.repository.CustomerRepository;
import com.homefix.backend_api.repository.ServiceRequestRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {

    // These repositories help us communicate with the database
    private final ServiceRequestRepository requestRepository;
    private final CustomerRepository customerRepository;

    // Spring automatically connects our repositories
    public ServiceRequestService(
            ServiceRequestRepository requestRepository,
            CustomerRepository customerRepository) {

        this.requestRepository = requestRepository;
        this.customerRepository = customerRepository;
    }

    // Get every service request
    public List<ServiceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // Find one service request using its ID
    public Optional<ServiceRequest> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    // Let a customer create a service request
    public Optional<ServiceRequest> createRequest(
            Long customerId, ServiceRequest request) {

        // First, check if the customer exists
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            // Can't create a request without a valid customer
            return Optional.empty();
        }

        // Connect the request to the customer
        request.setCustomer(customer.get());

        // Let PostgreSQL generate the request ID
        request.setRequestId(null);

        // Every new request starts as pending
        request.setStatus("PENDING");

        // Save the request to Neon
        return Optional.of(requestRepository.save(request));
    }

    // Delete a service request
    public boolean deleteRequest(Long id) {

        // Make sure the request exists before deleting
        if (!requestRepository.existsById(id)) {
            return false;
        }

        // Remove the request from the database
        requestRepository.deleteById(id);
        return true;
    }

    
    // Update a service request
    public Optional<ServiceRequest> updateRequest(
            Long id, ServiceRequest updatedRequest) {

        Optional<ServiceRequest> existing =
                requestRepository.findById(id);

        if (existing.isEmpty()) {
            return Optional.empty();
        }

        ServiceRequest request = existing.get();

        if (updatedRequest.getDescription() != null) {
            request.setDescription(updatedRequest.getDescription());
        }

        if (updatedRequest.getAddress() != null) {
            request.setAddress(updatedRequest.getAddress());
        }

        if (updatedRequest.getNotes() != null) {
            request.setNotes(updatedRequest.getNotes());
        }

        return Optional.of(requestRepository.save(request));
    }

    // Cancel a request without deleting it
    public Optional<ServiceRequest> cancelRequest(Long id) {

        Optional<ServiceRequest> existing =
                requestRepository.findById(id);

        if (existing.isEmpty()) {
            return Optional.empty();
        }

        ServiceRequest request = existing.get();
        request.setStatus("CANCELLED");

        return Optional.of(requestRepository.save(request));
    }



}
