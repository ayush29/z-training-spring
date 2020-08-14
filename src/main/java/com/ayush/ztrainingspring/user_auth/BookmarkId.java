package com.ayush.ztrainingspring.user_auth;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookmarkId
        implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "restro_id")
    private Integer restroId;

    private BookmarkId() {}

    public BookmarkId(
            Integer userId,
            Integer restroId) {
        this.userId = userId;
        this.restroId = restroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookmarkId that = (BookmarkId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(restroId, that.restroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restroId);
    }
}