package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.TableModel;
import com.shuyun.core.mybatis.Provider;

public interface TableModelDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(TableModel tableModel);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(TableModel tableModel);
	@Delete("delete from table_model where id=#{id}")
	public void delete(String id);
	@Select("select * from table_model where id=#{id}")
	public TableModel findById(String id);
	@Select("select * from table_model where pack_id=#{pack_id}")
	public List<TableModel> findByPackId(String pack_id);
	@Select("select count(*) from table_model")
	public  int count();
	@Select("select * from table_model limit #{page},50")
	public  List<TableModel> findByPage(int page ); 
	
	
}
