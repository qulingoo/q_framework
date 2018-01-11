package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.MetaFunction;
import com.shuyun.core.mybatis.Provider;

public interface MetaFunctionDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(MetaFunction fun);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(MetaFunction fun);
	@Delete("delete from meta_function where db_id=#{id}")
	public void delete(String id);
	@Select("select * from meta_function where db_id=#{id}")
	public MetaFunction findById(String id);
	@Select("select * from meta_function")
	public List<MetaFunction> findAll();
}
