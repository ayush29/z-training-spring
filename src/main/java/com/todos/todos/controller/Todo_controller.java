package com.todos.todos.controller;

import com.todos.todos.entities.todo;
import com.todos.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
public class Todo_controller {
    @Autowired
    private TodoService todoService;
    @GetMapping(value = "/todos")
    public List<todo> getTodos(){
        System.out.println("get Called");
        return todoService.getAlltodo();
    }
    @PostMapping(value="/todos")
    public todo postTodo(@RequestBody todo temptodo){
        System.out.println("post called");
        temptodo.setCompleted(Boolean.FALSE);
        todoService.insert(temptodo);
        return temptodo;
    }

//    @DeleteMapping(value = "/todos/{id}")
//    public todo deleteTodo()


}
