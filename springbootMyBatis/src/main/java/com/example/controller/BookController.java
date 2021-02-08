package com.example.controller;

import com.example.pojo.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/getAllBook")
    public Map<String, Object> getAllBook() {
        Map<String, Object> bookMap = new HashMap<String, Object>();
        List<Book> bookList = bookService.getAllBook();
        bookMap.put("bookList", bookList);
        return bookMap;
    }

    @GetMapping("/getbookbyid")
    public String getBookById(Integer id){
        return bookService.getBookById(id).toString();
    }
}
