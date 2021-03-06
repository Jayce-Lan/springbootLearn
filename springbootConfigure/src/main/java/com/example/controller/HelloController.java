package com.example.controller;

import com.example.handler.MyInterceptor1;
import com.example.hello.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    Hello hello;

    @GetMapping("/hello")
    public String hello() {
        return hello.sayHello("Jayce");
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2";
    }
}
