package com.shuyun.datacenter.listener;

import com.shuyun.datacenter.WorkPlan;

public interface TaskListener {
	public void before(WorkPlan plan);
	public void end(WorkPlan plan);
}
