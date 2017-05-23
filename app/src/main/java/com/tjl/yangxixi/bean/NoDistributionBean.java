package com.tjl.yangxixi.bean;

public class NoDistributionBean {
	public static final int TYPE_CHECKED = 1;
	public static final int TYPE_NOCHECKED = 0;
	private int type;
	private int id;
	private String emp_id,emp_name;
	private String cus_name,cus_sex,cus_age,cus_phone,cus_email,cunt;
	private boolean cheboxStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_sex() {
		return cus_sex;
	}
	public void setCus_sex(String cus_sex) {
		this.cus_sex = cus_sex;
	}
	public String getCus_age() {
		return cus_age;
	}
	public void setCus_age(String cus_age) {
		this.cus_age = cus_age;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	public String getCunt() {
		return cunt;
	}
	public void setCunt(String cunt) {
		this.cunt = cunt;
	}
	public boolean isCheboxStatus() {
		return cheboxStatus;
	}
	public void setCheboxStatus(boolean cheboxStatus) {
		this.cheboxStatus = cheboxStatus;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	
}
