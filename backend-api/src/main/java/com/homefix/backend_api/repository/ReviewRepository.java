package com.homefix.backend_api.repository;


import com.homefix.backend_api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

// This repository handles our review database operations
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Spring already gives us save(), findAll(), findById(), and deleteById()
    // So we don't need to write those methods ourselves!

}
