package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_subject_normal_ref")
public class MetaSubjectNormalRef {
	@PrimaryKey
	private String normal_id;
	private String field_id;
	private String field_name;
	public String getNormal_id() {
		return normal_id;
	}
	public void setNormal_id(String normal_id) {
		this.normal_id = normal_id;
	}
	public String getField_id() {
		return field_id;
	}
	public void setField_id(String field_id) {
		this.field_id = field_id;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	
 
}
