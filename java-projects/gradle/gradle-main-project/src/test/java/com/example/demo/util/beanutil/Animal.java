package com.example.demo.util.beanutil;

import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月21日 上午10:13:44
 */

@Data
public class Animal {
	private String name;
	private int age;
	private String color;
	private String sex;
	private Zoo fromZoo;

	public Animal() {
	}

}
