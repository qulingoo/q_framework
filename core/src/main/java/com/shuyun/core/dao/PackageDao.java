package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.Package;
import com.shuyun.core.mybatis.Provider;

public interface PackageDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(Package pack);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(Package reference);
	@Delete("delete from package where pack_id=#{id}")
	public void delete(String id);
	@Select("select * from package where pack_id=#{id}")
	public Package findById(String id);
	@Select("select * from package where parent_pack_id=#{parent_pack_id}")
	public List<Package> findByParentId(String parent_pack_id);
}
