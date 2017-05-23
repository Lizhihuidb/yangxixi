package com.tjl.yangxixi.bean;

public class DistributionScreeningBean {
	private int id;
	private int icon;
	private String describe;
	private String count;
	public DistributionScreeningBean() {
		// TODO Auto-generated constructor stub
	}
	public DistributionScreeningBean(int id, int icon, String describe,String count) {
		super();
		this.id = id;
		this.icon = icon;
		this.describe = describe;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}


}