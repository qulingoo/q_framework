package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.datasource.dao.DataSourceDao;
import com.shuyun.datasource.entity.DataSource;

@Controller
@RequestMapping("/ds")
public class DataSourceController  {
	@Autowired
	private DataSourceDao  dataSourceDao;

	@ResponseBody
	@RequestMapping("/insert")
	public void insert(DataSource ds) {
		dataSourceDao.insert(ds);
	}

	@ResponseBody
	@RequestMapping("/update")
	public void update(DataSource ds) {
		dataSourceDao.update(ds);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		dataSourceDao.delete(id);
	}
 

	@ResponseBody
	@RequestMapping("/findById")
	public DataSource findById(String id) {
		return dataSourceDao.findById(id);
	}
	@ResponseBody
	@RequestMapping("/findByType")	
	public List<DataSource> findByType(String type) {
		return dataSourceDao.findByType(type);
	}
	
}
