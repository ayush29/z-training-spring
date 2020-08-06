package com.ayush.ztrainingspring.user_auth;


import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
