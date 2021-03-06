package com.example.demo.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.UserVO;
import com.example.demo.service.RibbonHystrixService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

/**
 * @Description: 继承HystrixCollapser的请求合并器
 * @author ThinkPad Date:2020年2月15日 下午1:04:16
 */
public class UserCollapseCommand extends HystrixCollapser<List<UserVO>, UserVO, Long> {

	private RibbonHystrixService ribbonHystrixService;
	private Long userId;

	/**
	 * 构造方法，主要用来设置这个合并器的时间，意为每多少毫秒就会合并一次
	 * 
	 * @param ribbonService 调用的服务
	 * @param userId        单个请求传入的参数
	 */
	public UserCollapseCommand(RibbonHystrixService ribbonHystrixService, Long userId) {
		super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand"))
				.andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
		this.ribbonHystrixService = ribbonHystrixService;
		this.userId = userId;
	}

	/**
	 * 获取请求中的参数
	 */
	@Override
	public Long getRequestArgument() {
		return userId;
	}

	/**
	 * 创建命令，执行批量操作
	 */
	@Override
	public HystrixCommand<List<UserVO>> createCommand(Collection<CollapsedRequest<UserVO, Long>> collapsedRequests) {
		// 按请求数声名UserId的集合
		List<Long> userIds = new ArrayList<>(collapsedRequests.size());
		// 通过请求将100毫秒中的请求参数取出来装进集合中
		userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
		// 返回UserBatchCommand对象，自动执行UserBatchCommand的run方法
		return new UserBatchCommand(ribbonHystrixService, userIds);
	}

	/**
	 * 将返回的结果匹配回请求中
	 * 
	 * @param batchResponse     批量操作的结果
	 * @param collapsedRequests 合在一起的请求
	 */
	@Override
	protected void mapResponseToRequests(List<UserVO> batchResponse,
			Collection<CollapsedRequest<UserVO, Long>> collapsedRequests) {
		int count = 0;
		for (CollapsedRequest<UserVO, Long> collapsedRequest : collapsedRequests) {
			// 从批响应集合中按顺序取出结果
			UserVO user = batchResponse.get(count++);
			// 将结果放回原Request的响应体内
			collapsedRequest.setResponse(user);
		}
	}
}