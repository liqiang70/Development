package com.example.demo.feign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.service.FeignRetryService;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月22日 下午2:40:13
 */

@RestController
public class FeignRetryController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FeignRetryService feignRetryService;

	@RequestMapping("feign_retry")
	public String test() {
		long start = System.currentTimeMillis();
		String re = "";
		try {
			re = feignRetryService.getbaidu8080();
		} catch (Exception e) {
			logger.error("", e);
			logger.info("time:" + (System.currentTimeMillis() - start));
		}

		return "return: " + re;
	}
}
