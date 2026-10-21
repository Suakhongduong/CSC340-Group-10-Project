package com.homefix.backend_api.controller;

import com.homefix.backend_api.entity.Customer;
import com.homefix.backend_api.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This tells Spring Boot that this class handles REST API requests.
// When someone sends a request to our backend, the controller
// decides which service method should handle it.
@RestController

// All the endpoints in this class will start with /api/customers.
// For example: http://localhost:8080/api/customers
@RequestMapping("/api/customers")
public class CustomerController {

    // We're using the CustomerService to handle the actual logic.
    // The controller shouldn't communicate directly with Neon.
    private final CustomerService customerService;

    // Spring automatically provides the CustomerService when
    // it creates this controller. This is dependency injection.
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET /api/customers
    // This endpoint returns a list of every customer in the database.
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // GET /api/customers/1
    // This endpoint finds one customer using their ID.
    // If the customer exists, return 200 OK with their information.
    // If they don't exist, return 404 Not Found.
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/customers
    // This endpoint creates a new customer.
    // @RequestBody turns the JSON sent by the frontend
    // into a Customer object that Java can work with.
    // Return 201 Created when the customer is saved.
    
    // Creates a new customer.
    // @Valid checks the customer's information before saving.
    // If something is missing or invalid, Spring returns 400.
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Valid @RequestBody Customer customer) {

        Customer savedCustomer = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }


    
    // Updates an existing customer's information.
    // @Valid makes sure the updated information follows
    // the validation rules inside Customer.java.
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody Customer customer) {

        return customerService.updateCustomer(id, customer)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // DELETE /api/customers/1
    // This endpoint deletes a customer using their ID.
    // Return 204 No Content if the deletion succeeds.
    // Return 404 Not Found if the customer doesn't exist.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        boolean deleted = customerService.deleteCustomer(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
