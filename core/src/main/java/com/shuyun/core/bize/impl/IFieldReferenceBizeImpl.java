package com.shuyun.core.bize.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuyun.core.bize.IFieldReferenceBize;
import com.shuyun.core.dao.FieldReferenceDao;
import com.shuyun.core.entity.Reference;
@Service("iFieldReferenceBize")
public class IFieldReferenceBizeImpl implements IFieldReferenceBize{
	@Autowired
	private FieldReferenceDao fieldReferenceDao;
	@Override
	public void insert(Reference reference) {
		fieldReferenceDao.insert(reference);		
	}

	@Override
	public void update(Reference reference) {
		fieldReferenceDao.update(reference);		
	}

	@Override
	public void delete(String id) {
		fieldReferenceDao.delete(id);		
	}

	@Override
	public void deleteByFieldId(String id) {
		fieldReferenceDao.deleteByFieldId(id);		
	}

	@Override
	public Reference findById(String id) {
		return fieldReferenceDao.findById(id);
	}

	 

	@Override
	public List<Reference> findByFieldId(String field_id) {
		// TODO Auto-generated method stub
		return fieldReferenceDao.findByFieldId(field_id);
	}

}
