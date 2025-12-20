package com.university.java_lecture_3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String sayHello() {
    return "Привет!";
  }

  @GetMapping("/time")
  public String getTime() {
    return "Текущее время: " + LocalDateTime.now();
  }

}

