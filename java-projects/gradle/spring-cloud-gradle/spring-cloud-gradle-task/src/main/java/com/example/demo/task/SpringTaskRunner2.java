package com.example.demo.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月28日 下午10:19:02
 */

@Slf4j
@Component
public class SpringTaskRunner2 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
    	log.info("Hello World from Spring Cloud Task! --CommandLineRunner");
    }

}