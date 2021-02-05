package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getUserById")
    public String getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("deleteUserById")
    public void deleteUserById(Integer id) {
        userService.deleteUserById(id);
    }
}
