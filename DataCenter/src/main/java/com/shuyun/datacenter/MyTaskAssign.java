package com.shuyun.datacenter;

import java.util.ArrayList;
import java.util.List;

public class MyTaskAssign extends TaskAssign {

	@Override
	public List<TaskPlanning> split(WorkPlan workPlan) {
		Integer concurrence = workPlan.getInteger("concurrence");
		List<TaskPlanning> list=new ArrayList<>();
		List<String> dateList = Util.splitDate(workPlan.getString("begin-time"), workPlan.getString("end-time"), concurrence);
		for (int i = 0; i < dateList.size()-1; i++) {
			TaskPlanning taskPlan = new TaskPlanning();
			taskPlan.put("topology", workPlan.get("topology"));
			taskPlan.put("source", workPlan.get("source"));
			taskPlan.put("target", workPlan.get("target"));
			taskPlan.put("begin-time", dateList.get(i));
			taskPlan.put("end-time", dateList.get(i+1));
			list.add(taskPlan);
		}
		return list;
	}

}
