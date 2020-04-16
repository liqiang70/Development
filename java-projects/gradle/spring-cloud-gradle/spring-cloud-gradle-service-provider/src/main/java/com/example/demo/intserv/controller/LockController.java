package com.example.demo.intserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.lock.redisson.AquiredLockWorker;
import com.example.demo.common.lock.redisson.DistributedLocker;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月2日 下午5:01:59
 */

@Slf4j
@RestController
@RequestMapping(value = "/lock", method = RequestMethod.GET)
public class LockController {

	@Autowired
	private DistributedLocker distributedLocker;

	@RequestMapping("redisson")
	public String redisson() throws Exception {
		distributedLocker.lock("redisson", new AquiredLockWorker<Object>() {

			@Override
			public Object invokeAfterLockAquire() {
				try {
					log.info("执行方法！--redisson");
					Thread.sleep(5000);
				} catch (Exception e) {
					log.error(e.getLocalizedMessage(), e);
				}
				return null;
			}

		});
		return "hello world! --redisson";
	}
}