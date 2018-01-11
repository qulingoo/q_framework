package com.shuyun.sqlgather.entry;

public class GatherInfo {
	private Database source;
	private Database target;
	private String targetTable;
	private String sql;
	private String lastTime;
	private int status;
	public String getTargetTable() {
		return targetTable;
	}
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
	public Database getSource() {
		return source;
	}
	public void setSource(Database source) {
		this.source = source;
	}
	public Database getTarget() {
		return target;
	}
	public void setTarget(Database target) {
		this.target = target;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	 
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
