package com.tjl.yangxixi.bean;
/**
 * @author tao
 */
public class HttpMessage {
	private int result;
	private int totalPageCount;
	private String msg;
	private String data;
	private int StayTask;
	private int TotalTask;

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStayTask() {
		return StayTask;
	}
	public void setStayTask(int stayTask) {
		StayTask = stayTask;
	}
	public int getTotalTask() {
		return TotalTask;
	}
	public void setTotalTask(int totalTask) {
		TotalTask = totalTask;
	}
}