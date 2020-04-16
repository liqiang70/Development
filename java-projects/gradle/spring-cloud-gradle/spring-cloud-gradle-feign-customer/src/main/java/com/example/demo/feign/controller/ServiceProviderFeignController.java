package com.example.demo.feign.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.feign.model.UserVO;
import com.example.demo.feign.service.ServiceProviderFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月15日 上午8:39:57
 */

@Controller
@RequestMapping("feign")
public class ServiceProviderFeignController {

	private final Logger logger = LoggerFactory.getLogger(ServiceProviderFeignController.class);

	@Autowired
	private ServiceProviderFeignService serviceProviderFeignService; // 注入Feign

	@HystrixCommand
	@GetMapping("/hello")
	@ResponseBody
	public String sayHello() {
		// 在方法中调用feign的方法
		String message = serviceProviderFeignService.helloFeign();
		logger.info("[feign-customer][FeignController][sayHello], message={}", message);
		
		return message;
	}

	/**
	 * 注意这里是SpringMVC，URL中的参数与方法中的参数名相同无需在注解中注明参数名
	 */
	@HystrixCommand
	@GetMapping("/greet/{test}")
	@ResponseBody
	public String greet(@PathVariable String test) {
		return serviceProviderFeignService.greetFeign(test);
	}

	/**
	 * 调用Feign中使用@RequestParam的方法
	 */
	@HystrixCommand
	@GetMapping("/users")
	@ResponseBody
	public List<UserVO> getUserListByIds(@RequestParam("ids") List<Long> ids) {
		return serviceProviderFeignService.getUsersByIds(ids);
	}

	@HystrixCommand
	@GetMapping("/headers")
	@ResponseBody
	public String getParamByHeaders(@RequestHeader("name") String name) {
		return serviceProviderFeignService.getParamByHeaders(name);
	}

	@HystrixCommand
	@PostMapping("/requestBody")
	@ResponseBody
	public UserVO getParamByRequestBody(@RequestBody UserVO user) {
		return serviceProviderFeignService.getUserByRequestBody(user);
	}

	/**
	 * 测试Feign的继承特性
	 */
	@HystrixCommand
	@GetMapping("/refactor/{name}")
	@ResponseBody
	public String refactorHelloService(@PathVariable String name) {
		return serviceProviderFeignService.hello(name);
	}
	
	
	/**
     * 测试重连机制
     */
	@HystrixCommand
    @LoadBalanced
    @GetMapping("/retry")
    @ResponseBody
    public String retry(){
        return serviceProviderFeignService.feignRetry();
    }
}