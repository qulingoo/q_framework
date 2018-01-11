package com.shuyun.core.bize;

import java.util.List;

import com.shuyun.core.entity.Reference;

public interface IFieldReferenceBize {
	public void insert(Reference reference);
	public void update(Reference reference);
	public void delete(String id);
	public void deleteByFieldId(String id);
	public Reference findById(String id);
	public List<Reference> findByFieldId(String field_id);
}
