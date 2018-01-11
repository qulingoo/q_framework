package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.dao.MetaSegmentationDao;
import com.shuyun.core.dao.MetaSubjectDao;
import com.shuyun.core.entity.MetaSegmentation;

@Controller
@RequestMapping("/seg")
public class MetaSegmentationController {
	@Autowired
	private MetaSegmentationDao segmentationDao;
	@Autowired
	private MetaSubjectDao metaSubjectDao;

	@ResponseBody
	@RequestMapping("/add")
	public void add(MetaSegmentation seg) {
		segmentationDao.insert(seg);
	}

	@ResponseBody
	@RequestMapping("/update")
	public void update(MetaSegmentation seg) {
		segmentationDao.update(seg);
	}

	@ResponseBody
	@RequestMapping("/delete")
	@Transactional
	public void delete(String id) {
		metaSubjectDao.delete(id);
		segmentationDao.delete(id);
	}

	@ResponseBody
	@RequestMapping("/get")
	public MetaSegmentation get(String id) {
		return segmentationDao.findById(id);
	}

	@ResponseBody
	@RequestMapping("/getAll")
	public List<MetaSegmentation> getAll() {
		return segmentationDao.findAll();
	}
}
