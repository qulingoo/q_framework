package com.shuyun.rest;

import java.util.HashMap;
import java.util.Map;




public class RestMain {
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
		java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
		args=new String[] {"getTable","123"};
		if(args.length==0) {
			System.out.println("参数异常");
			System.out.println("getTable:获取表");
			return ;
		}
		switch (args[0]) {
		case "getTable":
			if(args.length==2) {
				Map<String,String> params=new HashMap<>();
				params.put("packId", args[1]);
				String post = RestChient.post("findByPackId", params);
				System.out.println(post);
			}
			break;

		default:
			System.out.println("请输入参数");
			break;
		}
	}
}
