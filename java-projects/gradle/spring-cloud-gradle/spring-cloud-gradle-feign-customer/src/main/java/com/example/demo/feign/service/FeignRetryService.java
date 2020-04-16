package com.example.demo.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月22日 下午2:37:17
 */

@FeignClient(name = "service", url = "http://baidu.com:8080", configuration = FeignClientsConfiguration.class, fallback = FeignRetryServiceFallBack.class)
public interface FeignRetryService {

	@RequestMapping("/getbaidu8080")
	String getbaidu8080();
}