package com.shuyun.core.bize.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuyun.core.bize.IFieldBize;
import com.shuyun.core.dao.FieldDao;
import com.shuyun.core.dao.FieldReferenceDao;
import com.shuyun.core.entity.Field;
@Service("iFieldBize")
public class IFieldBizeImpl implements IFieldBize {
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private FieldReferenceDao fieldReferenceDao;
	@Override
	public void insert(Field field) {
		fieldDao.insert(field);
	}

	@Override
	public void update(Field field) {
		fieldDao.update(field);
	}

	@Override
	public void delete(String id) {
		fieldDao.delete(id);
		fieldReferenceDao.deleteByFieldId(id);
	}

	@Override
	public void deleteByTableId(String table_id) {
		fieldDao.deleteByTableId(table_id);
	}

	@Override
	public Field findById(String id) {
		return fieldDao.findById(id);
	}

	public Field findAll() {
		// TODO Auto-generated method stub
		return fieldDao.findAll();
	}

	@Override
	public List<Field> findByTableId(String table_id) {
		return fieldDao.findByTableId(table_id);
	}

}
