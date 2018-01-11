package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.dao.MetaSubjectNormalDao;
import com.shuyun.core.entity.MetaSubjectNormal;
import com.shuyun.core.entity.MetaSubjectNormalRef;

@Controller
@RequestMapping("/subject/normal")
public class MetaSubjectNormalController {
	@Autowired
	private MetaSubjectNormalDao metaSubjectNormalDao;

	@ResponseBody
	@RequestMapping("/add")
	public void insert(MetaSubjectNormal nomal) {
		metaSubjectNormalDao.insert(nomal);
	}

	@ResponseBody
	@RequestMapping("/addRef")
	public void insertFieldRef(MetaSubjectNormalRef ref) {
		metaSubjectNormalDao.insertFieldRef(ref);
	}

	@ResponseBody
	@RequestMapping("/update")
	public void update(MetaSubjectNormal nomal) {
		metaSubjectNormalDao.update(nomal);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		metaSubjectNormalDao.deleteRef(id);
		metaSubjectNormalDao.delete(id);
	}

	@ResponseBody
	@RequestMapping("/get")
	public MetaSubjectNormal findById(String id) {
		return metaSubjectNormalDao.findById(id);
	}

	@ResponseBody
	@RequestMapping("/getAll")
	public List<MetaSubjectNormal> findAll() {
		return metaSubjectNormalDao.findAll();
	}

	@ResponseBody
	@RequestMapping("/getAllRef")
	public List<MetaSubjectNormalRef> findAllRef(String id) {
		return metaSubjectNormalDao.findAllRef(id);
	}

	@ResponseBody
	@RequestMapping("/getBySubject")
	public List<MetaSubjectNormal> findBySubject(String id) {
		return metaSubjectNormalDao.findBySubject(id);
	}

}
