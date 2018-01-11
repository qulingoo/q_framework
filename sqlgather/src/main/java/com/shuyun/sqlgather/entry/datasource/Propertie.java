package com.shuyun.sqlgather.entry.datasource;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class Propertie extends HashMap<String,String>{
	private static final long serialVersionUID = -5460679830408432443L;
	public Propertie() {
		super();
	}
	public Propertie(JSONObject obj) {
		super();
		for (Entry<String, Object> entry : obj.entrySet()) {
			this.put(entry.getKey(),entry.getValue()+"");
		}
		
	}
	public Propertie split(String...keys) {
		Propertie propertie = new Propertie();
		for (String key : keys) {
			propertie.put(key.split("\\.")[1], this.get(key));
		}
		return propertie;
	}
}
