package com.shuyun.core.bize;

import java.util.List;

import com.shuyun.core.entity.Package;

public interface IPackageBize {
	public void insert(Package pack);
	public void update(Package reference);
	public void delete(String id);
	public Package findById(String id);
	public List<Package> findByParentId(String parent_pakc_id);
}
