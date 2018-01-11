package com.shuyun.core.resource;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuyun.core.dao.TableModelDao;
import com.shuyun.core.entity.TableModel;

@RestController
@RequestMapping("/core")
public class CoreController{
	@Autowired
	TableModelDao tableModelDao;
	@ResponseBody
	@RequestMapping(value="/addTable",method=RequestMethod.POST)
	public String addTable(TableModel model) {
		try {
			tableModelDao.insert(model);
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return "添加成功";
	}
	@ResponseBody
	@RequestMapping(value="/findByPackId",method=RequestMethod.POST)
	public List<TableModel> addField(String packId) {
		try {
			List<TableModel> findByPackId = tableModelDao.findByPackId(packId);
			return findByPackId;
		} catch (Exception e) {
		}
		
		return null;
	}
}
