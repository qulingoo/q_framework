package com.shuyun.sqlgather.stream;

/**
 * 抽象
 * 
 * @author Machenike
 *
 * @param <T>
 * @param <R>
 */
public interface ExecuteNode<T, R> {
	/**
	 * 
	 * @param t 上一个节点产生的数据
	 * @return 传入给下一个节点的数据
	 */
	public R execute(T t);

	public String getName();
	public void setName(String name);
	public void setContext(ExecuteContext context);
	public void close() ;

}
