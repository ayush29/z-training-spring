package com.ayush.ztrainingspring.order.menus;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =
"*")
@RestController
public class Menuapi {
    @Autowired
    Menurepo menurepo;

    @GetMapping("/api/menus")
    public List<Menus> dispMenu(){
        return menurepo.findAll();
    }

    @PostMapping(value="api/menus")
    public Menus registerMenu(@RequestBody Map<String, String> body) {
        return menurepo.save(new Menus(body));
    }
}