package com.example.demo.sharding.config;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月28日 上午11:17:34
 */

public class PreciseShardingAlgorithmImpl implements PreciseShardingAlgorithm<Long> {

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
		String dbName = "ds";
		Long val = shardingValue.getValue();
		dbName += val;
		for (String each : availableTargetNames) {
			if (each.equals(dbName)) {
				return each;
			}
		}
		throw new IllegalArgumentException();
	}
}