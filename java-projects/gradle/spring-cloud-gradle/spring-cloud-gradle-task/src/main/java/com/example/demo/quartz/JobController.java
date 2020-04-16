package com.example.demo.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月1日 下午9:38:32
 */

@RestController
@RequestMapping("/quartztest")
@Api(value = "JobController's api", tags = { "任务模块" })
public class JobController {
	@Autowired
	private JobService jobService;

	/**
	 * 创建cron任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	// swagger
	@ApiOperation("创建定时任务")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "jobName", dataType = "String", required = true, value = "任务名称", defaultValue = "CronTask1"),
			@ApiImplicitParam(paramType = "query", name = "jobGroup", dataType = "String", required = true, value = "任务组名称", defaultValue = "TaskGroup1") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/cron", method = RequestMethod.POST)
	public String startCronJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		jobService.addCronJob(jobName, jobGroup);
		return "create cron task success";
	}

	/**
	 * 创建异步任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	// swagger
	@ApiOperation("创建异步任务")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "jobName", dataType = "String", required = true, value = "任务名称", defaultValue = "AsyncTask1"),
			@ApiImplicitParam(paramType = "query", name = "jobGroup", dataType = "String", required = true, value = "任务组名称", defaultValue = "TaskGroup1") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/async", method = RequestMethod.POST)
	public String startAsyncJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		jobService.addAsyncJob(jobName, jobGroup);
		return "create async task success";
	}

	/**
	 * 暂停任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@ApiOperation("暂停任务")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "jobName", dataType = "String", required = true, value = "任务名称", defaultValue = "CronTask1"),
			@ApiImplicitParam(paramType = "query", name = "jobGroup", dataType = "String", required = true, value = "任务组名称", defaultValue = "TaskGroup1") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public String pauseJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		jobService.pauseJob(jobName, jobGroup);
		return "pause job success";
	}

	/**
	 * 恢复任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@ApiOperation("恢复任务")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "jobName", dataType = "String", required = true, value = "任务名称", defaultValue = "CronTask1"),
			@ApiImplicitParam(paramType = "query", name = "jobGroup", dataType = "String", required = true, value = "任务组名称", defaultValue = "TaskGroup1") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public String resumeJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		jobService.resumeJob(jobName, jobGroup);
		return "resume job success";
	}

	/**
	 * 删除务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	@ApiOperation("删除任务")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "jobName", dataType = "String", required = true, value = "任务名称", defaultValue = "AsyncTask1"),
			@ApiImplicitParam(paramType = "query", name = "jobGroup", dataType = "String", required = true, value = "任务组名称", defaultValue = "TaskGroup1") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public String deleteJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		jobService.deleteJob(jobName, jobGroup);
		return "delete job success";
	}
}