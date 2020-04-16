package com.example.demo.feign.service;

import org.springframework.stereotype.Component;


/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月21日 下午10:40:06
 */

@Component
public class FeignRetryServiceFallBack implements FeignRetryService {

	@Override
	public String getbaidu8080() {
		return "feign hystrix fallback for getbaidu8080 method";
	}

}
