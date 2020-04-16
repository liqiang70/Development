package com.example.demo.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.web.client.RestTemplate;

import com.example.demo.model.UserVO;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月15日 下午9:40:57
 */

public class UserCommand extends HystrixCommand<UserVO> {

	private RestTemplate restTemplate;

	//private Long id;

	public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
		super(setter);
		this.restTemplate = restTemplate;
		//this.id = id;
	}

	/**
	 * 注意本地main方法启动，url请用http://localhost:8080/user
	 * 通过controller请求启动需要改为服务调用地址：http://eureka-service/user
	 */
	@Override
	protected UserVO run() {
		// 本地请求
		// return restTemplate.getForObject("http://localhost:8080/user", User.class);
		// 连注册中心请求
		return restTemplate.getForObject("http://demo-service-provider/user", UserVO.class);
	}

	/**
	 * 此方法为《spirngcloud微服务实战》中的学习部分，仅用于在此项目启动的之后调用本地服务,但是不能没有走注册中心。
	 * 书中为我们留下了这个坑，详情请直接翻阅151页。
	 * 问题解决请参考：https://blog.csdn.net/lvyuan1234/article/details/76550706
	 * 本人在书中基础上已经完成调用注册中心服务的功能，见RibbonService类中具体实现
	 */
	public static void main(String[] args) {
		// 同步请求
		UserVO userSync = new UserCommand(
				com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
						.andCommandPropertiesDefaults(
								HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)),
				new RestTemplate(), 0L).execute();
		System.out.println("------------------This is sync request's response:" + userSync);
		// 异步请求
		Future<UserVO> userFuture = new UserCommand(
				com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
						.andCommandPropertiesDefaults(
								HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)),
				new RestTemplate(), 0L).queue();

		UserVO userAsync = null;

		try {
			userAsync = userFuture.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("------------------This is async request's response:" + userAsync);
	}
}