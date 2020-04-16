package com.example.demo.common.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月9日 下午7:51:37
 */

public interface StreamClient {
	String INPUT = "myMessageIn";
	String OUTPUT = "myMessageOut";

	@Input(StreamClient.INPUT)
	SubscribableChannel input();

	@Output(StreamClient.OUTPUT)
	MessageChannel out();

}
