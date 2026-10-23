package com.homefix.backend_api.controller;


import com.homefix.backend_api.entity.Review;
import com.homefix.backend_api.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    // This connects our controller to the review service
    private final ReviewService reviewService;

    // Spring automatically gives us the service
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get every review saved in the database
    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Find one review using its ID
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {

        // Return 200 if we find it, or 404 if it doesn't exist
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Let an existing customer submit a review
    @PostMapping("/customers/{customerId}/reviews")
    public ResponseEntity<Review> createReview(
            @PathVariable Long customerId,
            @Valid @RequestBody Review review) {

        // Save the review and return 201 if successful
        // Return 404 if the customer doesn't exist
        return reviewService.createReview(customerId, review)
                .map(saved -> ResponseEntity.status(201).body(saved))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a review using its ID
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {

        // Return 204 when deleted, or 404 if it wasn't found
        if (reviewService.deleteReview(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    
    // Update an existing review
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestBody Review updatedReview) {

        return reviewService.updateReview(id, updatedReview)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

