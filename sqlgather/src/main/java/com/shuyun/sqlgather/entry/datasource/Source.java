package com.shuyun.sqlgather.entry.datasource;

import java.util.List;

import com.shuyun.sqlgather.stream.ExecuteContext;
/**
 * 抽象数据源
 * @author qlk
 *
 * @param <T> 数据源范围数据类型
 */
public abstract class Source<T> {
	
	public ExecuteContext context;

	public abstract void init(Propertie config);

	public abstract T get() throws Exception;

	public abstract void put(T t) throws Exception;

	public abstract List<T> batchGet(int size) throws Exception;

	public abstract void batchPut(List<T> datas) throws Exception;

	public abstract void close() throws Exception;

	public Propertie getConfig() {
		return this.context.getConfig();
	}


	public abstract void shunt();

	public  void setContext(ExecuteContext context) {
		this.context=context;
	}

}
