package com.shuyun.datacenter;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 工人
 * @author qlk
 *
 */
public class Worker extends Thread{
	private Logger log=Logger.getLogger(Worker.class);
	private TaskPlanning planning;
	private WorkEngine engine;
	public Worker(TaskPlanning planning) {
		this.planning = planning;
		this.engine=new WorkEngine(planning);
	}

	@Override
	public void run() {
		log.info("开始工作时间为："+Util.formatNowDate());
		  engine.work(this.planning);
		log.info("完成工作时间为："+Util.formatNowDate());
		log.info("任务抽取["+planning.getString("begin-time")+"]---["+planning.getString("end-time")+"]时间段的数据结束");
		
		
		
	}
}
