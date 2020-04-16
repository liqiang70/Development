package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;
import feign.Retryer;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月22日 下午2:49:39
 */

@Configuration
public class FeignConfigure {

	@Bean
	public Request.Options options() {
		return new Request.Options(12000, TimeUnit.MILLISECONDS, 12000, TimeUnit.MILLISECONDS, true);
	}

	@Bean
	public Retryer feignRetryer() {
		return new Retryer.Default(100, 1000, 4);
	}

}