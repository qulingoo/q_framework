package com.shuyun.core.entity;

import com.shuyun.core.mybatis.DefaultProc;
import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

/**
 * 物理表
 * @author Machenike
 *
 */
@Table("table_model")
public class TableModel {
	@PrimaryKey
	private String id;//uuid
	private String comment;//别名
	private String table_name;//表名
	private int is_exist;//是否存在 0不存在 、1存在
	@DefaultProc("sysdate()")
	private String created_date;//创建时间
	private String pack_id;//包名
	
	public String getPack_id() {
		return pack_id;
	}
	public void setPack_id(String pack_id) {
		this.pack_id = pack_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	 
	public int getIs_exist() {
		return is_exist;
	}
	public void setIs_exist(int is_exist) {
		this.is_exist = is_exist;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "TableModel [id=" + id + ", comment=" + comment + ", table_name=" + table_name + ", exist=" + is_exist
				+ ", created_date=" + created_date + "]";
	}
	
	
}
