package com.homefix.backend_api.service;


import com.homefix.backend_api.entity.Customer;
import com.homefix.backend_api.entity.Subscription;
import com.homefix.backend_api.repository.CustomerRepository;
import com.homefix.backend_api.repository.SubscriptionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    // These repositories let us talk to the database
    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;

    // Spring automatically gives us the repositories when this service starts
    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            CustomerRepository customerRepository) {

        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
    }

    // Get every subscription saved in the database
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // Find one subscription using its ID
    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    // Create a subscription for a customer who already exists
    public Optional<Subscription> createSubscription(
            Long customerId, Subscription subscription) {

        // First, check if this customer actually exists
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            // No customer found, so we can't create the subscription
            return Optional.empty();
        }

        // Connect the subscription to the customer we found
        subscription.setCustomer(customer.get());

        // Let the database generate the subscription ID
        subscription.setSubscriptionId(null);

        // Save it to PostgreSQL and return the new subscription
        return Optional.of(subscriptionRepository.save(subscription));
    }

    // Delete a subscription if it exists
    public boolean deleteSubscription(Long id) {

        // Don't try to delete something that isn't there
        if (!subscriptionRepository.existsById(id)) {
            return false;
        }

        subscriptionRepository.deleteById(id);
        return true;
    }
}

