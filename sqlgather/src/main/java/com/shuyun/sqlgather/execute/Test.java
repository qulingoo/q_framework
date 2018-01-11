package com.shuyun.sqlgather.execute;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.shuyun.sqlgather.entry.datasource.DatabaseSource;
import com.shuyun.sqlgather.entry.datasource.Source;
import com.shuyun.sqlgather.stream.ExecuteContext;
import com.shuyun.sqlgather.util.Util;

public class Test {
	public static void main(String[] args) {
		Util.startMonitoring();
		 String str="{\r\n" + 
		 		"	\"config\": {\r\n" + 
		 		"		\"target.driver\": \"com.mysql.jdbc.Driver\",\r\n" + 
		 		"		\"target.password\": \"root\",\r\n" + 
		 		"		\"target.username\": \"root\",\r\n" + 
		 		"		\"target.url\": \"jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useCursorFetch=true\",\r\n" + 
		 		"		\"sql\": \"select * from TSALSALEPLU201712_HY\",\r\n" + 
		 		"		\"table\": \"TSALSALEPLU201712_HY\",\r\n" + 
		 		"		\"driver\": \"com.mysql.jdbc.Driver\",\r\n" + 
		 		"		\"password\": \"root\",		 \r\n" + 
		 		"		\"username\": \"root\",		 \r\n" + 
		 		"		\"url\": \"jdbc:mysql://localhost:3306/test_qrtz?useUnicode=true&characterEncoding=utf-8&useCursorFetch=true\"\r\n" + 
		 		"	},\r\n" + 
		 		"	\"topology\": {\r\n" + 
		 		"		\"links\": {\r\n" + 
		 		"			\"null\": [\r\n" + 
		 		"				\"print\"\r\n" + 
		 		"			],\r\n" + 
		 		"			\"print\": [\r\n" + 
		 		"				\"dbsave\"\r\n" + 
		 		"			]\r\n" + 
		 		"		},\r\n" + 
		 		"		\"nodes\": {\r\n" + 
		 		"			\"print\": {\r\n" + 
		 		"				\"name\": \"print\",\r\n" + 
		 		"				\"class\":\"com.shuyun.sqlgather.stream.StreamNode\"\r\n" + 
		 		"			},\r\n" + 
		 		"			\"dbsave\": {\r\n" + 
		 		"				\"name\": \"dbsave\",\r\n" + 
		 		"				\"class\":\"com.shuyun.sqlgather.stream.DBStreamSaveNode\"\r\n" + 
		 		"			}\r\n" + 
		 		"		}\r\n" + 
		 		"	}\r\n" + 
		 		"}";
		 
		Source<Map<String,Object>> source=new DatabaseSource(JSON.parseObject(str, ExecuteContext.class));
		try {
			 source.shunt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
