package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("package")
public class Package {
	@PrimaryKey
	private String pack_id;
	private String parent_pack_id;
	private String pack_name;
	public String getPack_id() {
		return pack_id;
	}
	public void setPack_id(String pack_id) {
		this.pack_id = pack_id;
	}
	public String getParent_pack_id() {
		return parent_pack_id;
	}
	public void setParent_pack_id(String parent_pack_id) {
		this.parent_pack_id = parent_pack_id;
	}
	public String getPack_name() {
		return pack_name;
	}
	public void setPack_name(String pack_name) {
		this.pack_name = pack_name;
	}
	@Override
	public String toString() {
		return "Package [pack_id=" + pack_id + ", parent_pack_id=" + parent_pack_id + ", pack_name=" + pack_name + "]";
	}
	 
	
}
