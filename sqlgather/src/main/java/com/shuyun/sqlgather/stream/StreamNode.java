package com.shuyun.sqlgather.stream;

import java.util.Map;


/**
 * 流执行节点实现
 * 
 * @author qlk
 *
 */
public class StreamNode implements StreamExecuteNode {
	public ExecuteContext context;
	public String name;
	public StreamNode() {
		super();
	}

	public StreamNode(ExecuteContext context) {
		super();
		this.context = context;
	}

	@Override
	public Map<String, Object> execute(Map<String, Object> t) {
		return t;
	}


	@Override
	public void setContext(ExecuteContext context) {
		this.context = context;
	}

	@Override
	public String getName() {
		
		return this.name;
	}

	@Override
	public void setName(String name) {
			this.name=name;
	}

	@Override
	public String toString() {
		return "StreamNode [context=" + context + ", name=" + name + "]";
	}

	@Override
	public void close() {
		
	}

}
