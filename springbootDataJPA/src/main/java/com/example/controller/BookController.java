package com.example.controller;

import com.example.pojo.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public Map<String, Object> saveBook(@RequestBody Book book) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bookService.save(book));

        return map;
    }

    @GetMapping("getallbook")
    public Map<String, Object> getAllBook() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("books", bookService.getAllBook());

        return map;
    }
}
