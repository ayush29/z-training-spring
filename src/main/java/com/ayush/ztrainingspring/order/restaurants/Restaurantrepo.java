package com.ayush.ztrainingspring.order.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Restaurantrepo extends JpaRepository<Restaurants, Integer>{
    @Query(value = "select * from restaurants where id = ?1", nativeQuery = true)
    Restaurants findrestById(int rid);
}