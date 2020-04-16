package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月17日 下午3:15:44
 */

@RestController
@Slf4j
@RequestMapping("/config")
public class ConfigController {

	@Autowired
	ClientCertificationConfig clientCertificationConfig;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String config() {
		String message = "config= " + clientCertificationConfig.toString();
		log.info(message);
		return message;
	}
}