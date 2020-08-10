package com.ayush.ztrainingspring.overview.repository;

import com.ayush.ztrainingspring.overview.entities.todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Todo_repo extends JpaRepository<todo, Long>{

}