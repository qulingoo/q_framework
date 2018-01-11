package com.shuyun.datacenter;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.fastjson.JSONObject;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
 * @author qlk
 *
 */
public class DatabaseConnectionPool {
	private Map<String, ComboPooledDataSource> pools  ;
	private static DatabaseConnectionPool connPool;
	private DatabaseConnectionPool() {
		pools = new Hashtable<>();
	}
	public static DatabaseConnectionPool instance() {
		synchronized (DatabaseConnectionPool.class) {
			if(connPool==null) {
				connPool=new DatabaseConnectionPool();
			}
			return connPool;
		}
	}
	/**
	 * 获取数据库链接
	 * @param db
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(JSONObject source) throws SQLException {
		ComboPooledDataSource pool = pools.get(source.getString("url"));
		synchronized (DatabaseConnectionPool.class) {
			if (pool == null) {
				pool = createConnection(source);
				pools.put(source.getString("url"), pool);
			}
		}
		return pool.getConnection();
	}
	public DataSource getDataSource(JSONObject source) {
		ComboPooledDataSource pool = pools.get(source.getString("url"));
		
		synchronized (DatabaseConnectionPool.class) {
			if (pool == null) {
				pool = createConnection(source);
				pools.put(source.getString("url"), pool);
			}
			System.out.println(pools);
			return  pool ;
		}
	}
	/**
	 * 创建数据库链接
	 * @param db
	 * @return
	 */
	public  ComboPooledDataSource createConnection(JSONObject source) {
		ComboPooledDataSource ds = new ComboPooledDataSource(); 
		try {
			ds.setDriverClass(source.getString("driver"));
			ds.setJdbcUrl(source.getString("url")); 
			ds.setUser(source.getString("username")); 
			ds.setPassword(source.getString("password")); 
			ds.setMaxPoolSize(3); 
			ds.setMinPoolSize(1); 
			ds.setInitialPoolSize(2); 
			ds.setMaxStatements(100); 
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return ds;

	}
	public void destroy(String key) {
		ComboPooledDataSource ds = pools.get(key);
		ds.close();
	}
}
