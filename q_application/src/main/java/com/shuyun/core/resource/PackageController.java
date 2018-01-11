package com.shuyun.core.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyun.core.bize.IPackageBize;
import com.shuyun.core.entity.Package;

@Controller
@RequestMapping("/package")
public class PackageController {
	@Autowired
	IPackageBize iPackageBize;
	@ResponseBody
	@RequestMapping("/insert")
	public void instrt(Package pack) {
		iPackageBize.insert(pack);
	}
	@ResponseBody
	@RequestMapping("/update")
	public void update(Package pack) {
		iPackageBize.update(pack);
	}
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(String id) {
		iPackageBize.delete(id);
	}
	@ResponseBody
	@RequestMapping("/findById")
	public Package findById(String id) {
		return iPackageBize.findById(id);
	}
	@ResponseBody
	@RequestMapping("findByParentId")
	public List<Package> findByParentId(String parent_pakc_id) {
		return iPackageBize.findByParentId(parent_pakc_id);
	}
	
}
