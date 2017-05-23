package com.tjl.yangxixi.bean;

public class ProcessedBean {
	private int id;
	private String emp_id;
	private String emp_name;
	private String app_time;
	private String app_cause;
	private String create_time;
	private int isTrack;
	private int type;
	public static final int TYPE_CHECKED = 1;
	public static final int TYPE_NOCHECKED = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getApp_time() {
		return app_time;
	}
	public void setApp_time(String app_time) {
		this.app_time = app_time;
	}
	public String getApp_cause() {
		return app_cause;
	}
	public void setApp_cause(String app_cause) {
		this.app_cause = app_cause;
	}
	public int getIsTrack() {
		return isTrack;
	}
	public void setIsTrack(int isTrack) {
		this.isTrack = isTrack;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
