package com.example.demo.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月24日 下午6:04:48
 */

@RestController
public class ConifgController {
	@RequestMapping("/")
	public String users() {
		return "Hello, Config Server!";
	}
}
