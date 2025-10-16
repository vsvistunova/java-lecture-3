package com.example.java_lecture_3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController
{
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello!";
    }
    @GetMapping("/time")
    public String showTimeNow(){
        return "Now time:  " + new Date();
    }
}
