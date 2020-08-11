package com.ayush.ztrainingspring.photos;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
//import java.util.Calendar;
import java.sql.Timestamp;
import java.time.Instant;


import com.ayush.ztrainingspring.user_auth.User;


@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


    private int userID;


    private String category;

    private String link;

    private Timestamp timeAdded;

    private int likes;
    private int dislikes;


    public Photo() {
        timeAdded = Timestamp.from(Instant.now());
        likes = 0;
        dislikes = 0;
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



    public Integer getuserID() {
        return userID;
    }

    public String getCategory() {
        return category;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }

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

}