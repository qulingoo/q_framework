package com.shuyun.sqlgather.entry.datasource;

import com.shuyun.sqlgather.stream.ExecuteContext;

public class SourceFactory {
	public static Source<?> getDataSource(String type,ExecuteContext context){
		switch (type) {
		case "db":
			return new DatabaseSource(context);

		default:
			return null;
		}
	}
}
