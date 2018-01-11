package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.MetaDatabaseRef;
import com.shuyun.core.mybatis.Provider;

public interface MetaDatabaseRefDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(MetaDatabaseRef ref);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(MetaDatabaseRef ref);
	@Delete("delete from meta_database_ref where db_id=#{db_id}")
	public void delete(String db_id);
	@Select("select * from meta_database_ref where db_id=#{db_id}")
	public List<MetaDatabaseRef> findById(String db_id);
	@Select("select * from meta_database_ref")
	public List<MetaDatabaseRef> findAll();
}
