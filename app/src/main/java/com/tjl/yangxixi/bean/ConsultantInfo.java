package com.tjl.yangxixi.bean;

import java.io.Serializable;

/**
 * @author zzw
 *
 */
public class ConsultantInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id; 
	private String cus_name;
	private String cus_sex;
	private int cus_age;
	private String cus_phone;
	private String assemblage;
	private int cunt;
	private String createTime;
	private String bookTime;
	
	
	
	
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public int getCunt() {
		return cunt;
	}
	public void setCunt(int cunt) {
		this.cunt = cunt;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	
}
