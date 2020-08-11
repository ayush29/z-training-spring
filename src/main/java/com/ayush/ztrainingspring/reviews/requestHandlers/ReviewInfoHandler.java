package com.ayush.ztrainingspring.reviews.requestHandlers;

import com.ayush.ztrainingspring.reviews.model.ReviewTag;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ReviewInfoHandler {

    private String text;
    private int rating;
    private int userId;
    private List<ReviewTag> reviewTags;

    public ReviewInfoHandler(@JsonProperty("text") String text,
                             @JsonProperty("rating") int rating,
                             @JsonProperty("user_id") int userId,
                             @JsonProperty("review_tags") List<ReviewTag> reviewTags) {
        this.text = text;
        this.rating = rating;
        this.userId = userId;
        if(reviewTags == null)
            this.reviewTags = new ArrayList<>();
        else
            this.reviewTags = reviewTags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ReviewTag> getReviewTags() {
        return reviewTags;
    }

    public void setReviewTags(List<ReviewTag> reviewTags) {
        this.reviewTags = reviewTags;
    }
}
