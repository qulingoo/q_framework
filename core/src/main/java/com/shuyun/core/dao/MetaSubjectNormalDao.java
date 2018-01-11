package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import com.shuyun.core.entity.MetaSubjectNormal;
import com.shuyun.core.entity.MetaSubjectNormalRef;
import com.shuyun.core.mybatis.Provider;

public interface MetaSubjectNormalDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(MetaSubjectNormal nomal);

	@InsertProvider(type = Provider.class, method = "insert")
	public void insertFieldRef(MetaSubjectNormalRef ref);

	@InsertProvider(type = Provider.class, method = "update")
	public void update(MetaSubjectNormal nomal);

	@Delete("delete from meta_subject_normal where db_id=#{id}  or subject_id=#{id}")
	public void delete(String id);
	@Delete("delete from meta_subject_normal_ref where normal_id=#{id}   ")
	public void deleteRef(String id);

	@Select("select * from meta_subject_normal where db_id=#{id}")
	public MetaSubjectNormal findById(String id);

	@Select("select * from meta_subject_normal")
	public List<MetaSubjectNormal> findAll();
	@Select("select * from meta_subject_normal_ref where normal_id=#{id}")
	public List<MetaSubjectNormalRef> findAllRef(String id);

	@Select("select * from meta_subject_normal where subject_id=#{id}")
	public List<MetaSubjectNormal> findBySubject(String id);
}
