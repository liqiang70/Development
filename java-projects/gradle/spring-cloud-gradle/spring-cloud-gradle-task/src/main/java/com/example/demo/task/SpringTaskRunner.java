package com.example.demo.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年2月28日 下午6:45:12
 */

@Slf4j
@Component
public class SpringTaskRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
    	log.info("Hello World from Spring Cloud Task! --ApplicationRunner");
    }
}