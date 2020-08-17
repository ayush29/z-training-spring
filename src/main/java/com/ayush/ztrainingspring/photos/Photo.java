package com.ayush.ztrainingspring.photos;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
//import java.util.Calendar;
import java.sql.Timestamp;
import java.time.Instant;


import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.order.restaurants.Restaurants;


@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "restID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurants restaurant;


//    private int userID;
//    private int restID;


    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private Timestamp timeAdded;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int dislikes;



    public Photo() {
//        timeAdded = Timestamp.from(Instant.now());
    }

    public Photo(Photo photo) {
        id = photo.id;
        user = photo.user;
        restaurant = photo.restaurant;
        category = photo.category;
        link = photo.link;
        timeAdded = photo.timeAdded;
        likes = photo.likes;
        dislikes = photo.dislikes;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void IncLikes() {
        this.likes = this.likes  + 1;
    }

    public void IncDislikes() {
        this.dislikes = this.dislikes  + 1;
    }



    public Integer getuserID() {
        return user.getId();
    }

    public String getCategory() {
        return category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public void setUserID(int userID) {
//        this.user.setId(userID);
//    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }



}