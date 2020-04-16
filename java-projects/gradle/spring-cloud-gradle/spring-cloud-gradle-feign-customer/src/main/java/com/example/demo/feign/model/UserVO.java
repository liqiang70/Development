package com.example.demo.feign.model;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月5日 下午2:16:27
 */

public class UserVO {

	private String name;
	private String sex;
	private String phone;

	public UserVO() {
	}

	public UserVO(String name, String sex, String phone) {
		this.name = name;
		this.sex = sex;
		this.phone = phone;
	}

	public String toString() {
		return "user:{" + "name: " + name + ", " + "sex: " + sex + ", " + "phone: " + phone + " }";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}