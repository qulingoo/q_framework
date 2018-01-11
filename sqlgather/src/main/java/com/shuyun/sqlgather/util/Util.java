package com.shuyun.sqlgather.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Util {
	private static ExecutorService dbThreadPool = Executors.newFixedThreadPool(10);
	public static void  execute(Runnable runn) {
		dbThreadPool.execute(runn);
	}
	public static String getNowTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static List<Map<String, Object>> getDataMap(ResultSet rs) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		ResultSetMetaData metaData = rs.getMetaData();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				map.put(metaData.getColumnLabel(i), rs.getObject(i));

			}
			list.add(map);
		}
		return list;
	}
	public static String getInsertSql(Map<String, Object> data,String table) {
		StringBuffer sql=new StringBuffer("insert into ");
		sql.append(table).append("(");
		StringBuffer values=new StringBuffer(" values(");
		Object[] array = data.keySet().toArray();
		
		for (int i = 0; i <array.length; i++) {
				sql.append(array[i]);
				Object value=data.get(array[i]);
				if(value==null) {
					values.append("null");
					if(i<array.length-1) {
						sql.append(",");
						values.append(",");
					}else {
						sql.append(") ");
						values.append(") ");
					}
				}else  if(value instanceof Number){
					values.append(value);
					if(i<array.length-1) {
						values.append(",");
						sql.append(",");
					}else {
						values.append(") ");
						sql.append(") ");
					}
				}else {
					values.append("'").append(value).append("'");
					if(i<array.length-1) {
						values.append(",");
						sql.append(",");
					}else {
						values.append(") ");
						sql.append(") ");
					}
				}
		}
		return sql.append(values).toString();
	}
	
	public static void startMonitoring() {
		execute(()->{
			System.out.println("=======================通过java来获取相关系统状态============================ ");  
			StringBuffer sb=new StringBuffer();
			while(true) {
			 
				sb=new StringBuffer();
				double i = (double)Runtime.getRuntime().totalMemory()/1024/1024;//Java 虚拟机中的内存总量,以字节为单位  
			    sb.append("总的内存量 :"+i+"MB").append("\n");
			    double j = (double)Runtime.getRuntime().freeMemory()/1024/1024;//Java 虚拟机中的空闲内存量  
			    sb.append("空闲内存量 : "+j+"MB").append("\n");
			    sb.append("使用内存量 : "+(i-j)+"MB").append("\n");
			    sb.append("最大内存量 : "+Runtime.getRuntime().maxMemory()/1024/1024+"MB");
			    try {
					Thread.sleep(11100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	public static void clear() throws AWTException
    {
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R);                    // 按下R键
        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
        r.delay(100);       

    }
}
