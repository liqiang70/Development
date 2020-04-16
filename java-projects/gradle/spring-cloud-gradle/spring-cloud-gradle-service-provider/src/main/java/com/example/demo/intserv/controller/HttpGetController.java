package com.example.demo.intserv.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.intserv.model.UserVO;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月5日 下午2:17:15
 */

@RestController
public class HttpGetController {

	@Autowired
	private DiscoveryClient client; // 注入发现客户端

	private final Logger logger = LoggerFactory.getLogger(HttpGetController.class);

	/**
	 * @Description: 构造日志字符串
	 * @author ThinkPad Date:2020年2月5日 下午2:17:15
	 */
	private String buildLog() {
		String logString = "";
		// 获取服务实例，作用为之后console显示效果
		List<ServiceInstance> serviceInstanceList = client.getInstances("demo-config");
		for (ServiceInstance serviceInstance : serviceInstanceList) {
			logString = "/hello host:" + serviceInstance.getHost() + " service_id:"
					+ serviceInstance.getServiceId().toLowerCase();
		}
		return logString;
	}

	/**
	 * go straight test
	 */
	@GetMapping(value = "/hello")
	public String hello() {
		String logString = buildLog();
		logger.info(logString);
		return "httpget_hello: " + logString;
	}

	/**
	 * parameter test
	 */
	@GetMapping(value = "/greet/{dd}")
	public String greet(@PathVariable String dd) {
		String logString = buildLog();
		logger.info(logString);
		return "httpget_hello: " + logString + "-----" + dd;
	}

	/**
	 * 返回测试对象
	 */
	@GetMapping("/user")
	public UserVO getUser() {
		String logString = buildLog();
		logger.info(logString);
		return new UserVO("tom", "male", "123456789");
	}

	/**
	 * 根据名称返回对象，这里模拟查数据库操作
	 */
	@GetMapping("/user/{name}")
	public UserVO getUserSelect(@PathVariable String name) {
		String logString = buildLog();
		logger.info(logString);
		if (name.isEmpty()) {
			return new UserVO();
		} else if (name.equals("tom")) {
			return new UserVO("tom", "male", "123456789");
		} else {
			return new UserVO("随机用户", "male", "987654321");
		}
	}
	
	/**
     * 为Hystrix请求合并提供的接口
     */
    @GetMapping("/users/{id}")
    public UserVO getUserById(@PathVariable Long id){
        logger.info("=========getUserById方法：入参id："+id);
        
        UserVO user = new UserVO("one"+id, "女", "110-"+id);
		logger.info(user.toString());
        return user;
    }

    @GetMapping("/users")
    public List<UserVO> getUsersByIds(@RequestParam("ids") List<Long> ids){
        List<UserVO> userList = new ArrayList<>();
        UserVO user;
        logger.info("=========getUsersByIds方法：入参ids："+ids);

        for(Long id : ids){
            user = new UserVO("person"+id ,"男","123-"+id);
            userList.add(user);
    		logger.info(user.toString());
        }
        
        return userList;
    }
    
    
    @GetMapping("/headers")
    public String getParamByRequestHeader(@RequestHeader("name") String name){
        return name;
    }
    
    
    /**
     * 测试Feign延迟重试的代码
     * 这里我们为这个方法加上超过Feign默认2000ms以上的延迟，我们只需要通过查看日志输出即可
     */
    @GetMapping("/retry")
    public String feignRetry(){
        logger.info("feignRetry方法调用成功");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}