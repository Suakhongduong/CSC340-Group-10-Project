package com.homefix.backend_api.service;

import com.homefix.backend_api.entity.Customer;
import com.homefix.backend_api.entity.Review;
import com.homefix.backend_api.repository.CustomerRepository;
import com.homefix.backend_api.repository.ReviewRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    // These repositories help us work with the database
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;

    // Spring automatically connects our repositories here
    public ReviewService(
            ReviewRepository reviewRepository,
            CustomerRepository customerRepository) {

        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
    }

    // Get every review in the database
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Find a specific review using its ID
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    // Create a review for an existing customer
    public Optional<Review> createReview(Long customerId, Review review) {

        // First, check if the customer actually exists
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            // No customer found, so we can't save their review
            return Optional.empty();
        }

        // Connect this review to the customer
        review.setCustomer(customer.get());

        // Let PostgreSQL generate a new review ID
        review.setReviewId(null);

        // Save the review and return it
        return Optional.of(reviewRepository.save(review));
    }

    // Delete a review if it exists
    public boolean deleteReview(Long id) {

        // Check before trying to delete anything
        if (!reviewRepository.existsById(id)) {
            return false;
        }

        // Remove the review from the database
        reviewRepository.deleteById(id);
        return true;
    }

    
    // Update a review
    public Optional<Review> updateReview(
            Long id, Review updatedReview) {

        Optional<Review> existing =
                reviewRepository.findById(id);

        if (existing.isEmpty()) {
            return Optional.empty();
        }

        Review review = existing.get();

        if (updatedReview.getRating() >= 1
                && updatedReview.getRating() <= 5) {
            review.setRating(updatedReview.getRating());
        }

        if (updatedReview.getComment() != null) {
            review.setComment(updatedReview.getComment());
        }

        return Optional.of(reviewRepository.save(review));
    }

}

