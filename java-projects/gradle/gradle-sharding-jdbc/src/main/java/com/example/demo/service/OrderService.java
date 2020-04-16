package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderMapper;
import com.example.demo.entity.Order;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月28日 下午9:39:04
 */

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	public List<Order> list() {
		List<Order> orders = orderMapper.selectList(null);
		return orders;
	}

}
