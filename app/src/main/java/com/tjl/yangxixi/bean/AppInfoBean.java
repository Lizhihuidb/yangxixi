package com.tjl.yangxixi.bean;

import java.io.Serializable;

public class AppInfoBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String PackgeName;
	private String VersionCode;
	private String VersionName;
	private String LoadAddr;
	private String Contents;
	private String Remark;
	private String create_time;
	private long PackgeSize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackgeName() {
		return PackgeName;
	}
	public void setPackgeName(String packgeName) {
		PackgeName = packgeName;
	}
	public String getVersionCode() {
		return VersionCode;
	}
	public void setVersionCode(String versionCode) {
		VersionCode = versionCode;
	}
	public String getVersionName() {
		return VersionName;
	}
	public void setVersionName(String versionName) {
		VersionName = versionName;
	}
	public String getLoadAddr() {
		return LoadAddr;
	}
	public void setLoadAddr(String loadAddr) {
		LoadAddr = loadAddr;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contents) {
		Contents = contents;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public long getPackgeSize() {
		return PackgeSize;
	}
	public void setPackgeSize(long packgeSize) {
		PackgeSize = packgeSize;
	}
}
