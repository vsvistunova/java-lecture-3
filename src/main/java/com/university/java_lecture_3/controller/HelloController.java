package com.university.java_lecture_3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String sayHello() {
    return "Привет!";
  }
  @GetMapping("/time")
    public static ZonedDateTime sayTime() {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        System.out.println(date);  // Выводит, например: 2025-10-11T22:09:06.000+03:00[Europe/Moscow]
        return date;
    }
}