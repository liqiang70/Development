package com.example.demo.intserv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年3月21日 下午12:12:56
 */

@Service
public class CacheService {
 
    /**
     * 查询缓存，缓存的名字是testList，用key来标识
     * @param key
     * @return
     */
    @Cacheable(cacheNames = "testList" , key = "#key")
    public List<String> testCache(String key){
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(key);
        return list;
    }
 
    /**
     * 修改缓存，缓存的名字是testList，用key来标识
     * @param key
     * @return
     */
    @CachePut(cacheNames = "testList" , key = "#key")
    public List<String> testPutCache(String key){
        List<String> list = new ArrayList<String>();
        list.add("r");
        list.add("s");
        list.add("t");
        list.add(key);
        return list;
    }
}
