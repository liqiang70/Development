package com.example.demo.intserv.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月7日 下午1:40:07
 */

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

	@Value("${a.b}")
	public String para1;

	@Value("${c.d}")
	public String para2;

	@RequestMapping("/show")
	public String show() {
		return "a.b=" + para1 + "; c.d=" + para2;
	}
}
