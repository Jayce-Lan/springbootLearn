package com.example.service;

import com.example.dao.BookDao;
import com.example.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    /**
     * 保存一个实体
     * 这里的 save 方法是由 JpaRepository 接口提供的
     *
     * @param book 被保存的实体
     */
    public Book save(Book book) {
        return bookDao.save(book);
    }

    public Page<Book> getBookByPage(Pageable pageable) {
        return bookDao.findAll(pageable);
    }

    public List<Book> getAllBook() {
        return bookDao.findAll();
    }

    public List<Book> getBookByAuthorStartingWith(String author){
        return bookDao.getBookByAuthorStartingWith(author);
    }

    public List<Book> getBookByPriceGreaterThan(Float price) {
        return bookDao.getBookByPriceGreaterThan(price);
    }

    public Book getMaxIdBook() {
        return bookDao.getMaxIdBook();
    }

    public List<Book> getBookByIdAndAndAuthor(Integer id, String author) {
        return bookDao.getBookByIdAndAndAuthor(id, author);
    }

    public List<Book> getBookByIdAndName(String name, Integer id) {
        return bookDao.getBookByIdAndName(name, id);
    }
}
