package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testBoot")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("getUser/{id}")
	public String GetUser(@PathVariable int id) {
		return userService.selectById(id).toString();
	}

	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("setRedis/{key}/{value}")
	public String setRedis(@PathVariable String key, @PathVariable String value) {
		redisUtil.setKey(key, value);
		return "The Operation is finished on " + new Date() + " . ";
	}

	@RequestMapping("getRedis/{key}")
	public String getRedis(@PathVariable String key) {
		return "The Value of key( " + key + " ) = " + redisUtil.getValue(key);
	}

}