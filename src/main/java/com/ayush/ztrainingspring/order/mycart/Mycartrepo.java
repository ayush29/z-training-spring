package com.ayush.ztrainingspring.order.mycart;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mycartrepo extends JpaRepository<Mycart, UUID>{
    List<Mycart> findByUseridAndItemid(UUID userid, UUID itemid);
}