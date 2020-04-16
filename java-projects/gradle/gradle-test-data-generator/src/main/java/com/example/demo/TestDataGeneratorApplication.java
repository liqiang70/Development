package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demo.dao") // 扫描dao
@SpringBootApplication
public class TestDataGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDataGeneratorApplication.class, args);
	}

}
