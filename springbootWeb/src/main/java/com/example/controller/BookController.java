package com.example.controller;

import com.example.pojo.Book;
import com.example.pojo.BookForFastjson;
import com.example.pojo.BookForGson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookController {
    /**
     * 使用默认JSON
     * @return
     */
    @GetMapping("/book")
    public Book book() {
        Book book = new Book();
        book.setName("围城");
        book.setAuthor("钱钟书");
        book.setPrice(30f);
        book.setPublicationDate(new Date());
        return book;
    }

    /**
     * 使用Gson
     * @return
     */
    @GetMapping("/bookforgson")
    public BookForGson bookForGson() {
        BookForGson book = new BookForGson();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setPrice(30f);
        book.setPublicationDate(new Date());
        return book;
    }

    /**
     * 使用fastjson
     * @return
     * {
     *     "@type": "com.example.pojo.BookForFastjson",
     *     "author": "罗贯中",
     *     "name": "三国演义",
     *     "price": 30.0F,
     *     "publicationDate": "2021-01-28"
     * }
     */
    @GetMapping("/bookforfastjson")
    public BookForFastjson bookForFastjson() {
        BookForFastjson book = new BookForFastjson();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setPrice(30f);
        book.setPublicationDate(new Date());
        return book;
    }
}
