package com.ayush.ztrainingspring.reviews.dao;

import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import com.ayush.ztrainingspring.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByOrderByCreatedTimeAsc();
    List<Review> findByOrderByCreatedTimeDesc();
    List<Review> findByOrderByRatingAscCreatedTimeDesc();
    List<Review> findByOrderByRatingDescCreatedTimeDesc();
    Optional<List<Review>> findByUserId(int userId);
    List<Review> findByRestaurantOrderByCreatedTimeAsc(Restaurants restaurant);
}
