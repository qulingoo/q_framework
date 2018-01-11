package com.shuyun.datacenter;

import java.util.List;

public abstract class TaskAssign {
	public abstract List<TaskPlanning> split(WorkPlan workPlan);
}
