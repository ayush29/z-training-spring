package com.ayush.ztrainingspring.user_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    public void setUserRepository(UserRepository userRepository)
//    {
//        UserService.userRepository = userRepository;
//    }

//    private static ArrayList<User> userRecords = new ArrayList<User>();

//    static {
////        userRecords.add(new User("Ayush Jain","ayush@xyz.com","9090909090","ayush123"));
////        userRecords.add(new User("Zatin Meraz","zatin@xyz.com","9090909090","zatin123"));
////        userRecords.add(new User("Sai Kamal","sai@xyz.com","9090909090", "sai123"));
////        userRecords.add(new User("Naman","naman@xyz.com","9090909090","naman123"));
////        userRecords.add(new User("Sidharth","sidharth@xyz.com","9090909090","sid123"));
//        //load user records from storage
//        userRepository.save(new User("Ayush Jain","ayush@xyz.com","9090909090","ayush123"));
//        userRepository.save(new User("Zatin Meraz","zatin@xyz.com","9090909090","zatin123"));
//        userRepository.save(new User("Sai Kamal","sai@xyz.com","9090909090", "sai123"));
//        userRepository.save(new User("Naman","naman@xyz.com","9090909090","naman123"));
//        userRepository.save(new User("Sidharth","sidharth@xyz.com","9090909090","sid123"));
//    }

    public User addNewUser(String name, String email, String phone, String password)
    {
        User user = new User(name, email, phone, generateHash(password));
//        userRecords.add(user);
        //save new user in storage
        userRepository.save(user);
        return user;
    }
    public User addNewUser(User user)
    {
//        userRecords.add(user);
        //save user in storage
        user.setPassword(generateHash(user.getPassword()));
        userRepository.save(user);
        return getUserDetails(user);
    }
    public User verifyUser(String email,String password)
    {
        User user = findUserByEmail(email);
        if(user!=null && user.getPassword().equals(generateHash(password)))
        {
            return getUserDetails(user);
        }
        else
        {
            return null;
        }
    }
    public User findUserByEmail(String email)
    {
//        for( User user: userRecords)
//        {
//            if(user.getEmail().equals(email))
//            {
//                return user;
//            }
//        }
//        return null;
        User user = userRepository.findByEmail(email);
        return user;
    }
    public String generateHash(String password)
    {
        //adding salt and computing hashcode
        final String SALT = "#0*5#&";
        String str = SALT + password;
        return Integer.toString(str.hashCode());
    }

    public User getUserDetails(User user)
    {

        User userDetails = new User();
        //hiding sensitive details like password
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setName(user.getName());
        userDetails.setPhone(user.getPhone());
        userDetails.setPassword("");
        return userDetails;
    }

    public List<User> getAllUsers() {
//        ArrayList<User> allUsers = new ArrayList<User>();
        List<User> allUsers = new ArrayList<User>();
        for(User user :userRepository.findAll())
        {
            allUsers.add(getUserDetails(user));
        }
        return allUsers;
    }
}