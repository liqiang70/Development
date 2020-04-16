package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月28日 下午9:39:15
 */

@RestController
@RequestMapping("/order")
public class OrderController {

	@Resource
	OrderService orderService;

	@GetMapping("/list")
	public List<Order> list() {
		System.out.println("devtool test. modified.");
		return orderService.list();
	}
}
