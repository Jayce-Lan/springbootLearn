package com.example.dao;

import com.example.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBook(Book book);
    Book getBookById(Integer id);
    List<Book> getAllBook();
}
