package com.shuyun.sqlgather.entry.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shuyun.sqlgather.entry.Database;
import com.shuyun.sqlgather.entry.Topology;
import com.shuyun.sqlgather.simple.DatabaseConnectionPool;
import com.shuyun.sqlgather.stream.ExecuteContext;
import com.shuyun.sqlgather.stream.ExecuteNode;
import com.shuyun.sqlgather.util.Util;

/**
 * 数据库数据来源
 * 
 * @author qlk
 *
 */
@SuppressWarnings("unchecked")
public class DatabaseSource extends Source<Map<String, Object>> {
	private final Logger log = Logger.getLogger(DatabaseSource.class);
	private Connection ds;
	private ResultSet rs;
	private ResultSetMetaData metaData;

	public DatabaseSource(ExecuteContext context) {
		log.info("初始化数据源，配置信息：" + context.getConfig());
		setContext(context);
		init(context.getConfig());
	}

	@Override
	public void init(Propertie config) {
		Database database = new Database().init(config);
		try {
			ds = DatabaseConnectionPool.instance().getConnection(database);
			PreparedStatement prep = ds.prepareStatement(config.get("sql"),ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			prep.setFetchSize(Integer.MIN_VALUE);  
			prep.setFetchDirection(ResultSet.FETCH_REVERSE);  
			String paramJSON = config.get("sql.param");
			if (paramJSON == null) {
				paramJSON = "[]";
			}
			JSONArray parseArray = JSON.parseArray(paramJSON);
			for (int i = 1; i <= parseArray.size(); i++) {
				prep.setObject(i, parseArray.get(i - 1));
			}
			rs = prep.executeQuery();
			
			metaData = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> get() throws Exception {
		Map<String, Object> map = null;
		if (rs.next()) {
			map = new HashMap<>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				map.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
		}
		return map;
	}

	@Override
	public void put(Map<String, Object> data) throws Exception {
		String tableName = getConfig().get("table");
		ds.createStatement().executeUpdate(Util.getInsertSql(data, tableName));
	}

	@Override
	public List<Map<String, Object>> batchGet(int size) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Map<String, Object> data = get();
			if (data != null) {
				list.add(data);
			} else {
				break;
			}
		}
		return list;
	}

	@Override
	public void batchPut(List<Map<String, Object>> datas) throws Exception {
		ds.setAutoCommit(false);
		for (Map<String, Object> data : datas) {
			put(data);
		}
		ds.commit();
	}

	@Override
	public void close() throws Exception {
		ds.close();
	}

	@Override
	public void shunt() {
		log.info("数据分流开始");
		Topology topology = context.getTopology();
		log.debug("拓扑配置信息：" + topology);
		ExecuteNode<?, ?> rootExecuteNode = topology.getRootExecuteNode();
		ExecuteNode<Map<String, Object>, Map<String, Object>> root = (ExecuteNode<Map<String, Object>, Map<String, Object>>) rootExecuteNode;
		root.setContext(context);
		log.info(root.getName() + "数据分流开始");
		while (true) {
			try {
				Map<String, Object> map = get();
				if (map == null) {
					break;
				}
				execute(map, root, topology);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			log.info(root.getName() + "节点数据分流结束");
			log.info(root.getName() + "关闭数据库连接");
			ds.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 递归执行
	 * 
	 * @param data
	 * @param node
	 * @param topology
	 * @throws Exception
	 */
	private void execute(Map<String, Object> data, ExecuteNode<Map<String, Object>, Map<String, Object>> node,
			Topology topology) throws Exception {
		log.debug("当前执行节点：" + node.getName());
		log.debug("当前节点信息：" + node);
		if (node != null) {
			node.setContext(context);
			Map<String, Object> returnData = node.execute(data);
			List<ExecuteNode<?, ?>> linkNode = topology.getLinkNode(node.getName());
			for (ExecuteNode<?, ?> executeNode : linkNode) {
				execute(returnData, (ExecuteNode<Map<String, Object>, Map<String, Object>>) executeNode, topology);
			}
		}

	}

}
