package com.shuyun.datacenter.topology;

import com.alibaba.fastjson.JSONObject;
import com.shuyun.datacenter.TaskPlanning;

public abstract class Node  {
	private String name;
	public TaskPlanning plann;
	public abstract JSONObject execute(JSONObject data);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TaskPlanning getPlann() {
		return plann;
	}
	public void setPlann(TaskPlanning plann) {
		this.plann = plann;
	}
	

}
