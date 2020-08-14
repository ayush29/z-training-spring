package com.ayush.ztrainingspring.order.restaurants;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =
"*")
@RestController
public class Restaurantapi {
    @Autowired
    Restaurantrepo restaurantrepo;

    @GetMapping("/api/restaurants")
    public List<Restaurants> dispRestaurants(){
        return restaurantrepo.findAll();
    }

    @GetMapping("/api/restaurants/{id}")
    public Restaurants getrest(@PathVariable("id") int id){
        return restaurantrepo.findrestById(id);
    }

    @PostMapping(value="api/restaurants")
    public Restaurants registerRestaurants(@RequestBody Map<String, String> body) {
        return restaurantrepo.save(new Restaurants(body));
    }
}