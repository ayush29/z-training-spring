package com.ayush.ztrainingspring.user_auth;

import com.ayush.ztrainingspring.order.restaurants.Restaurantrepo;
import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        return getUserDetails(user);
        return user;
    }

    public User login(String email, String password)
    {
        User user = verifyUser(email, password);
        if(user!=null){
            user.setLoggedIn(true);
            userRepository.save(user);
        }
        return user;
    }
    public void logout(Integer id)
    {
        User user = userRepository.findById(id).orElse(null);
        if(user!=null)
        {
            user.setLoggedIn(false);
            userRepository.save(user);
        }
    }
    public User signup(User user)
    {
        user.setLoggedIn(true);
        return addNewUser(user);
    }

    public Boolean isUserOnline(Integer id)
    {
        User user = userRepository.findById(id).orElse(null);
        return user != null && user.getLoggedIn();
    }
    public User verifyUser(String email,String password)
    {
        User user = findUserByEmail(email);
        if(user!=null && user.getPassword().equals(generateHash(password)))
        {
//            return getUserDetails(user);
            return user;
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

//    public User getUserDetails(User user)
//    {
//
//        User userDetails = new User();
//        //hiding sensitive details like password
//        userDetails.setId(user.getId());
//        userDetails.setEmail(user.getEmail());
//        userDetails.setName(user.getName());
//        userDetails.setPhone(user.getPhone());
//        userDetails.setImgUrl(user.getImgUrl());
//        userDetails.setPassword("");
//        userDetails.setLoggedIn(user.getLoggedIn());
//        return userDetails;
//    }

    public List<User> getAllUsers() {
//        ArrayList<User> allUsers = new ArrayList<User>();
//        List<User> allUsers = new ArrayList<User>();
//        for(User user :userRepository.findAll())
//        {
//            allUsers.add(getUserDetails(user));
//        }
//        return allUsers;
        return userRepository.findAll();
    }
//
//    public void deleteUser(Integer id)
//    {
//        userRepository.deleteById(id);
//    }

    //methods for adding , deleting, finding bookmarked restraunts

    @Autowired
    private BookmarkRepository bookmarkRepository;


    @Autowired
    private Restaurantrepo restaurantrepo;

    public Boolean addBookmark(Integer userID, Integer restroId)
    {
        User user = userRepository.findById(userID).orElse(null);
        Restaurants restro = restaurantrepo.findById(restroId).orElse(null);
        if(user!=null && restro!=null)
        {
            UserRestaurantBookmark bookmark = new UserRestaurantBookmark(user,restro);
            bookmarkRepository.save(bookmark);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void removeBookmark(Integer userId, Integer restroId)
    {
        BookmarkId bookmarkId = new BookmarkId(userId,restroId);
        bookmarkRepository.deleteById(bookmarkId);
    }

    public Boolean findBookmark(Integer userId, Integer restroId)
    {
        BookmarkId bookmarkId = new BookmarkId(userId,restroId);
        return bookmarkRepository.existsById(bookmarkId);
    }

}
