package com.example;

import com.example.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDevtoolsApplicationTests {
	@Autowired
	HelloService helloService;

	@Test
	void contextLoads() {
		String hello = helloService.sayHello("Jom");
		Assert.assertThat(hello, Matchers.is("Hello Jom!"));
	}

}
