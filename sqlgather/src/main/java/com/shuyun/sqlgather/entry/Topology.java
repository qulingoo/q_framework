package com.shuyun.sqlgather.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.shuyun.sqlgather.stream.ExecuteNode;

public class Topology {
	private Map<String, ExecuteNode<?, ?>> nodes;
	private Map<String, List<String>> links;

	public Topology() {
		super();
		nodes = new HashMap<>();
		links = new HashMap<>();
	}

	public Map<String, ExecuteNode<?, ?>> getNodes() {
		return nodes;
	}
	public void setNodes(Map<String, Map<String, String>> nodes) {
		for (Entry<String, Map<String, String>> entry : nodes.entrySet()) {
			String key = entry.getKey();
			Map<String, String> value = entry.getValue();
			try {
				ExecuteNode<?, ?> newInstance = (ExecuteNode<?, ?>) Class.forName(value.get("class")).newInstance();
				newInstance.setName(key);
				this.nodes.put(key, newInstance);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public Map<String, List<String>> getLinks() {
		return links;
	}

	public void setLinks(Map<String, List<String>> links) {
		this.links = links;
	}

	public void setNode(ExecuteNode<?, ?> node) {
		nodes.put(node.getName(), node);
	}

	public void link(String s, String e) {
		List<String> list = links.get(s);
		if (list == null) {
			list = new ArrayList<>();
			links.put(s, list);
		}
		list.add(e);

	}

	public String getRoot() {
		return links.get("null").get(0);
	}

	public ExecuteNode<?, ?> getRootExecuteNode() {
		String root = getRoot();

		return nodes.get(root);

	}

	public boolean isLast(ExecuteNode<?, ?> node) {
		List<String> link = this.links.get(node.getName());
		return link == null;
	}

	public List<ExecuteNode<?, ?>> getLinkNode(String name) {
		List<String> list = this.links.get(name);
		List<ExecuteNode<?, ?>> node = new ArrayList<>();
		if (list != null) {
			for (String link : list) {
				ExecuteNode<?, ?> node2 = nodes.get(link);
				node.add(node2);
			}
		}
		return node;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
