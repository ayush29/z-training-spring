package com.ayush.ztrainingspring.user_auth;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    private static ArrayList<User> userRecords = new ArrayList<User>();

    static {
        userRecords.add(new User("Ayush Jain","ayush@xyz.com","9090909090","ayush123"));
        userRecords.add(new User("Zatin Meraz","zatin@xyz.com","9090909090","zatin123"));
        userRecords.add(new User("Sai Kamal","sai@xyz.com","9090909090", "sai123"));
        userRecords.add(new User("Naman","naman@xyz.com","9090909090","naman123"));
        userRecords.add(new User("Sidharth","sidharth@xyz.com","9090909090","sid123"));
        //load user records from storage
    }

    public User addNewUser(String name, String email, String phone, String password)
    {
        User user = new User(name, email, phone,password);
        userRecords.add(user);
        //save new user in storage
        return user;
    }
    public User addNewUser(User user)
    {
        userRecords.add(user);
        //save user in storage
        return getUserDetails(user);
    }
    public User verifyUser(String email,String password)
    {
        User user = findUserByEmail(email);
        if(user!=null && user.getPassword().equals(password))
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
        for( User user: userRecords)
        {
            if(user.getEmail().equals(email))
            {
                return user;
            }
        }
        return null;
    }
    public String encryptPassword(String password)
    {
        return password;
    }
    public User getUserDetails(User user)
    {
        User userDetails = new User(user.getName(),user.getEmail(),user.getPhone(),"");
        return userDetails;
    }

    public ArrayList<User> getUserRecords() {
        return userRecords;
    }
}
