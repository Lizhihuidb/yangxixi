package com.tjl.yangxixi;

import java.util.ArrayList;
import java.util.List;

import com.tjl.yangxixi.bean.UserInfoBean;

import android.app.Application;


public class MyApplication extends Application{

	private static MyApplication myApplication;

	private String userName;
	
	private List<UserInfoBean> userList;



	@Override
	public void onCreate() {
		super.onCreate();
		myApplication = this;
		
	}

	public static MyApplication getInstance() {
		return myApplication;
	}

	public List<UserInfoBean> getUserList() {
		if(userList == null){
			userList = new ArrayList<UserInfoBean>();
		}
		return userList;
	}

	public void setUserList(List<UserInfoBean> userList) {
		if(userList == null){
			userList = new ArrayList<UserInfoBean>();
		}
		this.userList = userList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
