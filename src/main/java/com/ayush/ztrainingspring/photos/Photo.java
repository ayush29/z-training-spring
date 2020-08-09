package com.ayush.ztrainingspring.photos;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

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

    private Calendar timeAdded;

    public Photo() {
    }

    public Integer getId() {
        return id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}