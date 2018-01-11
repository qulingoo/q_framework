package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.Reference;
import com.shuyun.core.mybatis.Provider;

public interface FieldReferenceDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(Reference reference);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(Reference reference);
	@Delete("delete from field_reference where id=#{id}")
	public void delete(String id);
	@Delete("delete from field_reference where field_id=#{id}")
	public void deleteByFieldId(String id);
	@Select("select * from field_table where id=#{id}")
	public Reference findById(String id);
	@Select("select * from field_reference where field_id=#{field_id}")
	public List<Reference> findByFieldId(String field_id);
}
