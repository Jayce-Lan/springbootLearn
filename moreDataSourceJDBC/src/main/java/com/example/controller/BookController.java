package com.example.controller;

import com.example.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Resource(name = "jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;

    @Autowired
    @Qualifier("jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    @GetMapping("getallbook")
    public Map<String, Object> test1() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> bookList1 = jdbcTemplateOne.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));

        List<Book> bookList2 = jdbcTemplateTwo.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));

        map.put("book1", bookList1);
        map.put("book2", bookList2);

        return map;
    }
}
