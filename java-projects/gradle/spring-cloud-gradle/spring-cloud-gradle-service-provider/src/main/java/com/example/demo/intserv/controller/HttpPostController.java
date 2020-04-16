package com.example.demo.intserv.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.intserv.model.UserVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月5日 下午6:55:25
 */

@RestController
public class HttpPostController {

	private final Logger logger = LoggerFactory.getLogger(HttpPostController.class);

	/**
	 * 接收一个对象再返回回去,postForEntity/postForObject方法通用
	 */
	@PostMapping("/user")
	public UserVO returnUserByPost(@RequestBody UserVO user) {
		logger.info("/use接口 " + user);
		return user;
	}

	/**
	 * 测试PostForEntity方法的参数，可以直接看输出判断结果了
	 */
	@PostMapping("/user/{str}")
	public UserVO returnUserByPost(@PathVariable String str, @RequestBody UserVO user) {
		logger.info("/user/someparam 接口传参 name：" + str + " " + user);
		return user;
	}

	/**
	 * 为postForLocation方法返回URI
	 */
	@PostMapping("/location")
	public @ResponseBody ResponseEntity<URI> returnURI(@RequestBody UserVO user) {
		// 这里模拟一个url，真实资源位置不一定是这里
		UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://springcloud-service-provider/location")
				.build().expand(user).encode();
		URI toUri = uriComponents.toUri();
		logger.info("/location uri: " + toUri);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(toUri);
		return new ResponseEntity<URI>(toUri, httpHeaders, HttpStatus.OK);
	}

}