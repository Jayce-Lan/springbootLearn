package com.example.controller;

import com.example.pojo.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public Map<String, Object> addBook(@RequestBody Book book) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", (bookService.addBook(book) == 1) ? "OK" : "NO");

        return map;
    }

    @RequestMapping(value = "/updatebook", method = RequestMethod.POST)
    public Map<String, Object> updateBook(@RequestBody Book book) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", (bookService.updateBook(book) == 1) ? "OK" : "NO");

        return map;
    }

    @GetMapping("/deletebook")
    public Map<String, Object> deleteBook(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", (bookService.deleteBookById(id) == 1) ? "OK" : "NO");

        return map;
    }
}
