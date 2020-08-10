package com.ayush.ztrainingspring.overview.service;

import com.ayush.ztrainingspring.overview.entities.todo;
import com.ayush.ztrainingspring.overview.repository.Todo_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private Todo_repo todo_repo;
    @Autowired
    private todo temp;
    public List<todo> getAlltodo(){
        return todo_repo.findAll();
    }
    public void insert(todo temptodo){
        todo_repo.save(temptodo);
        return;
    }
}
