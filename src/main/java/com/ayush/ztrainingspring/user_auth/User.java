package com.ayush.ztrainingspring.user_auth;

//import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(min = 6,max = 15)
    private String password;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @Size(max = 15)
    private String phone;

//    @Column(columnDefinition = "varchar(255) default 'https://b.zmtcdn.com/images/user_avatars/wine_2x.png'")
    @NotNull
    private String imgUrl = "https://b.zmtcdn.com/images/user_avatars/wine_2x.png";

    @NotNull
    private Boolean loggedIn = false;

    @CreationTimestamp
    private Calendar createdOn;

    @UpdateTimestamp
    private Calendar updatedOn;

//    private ArrayList<String> bookmarks;
//    public User(String username, String password)
//    {
//        this.username = username;
//        this.password = password;
//    }
    public User()
    {

    }

    public User(String name, String email, String phone, String password)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
