package com.example.demo.feign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <b>类名</b>: HelloService
 * <p><b>描    述</b>: 用于定义Controller的接口，实现放在Controller中
 * 这个类的主要作用是方便Feign中继承特性的测试
 * @author ThinkPad
 * Date:2020年2月16日 上午11:06:14
 */

public interface RefactorServiceController {

    /**
     * 声明一个接口，没有实现
     */
    @GetMapping(value = "/refactor-service/{name}")
    String hello(@PathVariable("name") String name);
}
