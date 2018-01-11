package com.shuyun.quartz.entity;

import java.util.Map;

import com.shuyun.quartz.enums.SchedulingMode;
/**
 * 任务信息
 * @author qlk
 *
 */
public class JobDetials {
	private String id;//uuid
	private String job_name;//任务名称
	private String job_group;//任务分组
	private SchedulingMode mode;//调度模式
	private Map<String,Object> schedule_params;//调度参数
	private Map<String,Object> datas;//任务执行参数
	private String last_modify;//最后更新时间
	private String created_date;//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getJob_group() {
		return job_group;
	}
	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}
	public SchedulingMode getMode() {
		return mode;
	}
	public void setMode(SchedulingMode mode) {
		this.mode = mode;
	}
	public Map<String, Object> getSchedule_params() {
		return schedule_params;
	}
	public void setSchedule_params(Map<String, Object> schedule_params) {
		this.schedule_params = schedule_params;
	}
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	public String getLast_modify() {
		return last_modify;
	}
	public void setLast_modify(String last_modify) {
		this.last_modify = last_modify;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "JobDetials [id=" + id + ", job_name=" + job_name + ", job_group=" + job_group + ", mode=" + mode
				+ ", schedule_params=" + schedule_params + ", datas=" + datas + ", last_modify=" + last_modify
				+ ", created_date=" + created_date + "]";
	}
	
}
