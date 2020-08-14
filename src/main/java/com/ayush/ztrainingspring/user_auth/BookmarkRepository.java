package com.ayush.ztrainingspring.user_auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<UserRestaurantBookmark,BookmarkId> {

}
