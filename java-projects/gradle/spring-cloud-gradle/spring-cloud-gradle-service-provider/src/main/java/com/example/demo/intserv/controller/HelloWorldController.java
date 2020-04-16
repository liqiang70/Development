package com.example.demo.intserv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月4日 下午8:08:48
 */

@RestController
@RequestMapping("/hello")
@Api(value = "HelloController's api", tags = { "hello模块" })
public class HelloWorldController {

	@Autowired
	private DiscoveryClient client; // 注入发现客户端

	private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	// swagger
	@ApiOperation("获取用户信息")
	@ApiResponses({ @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		// 获取服务实例，作用为之后console显示效果
		List<ServiceInstance> serviceInstanceList = client.getInstances("demo-config");
		String logString = "";
		for (ServiceInstance serviceInstance : serviceInstanceList) {
			logString = "/hello host:" + serviceInstance.getHost() + " service_id:"
					+ serviceInstance.getServiceId().toLowerCase();
			logger.info(logString);
		}

		logger.info("[service-provider][HelloController][hello], message={}", logString);
		return "hello: " + logString;
	}
}