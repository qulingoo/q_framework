package com.shuyun.core.entity;

import java.util.HashMap;
import java.util.Map;

import com.shuyun.core.mybatis.DefaultProc;
import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;
@Table("table_field")
public class Field {
	@PrimaryKey
	private String id;
	private String table_id;
	private String comment;
	private String field_name;
	private int types;
	private String accuracy;
	@DefaultProc("sysdate()")
	private String created_date;
	private int dindex;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		try {
			this.accuracy= Double.valueOf(accuracy).intValue()+"";
		} catch (Exception e) {
			this.accuracy = accuracy;

		}
	}
	 
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public int getDindex() {
		return dindex;
	}
	public void setDindex(int dindex) {
		this.dindex = dindex;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getType( ) {
		Map<Integer, String> typeMap = new HashMap<>();
		typeMap.put(4, "INTEGER");
		typeMap.put(4, "INT");
		typeMap.put(3, "DECIMAL");
		typeMap.put(1, "CHAR");
		typeMap.put(12, "VARCHAR");
		typeMap.put(93, "TIMESTAMP");
		typeMap.put(94, "DATETIME");
		typeMap.put(2005, "CLOB");
		return typeMap.get(Integer.valueOf(types));
	}
	
	
}
