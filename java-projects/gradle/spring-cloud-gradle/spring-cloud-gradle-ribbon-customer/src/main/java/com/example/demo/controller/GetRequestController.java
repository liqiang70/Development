package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.UserVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月4日 下午8:08:48
 */

@RestController
public class GetRequestController {

	private final Logger logger = LoggerFactory.getLogger(GetRequestController.class);

	@Autowired
	// 注入restTemplate
	private RestTemplate restTemplate;

	/**
	 * ResponseEntity<T> getForEntity(String url, Class<T> responseType) T getBody()
	 * 以下此方法相同
	 */
    @HystrixCommand
	@GetMapping(value = "/entity/noparam")
	public String noParamGetForEntity() {
		// 这里注释掉，因为之前想当然使用了直链访问服务提供者的接口，这样是不会返回结果的，而且会报错
		// return
		// restTemplate.getForEntity("http://localhost:8080/hello",String.class).getBody();
		// 使用restTemplate调用微服务接口
		return restTemplate.getForEntity("http://demo-service-provider/hello", String.class).getBody();

	}

	/**
	 * ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object...
	 * uriVariables)
	 */
    @HystrixCommand
	@GetMapping("/entity/type")
	public UserVO getForEntityIdentifyByType() {
		// 不传参返回指定类型结果
		ResponseEntity<UserVO> entity = restTemplate.getForEntity("http://demo-service-provider/user", UserVO.class);
		UserVO body = entity.getBody();
		logger.info("user:" + body);
		return body;
		// 以上可简写为
//        return restTemplate.getForEntity("http://demo-service-provider/user", User.class).getBody();
	}

	/**
	 * ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object...
	 * uriVariables) 使用占位符对参数进行替换，内部使用String.format方法实现
	 */
    @HystrixCommand
	@GetMapping(value = "/entity")
	// 如果接收的参数是使用参数没有使用?有则使用@PathVariable，否则用@RequestParam
	public String getForEntityByQuestionMarkParam(@RequestParam("name") String name) {
		// 主要测试getEntity方法，这里测试直接传参
		return restTemplate.getForEntity("http://demo-service-provider/greet/{1}", String.class, name).getBody();
	}

	/**
	 * getForEntity方法内部会提取map中，以占位符为key的值作为参数回填入url中 ResponseEntity<T>
	 * getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
	 */
    @HystrixCommand
	@GetMapping(value = "/entity/map/{name}")
	// 如果接收的参数是使用参数没有使用?有则使用@PathVariable，否则用@RequestParam
	public String getForEntityByMap(@PathVariable("name") String name) {
		// 主要测试getEntity方法，这里测试map传参
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("name", name);
		return restTemplate.getForEntity("http://demo-service-provider/greet/{name}", String.class, reqMap)
				.getBody();
	}

	/**
	 * ResponseEntity<T> getForObject(URI url, Class<T> responseType)
	 */
    @HystrixCommand
	@GetMapping("/entity/uri")
	public String getForEntityByURI() {
		// 使用uri进行传参并访问
		UriComponents uriComponents = UriComponentsBuilder
				.fromUriString("http://demo-service-provider/greet/{name}").build().expand("Jerry").encode();
		URI uri = uriComponents.toUri();
		return restTemplate.getForEntity(uri, String.class).getBody();

	}

	/**
	 * T getForObject(String url, Class<T> responseType)
	 */
    @HystrixCommand
	@GetMapping("/object")
	public UserVO getForObjectWithNoParam() {
		// 相比getForEntity方法，获取对象可以省去调用getBody
		return restTemplate.getForObject("http://demo-service-provider/user", UserVO.class);
	}

	/**
	 * T getForObject(String url, Class<T> responseType, Map<String, ?>
	 * uriVariables)
	 */
    @HystrixCommand
	@GetMapping("/object/map")
	public UserVO getForObjectByMap() {
		// 使用map传参
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", "tom");
		return restTemplate.getForObject("http://demo-service-provider/user", UserVO.class, paramMap);
	}

	/**
	 * T getForObject(String url, Class<T> responseType, Object... uriVariables)
	 */
    @HystrixCommand
	@GetMapping("/object/param/{name}")
	public UserVO getForObjectByParam(@PathVariable String name) {
		return restTemplate.getForObject("http://demo-service-provider/user/{name}", UserVO.class, name);
	}

	/**
	 * T getForObject(URI url, Class<T> responseType)
	 */
    @HystrixCommand
	@GetMapping("/object/uri/{name}")
	public UserVO getForObjectByURI(@PathVariable String name) {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://demo-service-provider/user/{name}")
				.build().expand(name).encode();
		URI uri = uriComponents.toUri();
		return restTemplate.getForObject(uri, UserVO.class);
	}
}