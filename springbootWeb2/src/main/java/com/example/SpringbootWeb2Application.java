package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.error.ErrorAttributes;

@SpringBootApplication
//@ServletComponentScan
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})	//去除 ErrorMvcAutoConfiguration 的自动化配置
public class SpringbootWeb2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWeb2Application.class, args);
	}

}
