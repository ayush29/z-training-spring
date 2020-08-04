package com.ayush.ztrainingspring.reviews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.MalformedURLException;
import java.util.*;

public class Review {
    private final UUID id;
    private final ReviewProfile profile;
    private final int reviewRatingNum;
    private int reviewNumLikes, reviewNumComments;
    private final Calendar reviewRatingTime;
    private final List<String> reviewTags;
    private final String reviewText;
    private List<Comment> commentList;

    public Review(ReviewProfile profile, int reviewRatingNum, List<String> reviewTags, String reviewText) {
        this.id = UUID.randomUUID();
        this.profile = profile;
        this.reviewRatingNum = reviewRatingNum;
        this.reviewNumLikes = 0;
        this.reviewNumComments = 0;
        this.reviewTags = reviewTags;
        this.reviewText = reviewText;
        this.commentList = new ArrayList<>();
        this.reviewRatingTime = Calendar.getInstance();
    }

    public Review(@JsonProperty("reviewRatingNum") int reviewRatingNum, @JsonProperty("reviewText") String reviewText) throws MalformedURLException {
        this.id = UUID.randomUUID();
        this.profile = new ReviewProfile();
        this.reviewRatingNum = reviewRatingNum;
        this.reviewNumLikes = 0;
        this.reviewNumComments = 0;
        this.reviewTags = new ArrayList<>(Arrays.asList("biryani", "delicious food"));
        this.reviewText = reviewText;
        this.commentList = new ArrayList<>();
        this.reviewRatingTime = Calendar.getInstance();
    }

    public ReviewProfile getProfile() {
        return profile;
    }

    public int getReviewRatingNum() {
        return reviewRatingNum;
    }

    public int getReviewNumLikes() {
        return reviewNumLikes;
    }

    public void setReviewNumLikes() {
        reviewNumLikes++;
    }

    public int getReviewNumComments() {
        return reviewNumComments;
    }

    public Calendar getReviewRatingTime() {
        return reviewRatingTime;
    }

    public List<String> getReviewTags() {
        return reviewTags;
    }

    public UUID getId() {
        return id;
    }

    public void addComment(Comment comment) {
        commentList.add(0, comment);
        reviewNumComments++;
    }

    public String getReviewText() {
        return reviewText;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}
