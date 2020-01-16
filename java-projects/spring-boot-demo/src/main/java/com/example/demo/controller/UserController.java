package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;
import com.example.exception.UserNotFoundException404;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testBoot")
@Api("UserController's api")
public class UserController {

	// mysql
	@Autowired
	private UserService userService;

	@RequestMapping("getUser/{id}")
	public String GetUser(@PathVariable int id) throws UserNotFoundException404 {

		User user = userService.selectById(id);
		if (user == null) {
			throw new UserNotFoundException404();
		}
		return user.toString();
	}

	// redis
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

	// swagger
	@ApiOperation("获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户的姓名", defaultValue = "zhouzhigang"),
			@ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "用户的密码", defaultValue = "123456") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public void getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("username is:" + username);
		System.out.println("password is:" + password);
	}

}