package com.example.demo.common.lock.redisson;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年3月2日 下午10:44:57
 */

/**
 * 获取锁后需要处理的逻辑
 */
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
