package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableHystrix
public class RibbonCustomerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RibbonCustomerApplication.class, args);
	}

	@Bean // 将此Bean交给spring容器
	@LoadBalanced // 通过此注解开启负载均衡
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
