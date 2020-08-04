package com.ayush.ztrainingspring.reviews.model;

import java.net.MalformedURLException;
import java.net.URL;

public class ReviewProfile {
    private final URL img;
    private final String name;
    private int numReviews, numFollowers;

    public ReviewProfile(URL img, String name, int numReviews, int numFollowers) {
        this.img = img;
        this.name = name;
        this.numReviews = numReviews;
        this.numFollowers = numFollowers;
    }

    public ReviewProfile() throws MalformedURLException {
        this.img = new URL("https://b.zmtcdn.com/data/user_profile_pictures/3e8/b0000c8d0003578f54e63141373fe3e8.jpg?fit=around%7C100%3A100&amp;crop=100%3A100%3B%2A%2C%2A");
        this.name = "Ajay Kanji";
        this.numReviews = 0;
        this.numFollowers = 0;
    }

    public URL getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public int getNumFollowers() {
        return numFollowers;
    }
}
