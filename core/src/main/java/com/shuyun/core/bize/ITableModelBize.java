package com.shuyun.core.bize;

import java.util.List;

import com.shuyun.core.entity.TableModel;

public interface ITableModelBize {
	public void insert(TableModel tableModel);
	public void update(TableModel tableModel);
	public void delete(String id);
	public TableModel findById(String id);
	List<TableModel> findByPackId(String pack_id);
	public List<TableModel> findByPage(int page);
	public int count();
}
