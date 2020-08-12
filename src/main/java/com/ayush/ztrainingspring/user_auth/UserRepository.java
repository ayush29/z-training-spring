package com.ayush.ztrainingspring.user_auth;


import com.ayush.ztrainingspring.photos.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);
    public User findByIdEquals(int id);
}
