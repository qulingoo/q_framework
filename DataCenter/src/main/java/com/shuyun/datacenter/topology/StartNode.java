package com.shuyun.datacenter.topology;

import javax.sql.DataSource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.shuyun.datacenter.DatabaseConnectionPool;

public abstract class StartNode extends Node {
	private JSONObject links;
	private JSONObject nodes;
	public DataSource ds;

	@Override
	public JSONObject execute(JSONObject data) {
		buildDataSource();
		links = getPlann().getJSONObject("topology").getJSONObject("links");
		nodes = getPlann().getJSONObject("topology").getJSONObject("nodes");
		extract();
		return null;
	}

	public abstract void extract();

	public void executed(String linkName, JSONObject data) {
		JSONArray $links = links.getJSONArray(linkName);
		for (Object nodeName : $links) {
			JSONObject $node = nodes.getJSONObject(nodeName.toString());
			try {
				Class<?> nodeClass = Class.forName($node.getString("class"));
				Node node = (Node) nodeClass.newInstance();
				node.setName(nodeName.toString());
				node.setPlann(getPlann());
				JSONObject executeDate = node.execute(data);
				if (links.getString(nodeName.toString()) != null) {
					executed(nodeName.toString(), executeDate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setDataSource(DataSource ds) {
			this.ds=ds;
	}
	public void buildDataSource( ) {
		ComboPooledDataSource ds = DatabaseConnectionPool.instance().createConnection(plann.getJSONObject("source"));
		this.ds=ds;
	}
	public String getSql(int page,int size) {
		JSONObject source = plann.getJSONObject("source");
		String fields=source.getString("fields");
		String table=source.getString("table");
		String dateField=source.getString("date-field");
		String pageSplit=source.getString("page-split");
		String begin=getPlann().getString("begin-time");
		String end=getPlann().getString("end-time");
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
		if("top".equals(pageSplit)) {
			sql.append("top "+(page-1)*size).append(","+page*size);
		}
		sql.append(" ").append(fields).append(" ");
		if("rownum".equals(pageSplit)) {
			 sql.append(",rownum");
		}
		sql.append(" from ").append(table);
		sql.append(" where ");
		if("rownum".equals(pageSplit)) {
			 sql.append("rownum >=").append(""+(page-1)*size).append(" and ").append("rownum <=").append(""+page*size).append(" and ");
		}
		sql.append(dateField).append(">= '"+begin+"'").append(" and ");
		sql.append(dateField).append("<= '"+end+"' ");
		if("limit".equals(pageSplit)) {
			sql.append(" limit "+(page-1)*size).append(","+size);
		}
		return sql.toString();
	}

}
