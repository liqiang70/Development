package com.example.demo.common.lock.redisson;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年3月2日 下午10:51:45
 */

/**
 * 异常类
 */
public class UnableToAquireLockException extends RuntimeException {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToAquireLockException() {
    }
 
    public UnableToAquireLockException(String message) {
        super(message);
    }
 
    public UnableToAquireLockException(String message, Throwable cause) {
        super(message, cause);
    }
}