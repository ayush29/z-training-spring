package com.ayush.ztrainingspring.order.users;

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
public class Userapi{
    @Autowired
    Userrepo userrepo;

    @GetMapping("/api/users")
    public List<Users> dispUsers(){
        return userrepo.findAll();
    }
    @PostMapping(value="api/users")
    public Users registerUsers(@RequestBody Map<String, String> body) {
        return userrepo.save(new Users(body));
    }
}