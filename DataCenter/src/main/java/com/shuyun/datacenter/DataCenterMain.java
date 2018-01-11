package com.shuyun.datacenter;

import com.alibaba.fastjson.JSON;
import com.shuyun.datacenter.listener.TaskListener;

public class DataCenterMain {
	public static void main(String[] args) {
		String readAll = Util.readAll(DataCenterMain.class.getResource("/").getPath()+"/BaseTemplate.json");
		WorkPlan workPlan = JSON.parseObject(readAll, WorkPlan.class);
		JobManager jobManager = new JobManager(workPlan);
		jobManager.attach(new TaskListener() {
			
			@Override
			public void end(WorkPlan plan) {
				System.out.println("任务结束");
			}
			
			@Override
			public void before(WorkPlan plan) {
				System.out.println("任务开始");
			}
		});
		jobManager.execute();
	}
}
