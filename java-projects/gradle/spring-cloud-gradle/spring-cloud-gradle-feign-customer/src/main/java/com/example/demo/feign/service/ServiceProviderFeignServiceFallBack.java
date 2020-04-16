package com.example.demo.feign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.feign.model.UserVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月21日 下午10:40:06
 */

@Component
public class ServiceProviderFeignServiceFallBack implements ServiceProviderFeignService {

	@Override
	public String hello(String name) {
		return "feign hystrix fallback for hello method";
	}

	@Override
	public String helloFeign() {
		return "feign hystrix fallback for helloFeign method";
	}

	@Override
	public String greetFeign(String dd) {
		return "feign hystrix fallback for greetFeign method";
	}

	@Override
	public List<UserVO> getUsersByIds(List<Long> ids) {
		UserVO errorUser = new UserVO();
		errorUser.setPhone("getUsersByIds.errorPhone");
		errorUser.setName("getUsersByIds.errorName");
		errorUser.setSex("getUsersByIds.errorSex");

		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(errorUser);
		return list;
	}

	@Override
	public String getParamByHeaders(String name) {
		return "feign hystrix fallback for getParamByHeaders method";
	}

	@Override
	public UserVO getUserByRequestBody(UserVO user) {
		UserVO errorUser = new UserVO();
		errorUser.setPhone("getUserByRequestBody.errorPhone");
		errorUser.setName("getUserByRequestBody.errorName");
		errorUser.setSex("getUserByRequestBody.errorSex");
		
		return errorUser;
	}

	@Override
	public String feignRetry() {
		return "feign hystrix fallback for feignRetry method";
	}

}
