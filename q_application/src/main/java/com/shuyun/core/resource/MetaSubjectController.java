package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.dao.MetaSubjectDao;
import com.shuyun.core.entity.MetaSubject;

@Controller
@RequestMapping("/subject")
public class MetaSubjectController {
	@Autowired
	private MetaSubjectDao metaSubjectDao;
	 
	@ResponseBody
	@RequestMapping("/add")
	public void add(MetaSubject subject) {
		metaSubjectDao.insert(subject);
	}
	 
	@ResponseBody
	@RequestMapping("/update")
	public void update(MetaSubject subject) {
		metaSubjectDao.update(subject);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		metaSubjectDao.delete(id);
	}
	
	@ResponseBody
	@RequestMapping("/get")
	public MetaSubject get(String id) {
		return metaSubjectDao.findById(id);
	}
	@ResponseBody
	@RequestMapping("/getAll")
	public List<MetaSubject> getAll( ) {
		return metaSubjectDao.findAll();
	}
	@ResponseBody
	@RequestMapping("/findBySegId")
	public List<MetaSubject> findBySegId(String id ) {
		return metaSubjectDao.findBySegId(id);
	}
}

