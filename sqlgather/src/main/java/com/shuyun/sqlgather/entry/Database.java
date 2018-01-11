package com.shuyun.sqlgather.entry;

import com.shuyun.sqlgather.entry.datasource.Propertie;

public class Database {
	private String url;
	private String driver;
	private String password;
	private String username;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Database init(Propertie config) {
		this.setDriver(config.get("driver"));;
		this.setUrl(config.get("url"));
		this.setPassword(config.get("password"));
		this.setUsername(config.get("username"));
		return this;
	}
	
	
}
