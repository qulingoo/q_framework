package com.shuyun.datacenter.topology;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSONObject;

public class DataBaseExtractNode extends StartNode {
	private int page = 1;
	private int pageSize = 200;

	@Override
	public void extract() {

		System.out.println("正在执行【" + getName() + "】任务");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			do {

				ResultSet rs = stmt.executeQuery(getSql(page, pageSize));
				ResultSetMetaData metaData = rs.getMetaData();
				int index = 0;
				while (rs.next()) {
					index++;
					JSONObject data = new JSONObject();
					for (int i = 1; i <= metaData.getColumnCount(); i++) {
						data.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					executed(getName(), data);
				}
				if (pageSize == index) {
					page++;
					System.out.println(page);
				} else {
					 
					break;
				}

			} while (true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					conn.close();
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		executed(getName(), null);

	}

}
