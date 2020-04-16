package com.example.demo.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.UserVO;
import com.example.demo.service.RibbonHystrixService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年2月15日 下午12:56:51
 */

/**
 * @Author : Hellxz
 * @Description: 批量请求命令的实现
 * @Date : 2018/5/5 11:18
 */
public class UserBatchCommand extends HystrixCommand<List<UserVO>> {

	private final Logger logger = LoggerFactory.getLogger(RibbonHystrixService.class);

	private RibbonHystrixService ribbonHystrixService;
	/**
	 * 这个ids是UserCollapseCommand获取的参数集
	 */
	private List<Long> ids;

	public UserBatchCommand(RibbonHystrixService ribbonHystrixService, List<Long> ids) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userBatchCommand")));
		this.ribbonHystrixService = ribbonHystrixService;
		this.ids = ids;
	}

	/**
	 * <b>方法名</b>: run
	 * <p>
	 * <b>描 述</b>: 调用服务层的简单调用返回集合
	 * </p>
	 * @return List<User>
	 */
	@Override
	protected List<UserVO> run() {
		List<UserVO> users = ribbonHystrixService.findAll(ids);
		for (UserVO user : users) {
			logger.info(user.toString());
		}
		return users;
	}

	/**
	 * Fallback回调方法，如果没有会报错
	 */
	@Override
	protected List<UserVO> getFallback() {
		logger.info("UserBatchCommand的run方法，调用失败");
		return null;
	}

}