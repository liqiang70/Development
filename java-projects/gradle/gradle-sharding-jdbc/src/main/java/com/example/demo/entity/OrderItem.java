package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月27日 下午11:27:48
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	@TableId
	private long orderId;

	private long userId;

	private String item;

}
