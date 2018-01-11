package com.shuyun.datacenter;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * 工厂
 * 
 * @author qlk
 *
 */
public class WorkerFactory {
	Logger log = Logger.getLogger(WorkerFactory.class);
	private static WorkerFactory workerFactory;

	private WorkerFactory() {
	}

	private static WorkerFactory instence() {
		synchronized (WorkerFactory.class) {
			if (workerFactory == null) {
				workerFactory = new WorkerFactory();
			}
			return workerFactory;
		}
	}

	public static List<TaskPlanning> buildWorkder(WorkPlan workPlan) {
		WorkerFactory factory = instence();
		return factory.split(workPlan);

	}

	public List<TaskPlanning> split(WorkPlan workPlan) {
		List<TaskPlanning> planns = null;
		log.debug("开始分割工作计划");
		String splitClass = workPlan.getString("split-class");
		log.debug("获取分割工作计划执行类：" + splitClass);
		try {
			Class<?> clazz = Class.forName(splitClass);
			TaskAssign taskAssign = (TaskAssign)clazz.newInstance();
			 planns = taskAssign.split(workPlan);
			return planns;
		} catch ( Exception e) {
			if (splitClass == null) {
				log.error("获取分割工作计划执行类失败，需要设置任务分割执行类");
			} else {
				log.error("初始化分割类失败，" + e.getMessage());
			}
			return null;
		}


	}

}
