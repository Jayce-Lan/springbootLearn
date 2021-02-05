package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserById(Integer id) {
        System.out.println("get...");
        return "user" + id;
    }

    public void deleteUserById(Integer id) {
        System.out.println("delete...");
    }
}
