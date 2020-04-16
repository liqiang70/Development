package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException404;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.redis.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testBoot")
@Api(value = "UserController's api", tags = { "用户模块" })
@Slf4j
public class UserController {

	// mysql
	@Autowired
	private UserService userService;

	@ApiOperation("获取mysql中用户信息")
	@RequestMapping(value = "getUser/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String GetUser(@PathVariable int id) throws UserNotFoundException404 {

		User user = userService.selectById(id);
		if (user == null) {
			throw new UserNotFoundException404();
		}
		String msg = user.toString();
		log.info(msg);
		return msg;
	}

	// redis function
	@Autowired
	private RedisUtil redisUtil;

	@ApiOperation("设置redis中的值")
	@RequestMapping(value = "setRedis/{key}/{value}", method = { RequestMethod.POST, RequestMethod.GET })
	public String setRedis(@PathVariable String key, @PathVariable String value) {
		redisUtil.setKey(key, value);
		String msg = "The Operation is finished on " + new Date() + " . ";
		log.info(msg);
		return msg;
	}

	@ApiOperation("获取redis中的值")
	@RequestMapping(value = "getRedis/{key}", method = { RequestMethod.POST, RequestMethod.GET })
	public String getRedis(@PathVariable String key) {
		String msg = "The Value of key( " + key + " ) = " + redisUtil.getValue(key);
		log.info(msg);
		return msg;
	}

	// swagger
	@ApiOperation("获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户的姓名", defaultValue = "zhangsan"),
			@ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "用户的密码", defaultValue = "123456") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		String msg = "username is:" + username + "; password is:" + password;
		log.info(msg);
		return msg;
	}

}