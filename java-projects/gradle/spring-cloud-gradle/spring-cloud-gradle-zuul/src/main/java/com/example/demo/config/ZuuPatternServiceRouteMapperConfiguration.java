package com.example.demo.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年2月4日 下午2:28:08
 */
@Configuration
public class ZuuPatternServiceRouteMapperConfiguration {

	/**
     * 有版本号的路由匹配规则bean
     * @return 路由匹配规则bean
     */
	@Bean
    public PatternServiceRouteMapper patternServiceRouteMapperWithVersion(){
        return new PatternServiceRouteMapper("(?<name>.*)-(?<version>v.*$)","${version}/${name}");
    }

}
