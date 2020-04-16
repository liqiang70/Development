package com.example.demo.feign.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.feign.controller.RefactorServiceController;
import com.example.demo.feign.model.UserVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月15日 上午8:36:32
 */

/**
 * 服务提供者的Feign 这个接口相当于把原来的服务提供者项目当成一个Service类，
 * 我们只需在声明它的Feign-client的名字，会自动去调用注册中心的这个名字的服务
 * 更简单的理解是value相当于MVC中的Controller类的父路径，通过"父路径+子路径和参数来调用服务"
 */

@FeignClient(value = "demo-service-provider", configuration = FeignClientsConfiguration.class, fallback = ServiceProviderFeignServiceFallBack.class) // 其中的value的值为要调用服务的名称
public interface ServiceProviderFeignService extends RefactorServiceController {

	/**
	 * 第一个Feign代码
	 * Feign中没有原生的@GetMapping/@PostMapping/@DeleteMapping/@PutMapping，要指定需要用method进行
	 */
	@RequestMapping(value = "/hello/hello", method = RequestMethod.GET)
	String helloFeign();

	/**
	 * 在服务提供者我们有一个方法是用直接写在链接，SpringMVC中用的@PathVariable
	 * 这里边和SpringMVC中有些有一点点出入，SpringMVC中只有一个参数而且参数名的话是不用额外指定参数名的，而feign中必须指定
	 */
	@RequestMapping(value = "/greet/{dd}", method = RequestMethod.GET)
	String greetFeign(@PathVariable("dd") String dd);

	/**
	 * 这里说下@RequestParam 注解和SpringMVC中差别也是不大，我认为区别在于Feign中的是参数进入URL或请求体中，
	 * 而SpringMVC中是参数从请求体中到方法中
	 * 
	 * @param ids id串，比如“1，2，3”
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserVO> getUsersByIds(@RequestParam("ids") List<Long> ids);

	/**
	 * 这里是将参数添加到Headers中
	 * 
	 * @param name 参数
	 */
	@RequestMapping(value = "/headers")
	String getParamByHeaders(@RequestHeader("name") String name);

	/**
	 * 调用服务提供者的post方法,接收回来再被服务提供者丢回来
	 * 
	 * @param user User对象
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	UserVO getUserByRequestBody(@RequestBody UserVO user);

	/**
	 * 继承服务提供者的HelloService的接口，从而拥有这个接口的所有方法 那么在这个Feign中就只需要使用HelloService定义的接口方法
	 * 
	 * (参考接口HelloServiceController定义）
	 */

	/**
	 * 测试重连机制
	 */
	@RequestMapping(value = "/retry", method = RequestMethod.GET)
	String feignRetry();
}