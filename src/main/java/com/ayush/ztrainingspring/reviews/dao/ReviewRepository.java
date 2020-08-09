package com.ayush.ztrainingspring.reviews.dao;

import com.ayush.ztrainingspring.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
