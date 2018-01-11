package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.MetaSubject;
import com.shuyun.core.mybatis.Provider;

public interface MetaSubjectDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(MetaSubject fun);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(MetaSubject fun);
	@Delete("delete from meta_subject where db_id=#{id}  or seg_id=#{id}")
	public void delete(String id);
	@Select("select * from meta_subject where db_id=#{id}")
	public MetaSubject findById(String id);
	@Select("select * from meta_Subject")
	public List<MetaSubject> findAll();
	@Select("select * from meta_Subject where seg_id=#{id}")
	public List<MetaSubject> findBySegId(String id);
}
