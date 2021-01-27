package com.example.controller;

import com.example.pojo.Book;
import com.example.pojo.User;
import com.example.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private Book book;

    /*@Autowired
    private User user;*/

    @Autowired
    private Users users;

    @RequestMapping("/book")
    public String book() {
        return book.toString();
    }

    /*@GetMapping("/user")
    public String user() {
        return user.toString();
    }*/

    @RequestMapping("/userlist")
    public String userlist() {
        return users.toString();
    }
}
