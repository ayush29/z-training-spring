package com.ayush.ztrainingspring.order.menus;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Menurepo extends JpaRepository<Menus, UUID>{
    
}