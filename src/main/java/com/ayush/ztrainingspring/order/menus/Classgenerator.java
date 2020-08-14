package com.ayush.ztrainingspring.order.menus;

import java.util.Map;

import com.ayush.ztrainingspring.order.restaurants.Restaurantrepo;
import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import com.ayush.ztrainingspring.user_auth.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Classgenerator {
    @Autowired
    Restaurantrepo rrepo;
    @Autowired
    UserRepository userrepo;

    Classgenerator(){
    }

    Menus conv(Map<String, String> body){
        String item_name = body.get("item_name");
        String item_img = body.get("item_img");
        int item_cost = Integer.parseInt(body.get("item_cost"));
        int rid = Integer.parseInt(body.get("rid"));
        Restaurants ridd = rrepo.findById(rid)
            .orElseThrow(
                () -> new RuntimeException("unable to find restaurant with id: " + rid)
            );
        String item_category = body.get("item_category");
        String descp = body.get("descp");
        Menus ret =  new Menus(item_name, item_img, item_cost, item_category, descp, ridd);
        return ret;
    }
}