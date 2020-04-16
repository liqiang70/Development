package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.command.UserCollapseCommand;
import com.example.demo.model.UserVO;
import com.example.demo.service.RibbonHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月4日 下午7:59:56
 */

@RestController
@RequestMapping("/ribbon")
public class RobbinCustomerController {

	@Autowired
	// 注入restTemplate
	private RestTemplate restTemplate;

	private final Logger logger = LoggerFactory.getLogger(RobbinCustomerController.class);

	@HystrixCommand
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		// 这里注释掉，因为之前想当然使用了直链访问服务提供者的接口，这样是不会返回结果的，而且会报错
		// return
		// restTemplate.getForEntity("http://localhost:8080/hello",String.class).getBody();
		// 使用restTemplate调用微服务接口

		String message = restTemplate.getForEntity("http://demo-service-provider/hello/hello", String.class).getBody();
		logger.info("[ribbon-customer][RobbinCustomerController][hello], message={}", message);

		return message;

	}

	@Autowired
	RibbonHystrixService ribbonHystrixService;

	@HystrixCommand
	@GetMapping("/hystrix/hello")
	public String hystrixHelloController() {
		// 调用服务层方法
		return ribbonHystrixService.hystrixHelloService();
	}

	/**
	 * 发送同步请求，使用继承方式实现自定义Hystrix
	 */
	@HystrixCommand
	@GetMapping("/sync")
	public UserVO sendSyncRequestGetUser() {
		return ribbonHystrixService.useSyncRequestGetUser();
	}

	/**
	 * 发送异步请求，使用继承方式实现自定义Hystrix
	 */
	@HystrixCommand
	@GetMapping("/async")
	public UserVO sendAsyncRequestGetUser() {
		return ribbonHystrixService.useAsyncRequestGetUser();
	}

	/**
	 * 使用注解发送异步请求
	 */
	@HystrixCommand
	@GetMapping("/annotationasync")
	public UserVO sendAsyncRequestByAnnotation() {
		Future<UserVO> userFuture = ribbonHystrixService.asyncRequest();
		try {
			return userFuture.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 单个请求处理
	 * 
	 * @param id
	 */
	@HystrixCommand
	@GetMapping("/users/{id}")
	public UserVO findOne(@PathVariable Long id) {
		logger.debug("=============/hystrix/users/{} 执行了", id);
		UserVO user = ribbonHystrixService.findOne(id);
		return user;
	}

	/**
	 * 多个请求处理
	 * 
	 * @param ids id串，使用逗号分隔
	 */
	@HystrixCommand
	@GetMapping("/users")
	public List<UserVO> findAll(@RequestParam List<Long> ids) {
		logger.debug("=============/hystrix/users?ids={} 执行了", ids);
		return ribbonHystrixService.findAll(ids);
	}

	/**
	 * 合并请求测试 说明：这个测试本应在findOne方法中new一个UserCollapseCommand对象进行测试
	 * 苦于没有好的办法做并发实验，这里就放在一个Controller中了 我们看到，在这个方法中用了三个UserCollapseCommand对象进行模拟高并发
	 */
	@HystrixCommand
	@GetMapping("/collapse")
	public List<UserVO> collapseTest() {
		logger.info("==========>collapseTest方法执行了");
		HystrixRequestContext.initializeContext();
		List<UserVO> userList = new ArrayList<>();

		try {
			Future<UserVO> queue1 = new UserCollapseCommand(ribbonHystrixService, 1L).queue();
			Future<UserVO> queue2 = new UserCollapseCommand(ribbonHystrixService, 2L).queue();
			Future<UserVO> queue3 = new UserCollapseCommand(ribbonHystrixService, 3L).queue();
			UserVO user1 = queue1.get();
			UserVO user2 = queue2.get();
			UserVO user3 = queue3.get();
			userList.add(user1);
			userList.add(user2);
			userList.add(user3);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return userList;
	}

	/**
	 * 同步方法测试合并请求
	 *
	 * 说明：这个方法是用来与上面的方法做类比的，通过这个实验我们发现如果使用同步方法， 那么这个请求合并的作用就没有了，这会给findAll方法造成性能浪费
	 */
	@HystrixCommand
	@GetMapping("synccollapse")
	public List<UserVO> syncCollapseTest() {
		logger.info("==========>syncCollapseTest方法执行了");
		HystrixRequestContext.initializeContext();
		List<UserVO> userList = new ArrayList<>();

		UserVO user1 = new UserCollapseCommand(ribbonHystrixService, 1L).execute();
		UserVO user2 = new UserCollapseCommand(ribbonHystrixService, 2L).execute();
		UserVO user3 = new UserCollapseCommand(ribbonHystrixService, 3L).execute();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		return userList;
	}

}
