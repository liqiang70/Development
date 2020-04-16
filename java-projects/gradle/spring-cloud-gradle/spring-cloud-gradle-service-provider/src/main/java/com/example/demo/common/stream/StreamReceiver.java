package com.example.demo.common.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.example.demo.intserv.model.UserVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月9日 下午7:53:09
 */

@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

	private final Logger logger = LoggerFactory.getLogger(StreamReceiver.class);

	@StreamListener(StreamClient.OUTPUT)
	@SendTo(StreamClient.INPUT)
	public String handleMessage(UserVO user) {
		String logString = "Received message: " + user.toString();
		logger.info(logString);
		return "receive ok";
	}

	@StreamListener(StreamClient.INPUT)
	public void confirmMessage(Object message) {
		String logString = "Received confirm message: " + message;
		logger.info(logString);
	}
}
