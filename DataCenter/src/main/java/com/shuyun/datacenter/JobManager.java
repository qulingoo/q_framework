package com.shuyun.datacenter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.shuyun.datacenter.listener.AbstractSubject;

/**
 * 工作管理平台
 * 
 * @author Machenike
 *
 */
public class JobManager  extends AbstractSubject{
	Logger log = Logger.getLogger(JobManager.class);
	private ExecutorService threadPool;
	private List<TaskPlanning> planns;
	private WorkPlan workPlan;
	public JobManager(WorkPlan workPlan) {
		this.workPlan=workPlan;
		planns = WorkerFactory.buildWorkder(workPlan);
		if (planns != null && planns.size() > 0) {
			log.info("分割任务完成：" + planns);
			threadPool = Executors.newFixedThreadPool(planns.size());
		} else {
			log.error("任务分割失败");
		}
	}
	public void execute() {
		this.notifyStart(this.workPlan);
		for (int i = 0; i < planns.size(); i++) {
			threadPool.execute(new Worker(planns.get(i)));
		}
		threadPool.shutdown();
		while(true) {
			if(threadPool.isTerminated()) {
				this.notifyEnd(this.workPlan);	
				break;
			}
		}
		
	}

	 

}
