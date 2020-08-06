package com.ayush.ztrainingspring.reviews.model;

import com.ayush.ztrainingspring.user_auth.User;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int rating;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes;

    @Lob
    private String text;

    @Column(nullable = false)
    private Calendar createdTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReviewTag> reviewTags;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Review() {}

//    public Review(@JsonProperty("reviewRatingNum") int reviewRatingNum, @JsonProperty("reviewText") String reviewText) throws MalformedURLException {
//        this.reviewRatingNum = reviewRatingNum;
//        this.reviewNumLikes = 0;
//        this.reviewText = reviewText;
//        this.reviewRatingTime = Calendar.getInstance();
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ReviewTag> getReviewTags() {
        return reviewTags;
    }

    public void setReviewTags(List<ReviewTag> reviewTags) {
        this.reviewTags = reviewTags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
