package com.shuyun.sqlgather.simple;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.shuyun.sqlgather.entry.Database;
/**
 * 
 * @author qlk
 *
 */
public class DatabaseConnectionPool {
	private Map<String, ComboPooledDataSource> pools = new HashMap<>();
	private static DatabaseConnectionPool connPool;
	private DatabaseConnectionPool() {
		
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
	public Connection getConnection(Database db) throws SQLException {
		ComboPooledDataSource pool = pools.get(db.getUrl());
		synchronized (DatabaseConnectionPool.class) {
			if (pool == null) {
				pool = createConnection(db);
			}
		}
		return pool.getConnection();
	}
	public DataSource getDataSource(Database db) {
		ComboPooledDataSource pool = pools.get(db.getUrl());
		synchronized (DatabaseConnectionPool.class) {
			if (pool == null) {
				pool = createConnection(db);
			}
		}
		return  pool ;
	}
	/**
	 * 创建数据库链接
	 * @param db
	 * @return
	 */
	private  ComboPooledDataSource createConnection(Database db) {
		ComboPooledDataSource ds = new ComboPooledDataSource(); 
		try {
			ds.setDriverClass(db.getDriver());
			ds.setJdbcUrl(db.getUrl()); 
			ds.setUser(db.getUsername()); 
			ds.setPassword(db.getPassword()); 
			ds.setMaxPoolSize(20); 
			ds.setMinPoolSize(2); 
			ds.setInitialPoolSize(5); 
			ds.setMaxStatements(100); 
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return ds;

	}
}
