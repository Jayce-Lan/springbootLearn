package com.example.controller;

import com.example.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {
    //两个实例均为Spring Boot提供的默认实例，在我们没有手动创建的时候，这两个实例将会被默认创建
    @Autowired
    RedisTemplate redisTemplate;    //用于操作对象
    @Autowired
    StringRedisTemplate stringRedisTemplate;    //RedisTemplate的子类，可以操作序列化的字符串

    @GetMapping("/getname")
    public String test1() {
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set("name", "围城");
        String name = ops1.get("name");

        return name;
    }

    @GetMapping("/getbook")
    public Map<String, Object> test2() {
        Map<String, Object> map = new HashMap<String, Object>();
        ValueOperations ops2 = redisTemplate.opsForValue();
        Book book = new Book();
        book.setId(1);
        book.setName("围城");
        book.setAuthor("钱钟书");

        ops2.set("book", book);
        Book redisBook = (Book) ops2.get("book");

        map.put("book", redisBook);

        return map;
    }
}
