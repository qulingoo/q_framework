package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.Field;
import com.shuyun.core.mybatis.Provider;

public interface FieldDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(Field field);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(Field field);
	@Delete("delete from table_field where id=#{id}")
	public void delete(String id);
	@Delete("delete from table_field where table_id=#{table_id}")
	public void deleteByTableId(String table_id);
	@Select("select * from table_field where id=#{id}")
	public Field findById(String id);
	@Select("select * from table_field")
	public Field findAll();
	@Select("select * from table_field where table_id=#{table_id} order by dindex")
	public List<Field> findByTableId(String table_id);
}
