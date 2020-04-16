package com.example.demo.exception;

import java.util.function.Predicate;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月24日 下午6:01:10
 */

public class RecordFailurePredicate implements Predicate<Throwable> {
	@Override
	public boolean test(Throwable throwable) {
		return true;
	}
}
