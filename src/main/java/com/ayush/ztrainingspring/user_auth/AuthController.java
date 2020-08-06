package com.ayush.ztrainingspring.user_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody Map<String,String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = authService.verifyUser(email,password);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/signup")
    public User signup(@RequestBody User user)
    {
        User newUser = authService.addNewUser(user);
        return newUser;
    }

    @PostMapping("/user/details")
    public ResponseEntity<User> getUserDetails(@RequestBody Map<String,String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = authService.verifyUser(email,password);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
