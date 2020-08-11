package com.ayush.ztrainingspring.order.cart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins=
"*")
@RestController
public class Orderapi {
    @Autowired
    CartRepo cartrepo;

    @GetMapping("/api/cart")
    public List<Cart> displayCart(){
        return cartrepo.findAll();
    }

    @PostMapping(value="api/cart")
    public Cart addTOCart(@RequestBody Map<String, String> body) {
        return cartrepo.save(new Cart(body));
    }

    // @GetMapping(value="api/cart/{id}")
    // public SomeData getMethodName(@RequestParam String param) {
    //     return new SomeData();
    // }
    
    
}
