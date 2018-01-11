package com.shuyun.sqlgather.job;

import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.alibaba.fastjson.JSON;
import com.shuyun.sqlgather.entry.Database;
import com.shuyun.sqlgather.entry.GatherInfo;

public class Test {
	public Scheduler createScheduler() throws SchedulerException {
		return StdSchedulerFactory.getDefaultScheduler();
	}

	public void scheduleJob(Scheduler scheduler) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(SqlGatherJob.class).withIdentity("job1_1", "task").build();
		GatherInfo gatherInfo = new GatherInfo();
		Database database = new Database();
		database.setDriver("com.mysql.jdbc.Driver");
		database.setUrl("jdbc:mysql://localhost:3306/test_qrtz?useUnicode=true&characterEncoding=utf-8");
		database.setUsername("root");
		database.setPassword("root");
		gatherInfo.setTarget(database);
		Database database1 = new Database();
		database1.setDriver("com.mysql.jdbc.Driver");
		database1.setUrl("jdbc:mysql://localhost:3306/test_qrtz?useUnicode=true&characterEncoding=utf-8");
		database1.setUsername("root");
		database1.setPassword("root");
		gatherInfo.setSource(database1);
		gatherInfo.setTargetTable("tb_job_detail");
		gatherInfo.setLastTime("2017-09-19 11:01:06");
		gatherInfo.setSql("select class_name as CLASSNAME from tb_job_detail where fired_time >=? and fired_time<=?");
		System.out.println();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		jobDataMap.put("sqlgather", JSON.parseObject(JSON.toJSONString(gatherInfo)));
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(UUID.randomUUID().toString(), "tGroup1")
				.withSchedule(cronScheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}
	public static void main(String[] args) throws Exception {
		Test example = new Test();
		Scheduler scheduler = example.createScheduler();
		example.scheduleJob(scheduler);
		scheduler.start();
		
	}
}
