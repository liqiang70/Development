package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月28日 上午11:10:29
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Config {
	private int id;

	private long userId;

	private String config;
	
}
