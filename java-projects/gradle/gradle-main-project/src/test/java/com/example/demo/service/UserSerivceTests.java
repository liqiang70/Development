package com.example.demo.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.service.UserService;
import com.example.demo.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSerivceTests {

	@Autowired
	private UserService userService;

	@Test
	public void getUser() {
		User user = userService.selectById(1);
		Assertions.assertEquals(user.getUsername(), "zhangsan");
	}

}
