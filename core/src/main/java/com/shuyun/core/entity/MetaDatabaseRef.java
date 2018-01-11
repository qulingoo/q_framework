package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_segmentaton_subject_ref")
public class MetaDatabaseRef {
	@PrimaryKey
	private String seg_id;
	private String subject_id;
	public String getSeg_id() {
		return seg_id;
	}
	public void setSeg_id(String seg_id) {
		this.seg_id = seg_id;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	
 
	 
}
