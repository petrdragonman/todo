package com.petr.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @GetMapping
    public String hiFromPetr() {
        return "Hi from petr!";
    } 
    
}
