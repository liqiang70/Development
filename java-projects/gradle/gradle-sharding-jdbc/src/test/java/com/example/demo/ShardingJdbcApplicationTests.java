package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.ConfigMapper;
import com.example.demo.dao.OrderMapper;
import com.example.demo.entity.Config;
import com.example.demo.entity.Order;

@SpringBootTest
class ShardingJdbcApplicationTests {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ConfigMapper configMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSelect() {
		System.out.println(("----- selectList method test ------"));
		List<Order> orderList = orderMapper.selectList(null);
		orderList.forEach(System.out::println);
	}

	@Test
	public void testInsert() {
		System.out.println(("----- Insert method test ------"));
		Order order = new Order();
		int ret;

		order.setOrderId(100L);
		order.setUserId(0L);
		order.setName("order0");
		ret = orderMapper.insert(order);
		System.out.println(order.toString() + " " + ret);

		order.setOrderId(101L);
		order.setUserId(1L);
		order.setName("order1");
		ret = orderMapper.insert(order);
		System.out.println(order.toString() + " " + ret);

		order.setOrderId(103L);
		order.setUserId(0L);
		order.setName("order3");
		ret = orderMapper.insert(order);
		System.out.println(order.toString() + " " + ret);

		order.setOrderId(104L);
		order.setUserId(1L);
		order.setName("order4");
		ret = orderMapper.insert(order);
		System.out.println(order.toString() + " " + ret);

		List<Order> orderList = orderMapper.selectList(null);
		orderList.forEach(System.out::println);

		Config config = new Config();
		config.setId(9);
		config.setUserId(900L);
		config.setConfig("config");
		ret = configMapper.insert(config);
		List<Config> configList = configMapper.selectList(null);
		configList.forEach(System.out::println);

	}
}
