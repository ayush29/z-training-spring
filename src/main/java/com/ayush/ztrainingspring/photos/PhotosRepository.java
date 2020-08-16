package com.ayush.ztrainingspring.photos;

import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import com.ayush.ztrainingspring.reviews.model.Review;
import com.ayush.ztrainingspring.user_auth.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PhotosRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findByRestaurantAndCategory(Restaurants restaurant, String category);
    public Photo findByIdEquals(int id);
    List<Photo> findByRestaurant(Restaurants restaurant);


}

