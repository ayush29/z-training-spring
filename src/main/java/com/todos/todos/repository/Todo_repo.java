package com.todos.todos.repository;

import com.todos.todos.entities.todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Todo_repo extends JpaRepository<todo, Long>{

}