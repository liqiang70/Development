package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.command.UserCommand;
import com.example.demo.model.UserVO;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月6日 上午11:06:54
 */

@Service
public class RibbonHystrixService {

	@Autowired
	private RestTemplate restTemplate;

	private final Logger logger = LoggerFactory.getLogger(RibbonHystrixService.class);

	@HystrixCommand(fallbackMethod = "hystrixFallback")
	public String hystrixHelloService() {
		// 调用服务提供者接口，正常则返回hello字符串
		return restTemplate.getForEntity("http://demo-service-provider/hello/hello", String.class).getBody();
	}

	/**
	 * 调用服务失败处理方法
	 * 
	 * @return "error"
	 */
	public String hystrixFallback() {
		return "error";
	}

	/**
	 * 使用自定义HystrixCommand同步方法调用接口
	 */
	public UserVO useSyncRequestGetUser() {
		// 这里使用Spring注入的RestTemplate, Spring注入的对象都是静态的
		UserVO userSync = new UserCommand(
				com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
						.andCommandPropertiesDefaults(
								HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)),
				restTemplate, 0L).execute();

		return userSync;
	}

	/**
	 * 使用自定义HystrixCommand异步方法调用接口
	 */
	public UserVO useAsyncRequestGetUser() {

		Future<UserVO> userFuture = new UserCommand(
				com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
						.andCommandPropertiesDefaults(
								HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)),
				restTemplate, 0L).queue();

		UserVO userAsync = null;

		try {
			// 获取Future内部包含的对象
			userAsync = userFuture.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return userAsync;
	}

	/**
	 * 使用注解实现异步请求调用
	 *
	 * 注意：此处AsyncResult为netfix实现，spring也做了实现，注意导包。
	 */
	@HystrixCommand(fallbackMethod = "fallbackForUserTypeReturnMethod")
	public Future<UserVO> asyncRequest() {
		return new AsyncResult<UserVO>() {
			public UserVO invoke() {
				return restTemplate.getForObject("http://demo-service-provider/user", UserVO.class);
			}
		};
	}

	/**
	 * 调用服务失败处理方法：返回类型为User
	 */
	public UserVO fallbackForUserTypeReturnMethod() {
		return null;
	}

	/** 请求合并使用到的测试方法 **/
	/**
	 * 查一个User对象
	 */
	public UserVO findOne(Long id) {
		logger.info("findOne方法执行了，id= " + id);
		return restTemplate.getForObject("http://demo-service-provider/users/{1}", UserVO.class, id);
	}

	/**
	 * 查多个对象
	 *
	 * 注意： 这里用的是数组，作为结果的接收，因为restTemplate.getForObject方法在这里受限
	 * 如果尽如《SpringCloud微服务实战》一书中指定类型为List.class，会返回一个List<LinkedHashMap>类型的集合
	 * 为了避坑这里我们使用数组的方式接收结果
	 */
	public List<UserVO> findAll(List<Long> ids) {
		logger.info("findAll方法执行了，ids= " + ids);
		UserVO[] users = restTemplate.getForObject("http://demo-service-provider/users?ids={1}", UserVO[].class,
				StringUtils.join(ids, ","));
		return Arrays.asList(users);
	}
}