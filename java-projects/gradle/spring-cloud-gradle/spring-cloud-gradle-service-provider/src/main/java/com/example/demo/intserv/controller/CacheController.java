package com.example.demo.intserv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.intserv.service.CacheService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年3月21日 下午12:11:14
 */

@Api
@RestController
@RequestMapping("/cache")
public class CacheController {
 
    @Autowired
    private CacheService cacheService ;
 
    @ApiOperation(value = "测试缓存" )
    @GetMapping("/test")
    public String testCache(){
       //第一次中缓存中查询
        List<String> test = cacheService.testCache("test");
        System.out.println(test);
 
        //修改缓存中的值
        List<String> test2 = cacheService.testPutCache("test");
        System.out.println(test2);
 
        //再次从缓存中查询
        List<String> test3 = cacheService.testCache("test");
        System.out.println(test3);
        return "";
    }
}

