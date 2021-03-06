package com.example.demo.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月1日 下午9:40:14
 */

@Slf4j
public class AsyncJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		log.info("========================立即执行的任务，只执行一次===============================");
		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		log.info("jobName: " + jobDetail.getKey().getName() + " jobGroup: " + jobDetail.getKey().getGroup()
				+ " taskData: " + jobDetail.getJobDataMap().get("asyncData"));
	}
}