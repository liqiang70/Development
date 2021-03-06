package com.example.demo.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月1日 下午9:37:26
 */

@Service
@Slf4j
public class JobServiceImpl implements JobService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * 创建一个定时任务
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	@Override
	public void addCronJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail != null) {
				log.info("job:" + jobName + " 已存在");
			} else {
				// 构建job信息
				jobDetail = JobBuilder.newJob(CronJob.class).withIdentity(jobName, jobGroup).build();
				// 用JopDataMap来传递数据
				jobDetail.getJobDataMap().put("taskData", "hzb-cron-001");

				// 表达式调度构建器(即任务执行的时间,每5秒执行一次)
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");

				// 按新的cronExpression表达式构建一个新的trigger
				CronTrigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(jobName + "_trigger", jobGroup + "_trigger").withSchedule(scheduleBuilder)
						.build();
				scheduler.scheduleJob(jobDetail, trigger);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void addAsyncJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail != null) {
				log.info("job:" + jobName + " 已存在");
			} else {
				// 构建job信息,在用JobBuilder创建JobDetail的时候，有一个storeDurably()方法，可以在没有触发器指向任务的时候，将任务保存在队列中了。然后就能手动触发了
				jobDetail = JobBuilder.newJob(AsyncJob.class).withIdentity(jobName, jobGroup).storeDurably().build();
				jobDetail.getJobDataMap().put("asyncData", "this is a async task");
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger", jobGroup + "_trigger") // 定义name/group
						.startNow()// 一旦加入scheduler，立即生效
						.withSchedule(simpleSchedule())// 使用SimpleTrigger
						.build();
				scheduler.scheduleJob(jobDetail, trigger);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	@Override
	public void pauseJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");

			scheduler.pauseTrigger(triggerKey);
			log.info("=========================pause job:" + jobName + " success========================");
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 恢复任务
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	@Override
	public void resumeJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");
			scheduler.resumeTrigger(triggerKey);
			log.info("=========================resume job:" + jobName + " success========================");
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.deleteJob(jobKey);
			log.info("=========================delete job:" + jobName + " success========================");
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}

	}
}