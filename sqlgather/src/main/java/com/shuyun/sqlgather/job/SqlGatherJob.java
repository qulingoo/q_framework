package com.shuyun.sqlgather.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuyun.sqlgather.entry.GatherInfo;
import com.shuyun.sqlgather.simple.DatabaseConnectionPool;
import com.shuyun.sqlgather.util.Util;

public class SqlGatherJob implements Job {
	Logger log =Logger.getLogger(SqlGatherJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		long begin = System.currentTimeMillis();
		log.info("开始sql采集任务");
		log.info("当前时间："+Util.getNowTimeStr());
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JSONObject gatherJSON = (JSONObject) jobDataMap.get("sqlgather");
		GatherInfo gather = JSON.toJavaObject(gatherJSON, GatherInfo.class);
		log.info("上次最后更新数据时间："+gather.getLastTime());
		log.info("执行采集sql："+gather.getSql());
		log.info("数据采集目标表："+gather.getTargetTable());
		Connection tarconn = null;
		Connection sourceConn = null;
		try {
			sourceConn = DatabaseConnectionPool.instance().getConnection(gather.getSource());
			tarconn = DatabaseConnectionPool.instance().getConnection(gather.getTarget());
			List<Map<String, Object>> datas = extract(sourceConn, gather);
			List<Map<String, Object>> errorData = load(tarconn, gather, datas);
			errorData.size();
		} catch (SQLException e) {
			try {
				tarconn.close();
				sourceConn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("sql采集执行完成，执行耗时为："+(System.currentTimeMillis()-begin)+"ms");
		

	}

	public List<Map<String, Object>> extract(Connection conn, GatherInfo gather) throws SQLException {
		PreparedStatement prep = conn.prepareStatement(gather.getSql());
		prep.setString(1, gather.getLastTime());
		prep.setString(2, Util.getNowTimeStr());
		ResultSet rs = prep.executeQuery();
		List<Map<String, Object>> dataMap = Util.getDataMap(rs);
		return dataMap;
	}
	public List<Map<String,Object>> load(Connection conn, GatherInfo gather,List<Map<String,Object>> datas)  {
		List<Map<String,Object>> list=new ArrayList<>();
		for (Map<String, Object> data : datas) {
				String sql = Util.getInsertSql(data, gather.getTargetTable());
				try {
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
					Map<String,Object> message=new HashMap<>();
					message.put("data", JSON.toJSONString(data));
					message.put("message", e.getMessage());
					list.add(message);
					log.error(message);
				}
		}
		return list;
		
	}
	
	
	 
}
