package com.ayush.ztrainingspring.order.mycart;

import java.util.*;

import javax.transaction.Transactional;

import com.ayush.ztrainingspring.order.menus.Menus;
import com.ayush.ztrainingspring.user_auth.User;

// import com.ayush.ztrainingspring.order.menus.Menus;
// import com.ayush.ztrainingspring.user_auth.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface Mycartrepo extends JpaRepository<Mycart, Integer>{
    @Query(value = "select * from mycart where userid = ?1 and itemid = ?2", nativeQuery = true)
    List<Mycart> findByUserAndMenus(int userid, int itemid);
    @Modifying
    @Query(value = "delete from mycart where userid = ?1 and itemid = ?2", nativeQuery = true)
    void deletebyiuid(int userid, int itemid);

    @Query(value = "select * from mycart where userid = ?1", nativeQuery = true)
    List<Mycart> findAllbyuser(int userid);
    
    @Query(value = "select sum(quantity) from mycart where userid = ?1", nativeQuery = true)
    List<Mycart> totalQant(int userid);

}