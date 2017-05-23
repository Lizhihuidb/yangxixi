package com.tjl.yangxixi.bean;

import java.io.Serializable;
/**
 * @author tao
 *
 */
public class StoreEmployeeInfoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String Emp_id;
	private String Pwd;
	private String Emp_name;
	private String Store;//�ŵ�����
	private String Sto_code;//�ŵ����
	private String Company;//��˾����
	private String Manager;//������
	private String Position;//λ
	private String Phone;
	private boolean isCheck;
	
	
	
	
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getEmp_id() {
		return Emp_id;
	}
	public void setEmp_id(String emp_id) {
		Emp_id = emp_id;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getEmp_name() {
		return Emp_name;
	}
	public void setEmp_name(String emp_name) {
		Emp_name = emp_name;
	}
	public String getStore() {
		return Store;
	}
	public void setStore(String store) {
		Store = store;
	}
	public String getSto_code() {
		return Sto_code;
	}
	public void setSto_code(String sto_code) {
		Sto_code = sto_code;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getManager() {
		return Manager;
	}
	public void setManager(String manager) {
		Manager = manager;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	
}
