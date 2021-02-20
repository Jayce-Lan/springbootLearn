package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan("com.example.dao")
public class SpringbootSeckillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSeckillApplication.class, args);
	}

}
