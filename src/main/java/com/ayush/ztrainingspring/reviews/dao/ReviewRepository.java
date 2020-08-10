package com.ayush.ztrainingspring.reviews.dao;

import com.ayush.ztrainingspring.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByOrderByCreatedTimeAsc();
    List<Review> findByOrderByCreatedTimeDesc();
    List<Review> findByOrderByRatingAscCreatedTimeDesc();
    List<Review> findByOrderByRatingDescCreatedTimeDesc();
}
