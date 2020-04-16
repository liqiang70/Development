package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HelloServiceBackendB;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月24日 下午6:04:48
 */

@RequestMapping("/b")
@RestController
public class HelloControllerB {
	@Autowired
	HelloServiceBackendB helloServiceBackendB;

	@GetMapping("/users/{id}")
	public String users(@PathVariable String id) {
		return helloServiceBackendB.users(id);
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return helloServiceBackendB.hello();
	}

	@GetMapping("/hello_error")
	public String hello_error(String name) {
		return helloServiceBackendB.hello_error();
	}
}
