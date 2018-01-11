package com.shuyun.core.bize;

import java.util.List;

import com.shuyun.core.entity.Field;

public interface IFieldBize {
	public void insert(Field field);
	public void update(Field field);
	public void delete(String id);
	public void deleteByTableId(String table_id);
	public Field findById(String id);
	public List<Field> findByTableId(String table_id);
}
