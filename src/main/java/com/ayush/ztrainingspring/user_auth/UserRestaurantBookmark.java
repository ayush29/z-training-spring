package com.ayush.ztrainingspring.user_auth;

import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_restro_bookmarks")
public class UserRestaurantBookmark {

    @EmbeddedId
    private BookmarkId id;

    @ManyToOne(fetch = FetchType.LAZY)//,cascade = CascadeType.REMOVE)
    @MapsId("userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)//,cascade = CascadeType.REMOVE)
    @MapsId("restroId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurants restaurant;

    public UserRestaurantBookmark() {}

    public UserRestaurantBookmark(User user, Restaurants restaurant) {
        this.restaurant = restaurant;
        this.user = user;
        this.id = new BookmarkId(user.getId(), restaurant.getid());
    }

    //Getters and setters omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserRestaurantBookmark that = (UserRestaurantBookmark) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, restaurant);
    }
}
