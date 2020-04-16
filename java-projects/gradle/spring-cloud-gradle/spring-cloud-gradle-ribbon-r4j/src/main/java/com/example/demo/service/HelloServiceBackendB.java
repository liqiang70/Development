package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月24日 下午7:03:03
 */

@Service
@CircuitBreaker(name = "backendB", fallbackMethod = "fallback")
@Retry(name = "backendB")
@RateLimiter(name = "backendB")
@Bulkhead(name = "backendB")
public class HelloServiceBackendB {
	@Autowired
	RestTemplate restTemplate;

	public String users(String id) {
		return restTemplate.getForObject("http://demo-service-provider/users/{1}", String.class, id);
	}

	public String hello() {
		return restTemplate.getForObject("http://demo-service-provider/hello/hello", String.class);
	}

	public String hello_error() {
		return restTemplate.getForObject("http://demo-service-provider/hello/hello_error", String.class);
	}

	/**
	 * 调用服务失败处理方法
	 * 
	 * @return "message"
	 */
	@SuppressWarnings("unused")
	private String fallback(Throwable e) {
		return "This is CircuitBreaker FallBackB.";
	}
}
