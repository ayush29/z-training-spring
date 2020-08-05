package com.example.photos_sql_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping(path="/photos") // This means URL's start with /demo (after Application path)
public class PhotosController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private PhotosRepository photosRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Photo> getAllPhotos() {
        // This returns a JSON or XML with the users
        return photosRepository.findAll();
    }

    @GetMapping(path="/{category}")
    public @ResponseBody Iterable<Photo> getCatPhotos(@PathVariable String category) {
        // This returns a JSON or XML with the users
        return photosRepository.findByCategoryEquals(category);
    }
}
