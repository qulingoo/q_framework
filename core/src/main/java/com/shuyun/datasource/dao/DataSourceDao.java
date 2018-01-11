package com.shuyun.datasource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.mybatis.Provider;
import com.shuyun.datasource.entity.DataSource;

public interface DataSourceDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(DataSource ds);

	@InsertProvider(type = Provider.class, method = "update")
	public void update(DataSource ds);

	@Delete("delete from datasource where id=#{id}")
	public void delete(String id);

	@Select("select * from datasource where id=#{id}")
	public DataSource findById(String id);

	@Select("select * from datasource where type=#{type}")
	public List<DataSource> findByType(String type);

}
