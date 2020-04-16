package com.example.demo.feign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年2月16日 上午11:53:58
 */

/**
 * 实现了HelloService接口的Controller
 */
@RestController //必须添加这个注解，告诉SpringMVC这是一个Controller
public class RefactorServiceControllerImpl implements RefactorServiceController {

	private final Logger logger = LoggerFactory.getLogger(RefactorServiceControllerImpl.class);
    /**
     * 实现了HelloService中的hello方法，通过这个方法来返回结果
     */
    @Override
    public String hello(@PathVariable String name) {
        logger.info("refactorHelloService的hello方法执行了，入参：name:{}", name);
        return "hello,"+name;
    }
}