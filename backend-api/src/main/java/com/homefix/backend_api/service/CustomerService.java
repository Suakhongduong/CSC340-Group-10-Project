package com.homefix.backend_api.service;

import com.homefix.backend_api.entity.Customer;
import com.homefix.backend_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// This tells Spring that this class handles our customer logic spring automatically manages this class so other parts of
// our application can use it.
@Service
public class CustomerService {

    // This is our connection to the Customer repository.
    // The repository handles talking to the Neon database.
    private final CustomerRepository customerRepository;

    // Spring automatically gives us the repository when
    // it creates this service. This is called dependency injection.
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get every customer currently saved in the database.
    // findAll() is already provided by JpaRepository.
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Find one customer using their unique customer ID.
    // Optional means the customer might exist or might not.
    // This lets us handle missing customers safely.
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Save a new customer to the database.
    // The repository handles the SQL behind the scenes.
    public Customer createCustomer(Customer customer) {
        customer.setCustomerId(null);
        return customerRepository.save(customer);
    }

    // Update an existing customer's profile information.
    // First we check whether the customer exists.
    // If they don't, we return an empty Optional.
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {

        return customerRepository.findById(id).map(customer -> {

            // Replace the old profile information with
            // the new information from the request.
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            customer.setAddress(updatedCustomer.getAddress());

            // Save the updated customer back to Neon.
            return customerRepository.save(customer);
        });
    }

    // Delete a customer using their ID.
    // Return true if we found and deleted the customer.
    // Return false if that customer ID doesn't exist.
    public boolean deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            return false;
        }

        customerRepository.deleteById(id);
        return true;
    }
}

