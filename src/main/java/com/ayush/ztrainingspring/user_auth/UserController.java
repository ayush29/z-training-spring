package com.ayush.ztrainingspring.user_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody Map<String,String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = userService.login(email,password);
        if(user!=null)
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> signup(@RequestBody User user)
    {
        try{
            User newUser = userService.signup(user);
            return new ResponseEntity<>(newUser,HttpStatus.OK);
        }
        catch(Exception err)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //todo: can return specific errors like user already exist etc.
        }

    }
    @PutMapping("user/{id}/logout")
    public void logout(@PathVariable Integer id)
    {
        userService.logout(id);
    }

    @PostMapping("/user/details")
    public ResponseEntity<User> getUserDetails(@RequestBody Map<String,String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = userService.verifyUser(email,password);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/all")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }



    @PostMapping("/user/dummy-users")
    public ResponseEntity<List<User>> addDummyData()
    {
        try{
            userService.addNewUser(new User("Ayush Jain","ayush@xyz.com","9090909090","ayush123"));
            userService.addNewUser(new User("Zatin Meraz","zatin@xyz.com","9090909090","zatin123"));
            userService.addNewUser(new User("Sai Kamal","sai@xyz.com","9090909090", "sai123"));
            userService.addNewUser(new User("Naman","naman@xyz.com","9090909090","naman123"));
            userService.addNewUser(new User("Sidharth","sidharth@xyz.com","9090909090","sid123"));
            return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.CREATED);
        }
        catch (Exception err)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
