package com.shuyun.sqlgather.stream;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.shuyun.sqlgather.entry.Database;
import com.shuyun.sqlgather.entry.datasource.Propertie;
import com.shuyun.sqlgather.simple.DatabaseConnectionPool;
import com.shuyun.sqlgather.util.Util;

public class DBStreamSaveNode extends StreamNode {
	private Connection conn;
	public DBStreamSaveNode() {
		super();
		
	}

	@Override
	public Map<String, Object> execute(Map<String, Object> t) {
		
		 String sql = Util.getInsertSql(t,context.getConfig().get("table"));
//		 System.out.println(sql);
		 Statement stmt =null;
		 
		 try {
			 getConn();
			  stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}

	private void getConn() throws SQLException {
		synchronized (DBStreamSaveNode.class) {
			if(conn==null) {
				Propertie config = context.getConfig();
				Database database = new Database();
				database.setDriver(config.get("target.driver"));
				database.setUrl(config.get("target.url"));
				database.setUsername(config.get("target.username"));
				database.setPassword(config.get("target.password"));
				conn = DatabaseConnectionPool.instance().getConnection(database);
			}
		}
		
	}
	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
