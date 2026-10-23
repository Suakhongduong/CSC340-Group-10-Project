package com.homefix.backend_api.controller;


import com.homefix.backend_api.entity.Subscription;
import com.homefix.backend_api.service.SubscriptionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    // This service handles all our subscription database logic
    private final SubscriptionService subscriptionService;

    // Spring gives us the service automatically
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // Get every subscription in the database
    @GetMapping("/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    // Get one subscription using its ID
    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {

        // Return 200 if found, or 404 if the ID doesn't exist
        return subscriptionService.getSubscriptionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a subscription for a specific customer
    @PostMapping("/customers/{customerId}/subscriptions")
    public ResponseEntity<Subscription> createSubscription(
            @PathVariable Long customerId,
            @RequestBody Subscription subscription) {

        // Return 201 when saved, or 404 if the customer doesn't exist
        return subscriptionService.createSubscription(customerId, subscription)
                .map(saved -> ResponseEntity.status(201).body(saved))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a subscription using its ID
    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {

        // Return 204 if deleted, or 404 if nothing was found
        if (subscriptionService.deleteSubscription(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

