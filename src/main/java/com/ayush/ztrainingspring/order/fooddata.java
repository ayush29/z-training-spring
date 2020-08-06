package com.ayush.ztrainingspring.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins=
"*")
@Controller
@RequestMapping("/api/fooddata")
public class fooddata {
    @GetMapping("")
    public String fooods(){
        return "fooddata.json";
    }
}