package com.example.dao;

import com.example.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
//Dao接口继承自 JpaRepository ；JpaRepository 中提供了一些基本的数据库方法
public interface BookDao extends JpaRepository<Book, Integer> {
    List<Book> getBookByAuthorStartingWith(String author);
    List<Book> getBookByPriceGreaterThan(Float price);

    @Query(value = "select * from t_book where id = (select max(id) from t_book )", nativeQuery = true)
    Book getMaxIdBook();

    @Query("select b from t_book b where b.id > :id and b.author = :author")
    List<Book> getBookByIdAndAndAuthor(@Param("id") Integer id, @Param("author") String author);

    @Query("select b from t_book  b where b.id < ?2 and b.name like %?1%")
    List<Book> getBookByIdAndName(String name, Integer id);
}
