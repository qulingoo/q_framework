package com.shuyun.core.bize.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuyun.core.bize.IPackageBize;
import com.shuyun.core.dao.PackageDao;
import com.shuyun.core.entity.Package;
@Service("iPackageBzie")
public class IPackageBizeImpl implements IPackageBize {
	@Autowired
	PackageDao packageDao;
	@Override
	public void insert(Package pack) {
		packageDao.insert(pack);
	}

	@Override
	public void update(Package reference) {
		packageDao.update(reference);
	}

	@Override
	public void delete(String id) {
		packageDao.delete(id);
	}

	@Override
	public Package findById(String id) {
		return packageDao.findById(id);
	}

	@Override
	public List<Package> findByParentId(String parent_pakc_id) {
		return packageDao.findByParentId(parent_pakc_id);
	}

}
