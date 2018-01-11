package com.shuyun.datacenter.listener;

import java.util.ArrayList;
import java.util.List;

import com.shuyun.datacenter.WorkPlan;

public class AbstractSubject implements Subject{
	List<TaskListener> listeners=new ArrayList<>();
	@Override
	public void attach(TaskListener listener) {
		listeners.add(listener)	;	
	}

	@Override
	public void detach(TaskListener listener) {
		listeners.remove(listener);		
	}

	@Override
	public void notifyStart(WorkPlan plan) {
		for (TaskListener taskListener : listeners) {
			taskListener.before(plan);
		}
	}

	@Override
	public void notifyEnd(WorkPlan plan) {
		for (TaskListener taskListener : listeners) {
			taskListener.end(plan);
		}
	}

}
