package com.example.demo.task;

import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月28日 下午4:27:20
 */

@Slf4j
@Component
public class TaskListener implements TaskExecutionListener {

	@BeforeTask
	public void onTaskStartup(TaskExecution taskExecution) {
		log.info("任务执行 start: " + taskExecution.getTaskName());
	}

	@AfterTask
	public void onTaskEnd(TaskExecution taskExecution) {
		log.info("任务执行 end: " + taskExecution.getTaskName());
	}

	@FailedTask
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		log.info("任务执行 failed: " + taskExecution.getTaskName());
	}

}