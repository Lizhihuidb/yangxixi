package com.tjl.yangxixi.bean;

import java.io.Serializable;

public class FollManClueSaleInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id; 
	private String act_id;
	private String emp_id;
	private String emp_name;
	private String cus_name;
	private String cus_sex;
	private int cus_age;
	private String cus_phone;
	private String assemblage;
	private String clue_state;
	private String remark;
	private int cout;
	
	
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAct_id() {
		return act_id;
	}
	public void setAct_id(String act_id) {
		this.act_id = act_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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
	public int getCus_age() {
		return cus_age;
	}
	public void setCus_age(int cus_age) {
		this.cus_age = cus_age;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	
	public String getAssemblage() {
		return assemblage;
	}
	public void setAssemblage(String assemblage) {
		this.assemblage = assemblage;
	}
	public String getClue_state() {
		return clue_state;
	}
	public void setClue_state(String clue_state) {
		this.clue_state = clue_state;
	}
	public String getCemark() {
		return remark;
	}
	public void setCemark(String remark) {
		this.remark = remark;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	
	
	
}
