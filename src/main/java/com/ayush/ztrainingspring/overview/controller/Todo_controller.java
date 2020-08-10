package com.ayush.ztrainingspring.overview.controller;

import com.ayush.ztrainingspring.overview.entities.todo;
import com.ayush.ztrainingspring.overview.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
public class Todo_controller {
    @Autowired
    private TodoService todoService;
    @GetMapping(value = "/restaurant/menu_image")
    public List<todo> getTodos(){
        System.out.println("get Called");
        return todoService.getAlltodo();
    }
    @PostMapping(value="/restaurant/menu_image")
    public todo postTodo(@RequestBody todo temptodo){
        System.out.println("post called");
        temptodo.setCompleted(Boolean.FALSE);
        todoService.insert(temptodo);
        return temptodo;
    }

//    @DeleteMapping(value = "/todos/{id}")
//    public todo deleteTodo()


}
