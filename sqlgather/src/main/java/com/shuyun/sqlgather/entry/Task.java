package com.shuyun.sqlgather.entry;

import com.alibaba.fastjson.JSONObject;

public class Task {
	private String name;
	private JSONObject taskData;
	private String cron;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JSONObject getTaskData() {
		return taskData;
	}
	public void setTaskData(JSONObject taskData) {
		this.taskData = taskData;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	
}
