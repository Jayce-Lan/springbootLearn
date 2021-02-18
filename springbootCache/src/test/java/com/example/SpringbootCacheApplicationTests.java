package com.example;

import com.example.dao.BookDao;
import com.example.pojo.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootCacheApplicationTests {
	@Autowired
	BookDao bookDao;

	@Test
	void contextLoads() {
		Book bookById = bookDao.getBookById(1);
		System.out.println(bookById);
	}

}
