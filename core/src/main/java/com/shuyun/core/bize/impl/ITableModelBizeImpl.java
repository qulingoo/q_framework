package com.shuyun.core.bize.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuyun.core.bize.ITableModelBize;
import com.shuyun.core.dao.FieldDao;
import com.shuyun.core.dao.FieldReferenceDao;
import com.shuyun.core.dao.TableModelDao;
import com.shuyun.core.entity.Field;
import com.shuyun.core.entity.TableModel;

@Service("iTableModelBize")
public class ITableModelBizeImpl implements ITableModelBize {
	@Autowired
	private TableModelDao tableModelDao;
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private FieldReferenceDao fieldReferenceDao;
 
	@Override
	public void insert(TableModel tableModel) {
		tableModelDao.insert(tableModel);
	}

	@Override
	public void update(TableModel tableModel) {
		tableModelDao.update(tableModel);
	}

	@Override
	public void delete(String id) {
		tableModelDao.delete(id);
		List<Field> fields = fieldDao.findByTableId(id);
		for (Field field : fields) {
			fieldReferenceDao.deleteByFieldId(field.getId());
		}
		fieldDao.deleteByTableId(id);
	}

	@Override
	public TableModel findById(String id) {
		return tableModelDao.findById(id);
	}

	@Override
	public List<TableModel> findByPackId(String pack_id) {
		return tableModelDao.findByPackId(pack_id);
	}

	@Override
	public List<TableModel> findByPage(int page) {
		return tableModelDao.findByPage(page);
	}

	@Override
	public int count() {
		return tableModelDao.count();
	}

}
