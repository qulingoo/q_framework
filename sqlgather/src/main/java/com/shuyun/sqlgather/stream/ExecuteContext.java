package com.shuyun.sqlgather.stream;

import com.shuyun.sqlgather.entry.Topology;
import com.shuyun.sqlgather.entry.datasource.Propertie;

public class ExecuteContext {
	private Topology topology;
	private Propertie config; 
 
	public ExecuteContext() {
		super();
	}
	public ExecuteContext(Propertie config) {
		this.config=config;
		
	}
	public Topology getTopology() {
		return topology;
	}
	public void setTopology(Topology topology) {
		this.topology = topology;
	}
 
	 
	public Propertie getConfig() {
		return config;
	}
	public void setConfig(Propertie config) {
		this.config = config;
	}
	 
	@Override
	public String toString() {
		return "ExecuteContext [topology=" + topology + ", config=" + config + "]";
	}
	
}
