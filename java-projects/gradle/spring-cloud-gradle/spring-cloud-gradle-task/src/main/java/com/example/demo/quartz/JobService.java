package com.example.demo.quartz;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月1日 下午9:36:28
 */

public interface JobService {
	/**
	 * 添加一个定时任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 */
	void addCronJob(String jobName, String jobGroup);

	/**
	 * 添加异步任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 */
	void addAsyncJob(String jobName, String jobGroup);

	/**
	 * 暂停任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 */
	void pauseJob(String jobName, String jobGroup);

	/**
	 * 恢复任务
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 */
	void resumeJob(String triggerName, String triggerGroup);

	/**
	 * 删除job
	 * 
	 * @param jobName
	 * @param jobGroup
	 */
	void deleteJob(String jobName, String jobGroup);

}
