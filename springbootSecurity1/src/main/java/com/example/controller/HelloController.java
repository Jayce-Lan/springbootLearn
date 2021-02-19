package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello, admin!";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello, user!";
    }

    @GetMapping("/db/hello")
    public String dbHello() {
        return "Hello, DBA!";
    }
}
