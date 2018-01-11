package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.dao.MetaFunctionDao;
import com.shuyun.core.entity.MetaFunction;

@Controller
@RequestMapping("/function")
public class MetaFunctionController {
	@Autowired
	private MetaFunctionDao metaFunctionDao;
	@ResponseBody
	@RequestMapping("/add")
	public void add(MetaFunction fun) {
		metaFunctionDao.insert(fun);
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(MetaFunction fun) {
		metaFunctionDao.update(fun);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		metaFunctionDao.delete(id);
	}
	
	@ResponseBody
	@RequestMapping("/get")
	public MetaFunction get(String id) {
		return metaFunctionDao.findById(id);
	}
	@ResponseBody
	@RequestMapping("/getAll")
	public List<MetaFunction> getAll( ) {
		return metaFunctionDao.findAll();
	}
	
}
