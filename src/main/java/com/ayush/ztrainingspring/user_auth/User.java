package com.ayush.ztrainingspring.user_auth;

//import java.util.ArrayList;

public class User {
    private String name;
    private String password;
    private String email;
    private String phone;
//    private ArrayList<String> bookmarks;
//    public User(String username, String password)
//    {
//        this.username = username;
//        this.password = password;
//    }
    public User(String name, String email, String phone, String password)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }
}
