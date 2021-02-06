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

    @GetMapping("/getbookbyid")
    public String getBookById() {
        Book book = bookService.getBookById(1);
        return book.toString();
    }

    @GetMapping("/getallbook")
    public Map<String, Object> getAllBook() {
        Map<String, Object> bookMap = new HashMap<String, Object>();
        List<Book> bookList = bookService.getAllBooks();
        bookMap.put("bookList", bookList);
        return bookMap;
    }
}
