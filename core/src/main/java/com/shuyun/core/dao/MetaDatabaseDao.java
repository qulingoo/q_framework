package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.MetaDatabase;
import com.shuyun.core.mybatis.Provider;

public interface MetaDatabaseDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(MetaDatabase db);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(MetaDatabase db);
	@Delete("delete from meta_database where db_id=#{_id}")
	public void delete(String _id);
	@Select("select * from meta_database where db_id=#{_id}")
	public MetaDatabase findById(String _id);
	@Select("select * from meta_database")
	public List<MetaDatabase> findAll();
	
}
