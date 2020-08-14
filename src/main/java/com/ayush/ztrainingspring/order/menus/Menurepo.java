package com.ayush.ztrainingspring.order.menus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface Menurepo extends JpaRepository<Menus, Integer>{
    @Query(value = "select distinct item_category from menus m where m.rid = ?1", nativeQuery = true)
    List<String> getcats(int rid);

    @Query(value = "select * from menus m where m.rid = ?1 and m.item_category = ?2", nativeQuery = true)
    List<Menus> getmenu(int rid, String itcat);

    @Query(value = "select * from menus m where m.item_id = ?1", nativeQuery = true)
    List<Menus> findfooddet(Menus menus);
}