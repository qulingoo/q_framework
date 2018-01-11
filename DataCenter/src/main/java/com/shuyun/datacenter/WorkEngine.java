package com.shuyun.datacenter;

import com.alibaba.fastjson.JSONObject;
import com.shuyun.datacenter.topology.StartNode;

/**
 * 工作引擎
 * 
 * @author qlk
 *
 */
public class WorkEngine {
	private TaskPlanning planning;

	public WorkEngine(TaskPlanning planning) {
		this.planning = planning;
	}

	public JSONObject work(TaskPlanning planning) {
		JSONObject topology = planning.getJSONObject("topology");
		JSONObject start = topology.getJSONObject("start");
		execute(start);
		return null;
	}

	public void execute(JSONObject start) {
		try {
			Class<?> startNodeClass = Class.forName(start.getString("class"));
			StartNode startNode = (StartNode) startNodeClass.newInstance();
			startNode.setName(start.getString("name"));
//			startNode.setDataSource(DatabaseConnectionPool.instance().getDataSource(planning.getJSONObject("source")));
			startNode.setPlann(planning);
			startNode.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
