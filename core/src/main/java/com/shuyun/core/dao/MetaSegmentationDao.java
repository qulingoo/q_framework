package com.shuyun.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.shuyun.core.entity.MetaSegmentation;
import com.shuyun.core.mybatis.Provider;

public interface MetaSegmentationDao {
	@InsertProvider(type = Provider.class, method = "insert")
	public void insert(MetaSegmentation seg);
	@InsertProvider(type = Provider.class, method = "update")
	public void update(MetaSegmentation seg);
	@Delete("delete from meta_segmentation where db_id=#{id}")
	public void delete(String id);
	@Select("select * from meta_segmentation where db_id=#{id}")
	public MetaSegmentation findById(String id);
	@Select("select * from meta_segmentation")
	public List<MetaSegmentation> findAll();
}
