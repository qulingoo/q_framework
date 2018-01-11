package com.shuyun.config;


public interface Configure {
	/**
	 * 通过key获取值
	 * @param key
	 * @return
	 */
	public Object get(String key);
	/**
	 * 通过key获取值字符串
	 * @param key
	 * @return
	 */
	public String getString(String key);
	/**
	 * 设置值
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value);
	/**
	 * 判断值是否存在
	 * @param key
	 * @return
	 */
	public boolean exist(String key);
}