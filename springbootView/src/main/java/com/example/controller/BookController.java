package com.example.controller;

import com.example.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @GetMapping("/book")
    public ModelAndView books() {
        List<Book> bookList = new ArrayList<Book>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setName("三国演义");
        book1.setAuthor("罗贯中");

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("围城");
        book2.setAuthor("钱钟书");

        bookList.add(book1);
        bookList.add(book2);

        ModelAndView mav = new ModelAndView();
        mav.addObject("bookList", bookList);
        mav.setViewName("bookList");

        return mav;
    }

    @GetMapping("/books")
    public ModelAndView booksFreemarker() {
        List<Book> bookList = new ArrayList<Book>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setName("三国演义");
        book1.setAuthor("罗贯中");

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("围城");
        book2.setAuthor("钱钟书");

        bookList.add(book1);
        bookList.add(book2);

        ModelAndView mav = new ModelAndView();
        mav.addObject("books", bookList);
        mav.setViewName("books");

        return mav;
    }
}
