package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/admin/hello")
    public String testAdmin() {
        return "Hello, Admin!";
    }

    @GetMapping("/hello")
    public String testUser() {
        return "Hello, User!";
    }
}
