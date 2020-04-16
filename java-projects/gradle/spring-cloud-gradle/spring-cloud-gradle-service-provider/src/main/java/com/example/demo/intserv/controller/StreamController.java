package com.example.demo.intserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.stream.StreamClient;
import com.example.demo.intserv.model.UserVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月9日 下午7:55:03
 */

@RestController
@RequestMapping("/stream")
public class StreamController {
	@Autowired
	private StreamClient streamClient;

	@GetMapping("/send")
	public String sendStreamMessage() {
		UserVO user = new UserVO("tom", "male", "13823638815");
		streamClient.out().send(MessageBuilder.withPayload(user).build());
		return "send ok";
	}
}
