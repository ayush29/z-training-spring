package com.ayush.ztrainingspring.order.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Restaurantrepo extends JpaRepository<Restaurants, Integer>{
}