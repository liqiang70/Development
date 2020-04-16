package com.example.demo.common.lock.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月2日 下午10:56:49
 */

@Component
public class DistributedLockerImpl implements DistributedLocker {

	private final static String LOCKER_PREFIX = "lock:";

	@Autowired
	private RedissonClient redissonClient;

	@Override
	public <T> T lock(String resourceName, AquiredLockWorker<T> worker)
			throws InterruptedException, UnableToAquireLockException, Exception {

		return lock(resourceName, worker, 100);
	}

	@Override
	public <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime)
			throws UnableToAquireLockException, Exception {
		RLock lock = redissonClient.getLock(LOCKER_PREFIX + resourceName);
		// Wait for 100 seconds and automatically unlock it after lockTime seconds
		boolean success = lock.tryLock(100, lockTime, TimeUnit.SECONDS);
		if (success) {
			try {
				return worker.invokeAfterLockAquire();
			} finally {
				lock.unlock();
			}
		}
		throw new UnableToAquireLockException();
	}
}