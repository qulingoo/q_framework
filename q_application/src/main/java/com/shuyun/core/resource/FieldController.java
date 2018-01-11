package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.bize.IFieldBize;
import com.shuyun.core.entity.Field;

@Controller
@RequestMapping("/field")
public class FieldController  {
	@Autowired
	private IFieldBize iFieldBize;

	@ResponseBody
	@RequestMapping("/insert")
	public void insert(Field field) {
		iFieldBize.insert(field);
	}

	@ResponseBody
	@RequestMapping("/update")
	public void update(Field field) {
		iFieldBize.update(field);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		iFieldBize.delete(id);
	}

	@ResponseBody
	@RequestMapping("/deleteByTableId")
	public void deleteByTableId(String table_id) {
		iFieldBize.deleteByTableId(table_id);
	}

	@ResponseBody
	@RequestMapping("/findById")
	public Field findById(String id) {
		return iFieldBize.findById(id);
	}
	@ResponseBody
	@RequestMapping("/findByTableId")	
	public List<Field> findByTableId(String table_id) {
		return iFieldBize.findByTableId(table_id);
	}
	
}
