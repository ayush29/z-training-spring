package com.ayush.ztrainingspring.reviews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Comment {
    private final UUID id;
    private final URL img;
    private final String username, userTag;
    private final String text;

    public Comment(URL img, String username, String userTag, String text) {
        this.id = UUID.randomUUID();
        this.img = img;
        this.username = username;
        this.userTag = userTag;
        this.text = text;
    }
    public Comment(@JsonProperty("text") String text) throws MalformedURLException {
        this.id = UUID.randomUUID();
        this.img = new URL("https://b.zmtcdn.com/data/pictures/chains/5/312995/aa4fc3fc70d8e32772a724d6cbc55ab0_featured_v2.jpg?fit=around%7C100%3A100&amp;crop=100%3A100%3B%2A%2C%2A");
        this.username = "Biryani by Kilo";
        this.userTag = "Management";
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public URL getImg() {
        return img;
    }

    public String getUsername() {
        return username;
    }

    public String getUserTag() {
        return userTag;
    }

    public String getText() {
        return text;
    }
}
