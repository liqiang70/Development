package com.example.demo.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.UserVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月6日 下午12:02:41
 */

@RestController
public class PostRequestController {

	private final Logger logger = LoggerFactory.getLogger(PostRequestController.class);

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * ResponseEntity<T> postForEntity(String url, Object request, Class<T>
	 * responseType) 其中参数url不多说，Object
	 * request如果是不是一个HttpEntity对象，会自动转换为HttpEntity对象，视作完整的body来处理;
	 * 如果是HttpEntity对象，那么会被直接当做body处理并且包含header内容。
	 * 以下对于重写的方法就不多说了，使用方法大体同getForEntity，如果仅是简单post对象，那么使用不带Object...variables或Map
	 * variables的方法即可。 postForEntity(String url, Object request, Class<T>
	 * responseType, Object... uriVariables) postForEntity(String url, Object
	 * request, Class<T> responseType, Map<String, ?> uriVariables)
	 *
	 * 这里详细说下我遇到的坑： 1、其他几个重载方法的最后边的Object...variables和Map variables都是对之前的url进行操作的，
	 * 也就是说，在post请求的url中使用占位符进行传参，而如果在url中没有使用占位符，那么这些最后传的参数是无效的！ 2、方法中Object
	 * request这个对象如果和服务提供者的接收参数类型相同，那么服务提供者仅需使用@RequestBody接收参数即可。
	 * 3、如果二者都使用了，这就比较有趣了，需要一边通过@PathVariable注解接收uri中的参数，一边还需要@RequestBody接收对象或RequestParam按字段接收参数！
	 * 4、如果报错了，请仔细看看我上边写的三条，并注意服务提供者的参数接收注解的使用等。
	 */
    @HystrixCommand
	@PostMapping("/entity")
	public UserVO postForEntity() {
		UserVO user = new UserVO("tom1", "1", "678912345");
		ResponseEntity<UserVO> entity = restTemplate.postForEntity("http://demo-service-provider/user/jerry", user,
				UserVO.class, "测试参数");
		UserVO body = entity.getBody(); // 所有restTemplate.*ForEntity方法都是包装类，body为返回类型对象
		return body;
	}

	/**
	 * 使用URI传参，测试结果会显示在服务提供者的终端中 ResponseEntity<T> postForEntity(URI url, Object
	 * request, Class<T> responseType)
	 */
    @HystrixCommand
	@PostMapping("/entity/uri")
	public UserVO postForEntityByURI() {
		UserVO user = new UserVO("tom2", "1", "678912345");
		// 这里只是将url转成URI，并没有添加参数
		UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://demo-service-provider/user")
				.build().encode();
		URI toUri = uriComponents.toUri();
		// 使用user传参
		UserVO object = restTemplate.postForObject(toUri, user, UserVO.class);
		return object;
	}

	/**
	 * 这里测试postForObject方法，需要注意的参数如上述方法的描述，区别只是不需要getBody了，这里就不再累述了
	 * postForObject(String url, Object request, Class<T> responseType, Object...
	 * uriVariables) postForObject(String url, Object request, Class<T>
	 * responseType, Map<String, ?> uriVariables)
	 */
    @HystrixCommand
	@PostMapping("/object")
	public UserVO postForObject() {
		UserVO user = new UserVO("tom3", "1", "123654987");
		// 这里url传1是为了调用服务者项目中的一个接口
		UserVO responseBody = restTemplate.postForObject("http://demo-service-provider/user/jerry", user, UserVO.class);
		return responseBody;
	}

	/**
	 * post请求还有一种：postForLocation，这里也同样有三种重载，除了无需指定返回类型外，用法相同，返回类型均为URI，也就不累述了
	 * postForLocation(String url, Object request, Object... uriVariables)
	 * postForLocation(String url, Object request, Map<String, ?> uriVariables)
	 * postForLocation(URI url, Object request)
	 */
    @HystrixCommand
	@PostMapping("/location")
	public URI postForLocation() {
		UserVO user = new UserVO("tom4", "1", "987654321");
		URI uri = restTemplate.postForLocation("http://demo-service-provider/location", user);
		// 不知道为什么返回来是空，这个方法仅供参考吧，如果知道是什么情况，我会回来改的 -- 需要设置在response的header的location中
		logger.info("/location uri: " + uri);
		return uri;
	}

}