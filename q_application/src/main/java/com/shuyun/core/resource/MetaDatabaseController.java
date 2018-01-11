package com.shuyun.core.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.dao.MetaDatabaseDao;
import com.shuyun.core.dao.MetaDatabaseRefDao;
import com.shuyun.core.entity.MetaDatabase;
import com.shuyun.core.entity.MetaDatabaseRef;

@Controller
@RequestMapping("/database")
public class MetaDatabaseController {
	@Autowired
	MetaDatabaseDao metaDatabaseDao;
	@Autowired
	MetaDatabaseRefDao metaDatabaseRefDao;

	@ResponseBody
	@RequestMapping("/add")
	public void addDatabase(MetaDatabase database) {
		metaDatabaseDao.insert(database);
	}

	@ResponseBody
	@RequestMapping("/addRef")
	public void addDatabase(MetaDatabaseRef ref) {
		metaDatabaseRefDao.insert(ref);
	}
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		metaDatabaseDao.delete(id);
	}
	@ResponseBody
	@RequestMapping("/getDbs")
	public List<Map<String, Object>> getAllDataBase() {
		List<Map<String, Object>> list = new ArrayList<>();
		List<MetaDatabase> dbs = metaDatabaseDao.findAll();
		for (MetaDatabase db : dbs) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("db_id", db.getDb_id());
			map.put("id", db.getId());
			map.put("ref", metaDatabaseRefDao.findById(db.getDb_id()));
			list.add(map);
		}
		return list;
	}
}
