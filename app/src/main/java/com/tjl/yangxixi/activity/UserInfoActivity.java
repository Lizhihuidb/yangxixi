package com.tjl.yangxixi.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;

public class UserInfoActivity extends BaseActivity implements OnClickListener{
	private LinearLayout lay_userinfo,lay_user_update,lay_messger;
	private TextView tv_user_name,tv_user_type,tv_user_close;
	private UserInfoBean user;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_userinfo);
		lay_userinfo= (LinearLayout) findViewById(R.id.lay_userinfo);
		lay_user_update=(LinearLayout) findViewById(R.id.lay_user_update);
		lay_messger=(LinearLayout) findViewById(R.id.lay_messger);

		tv_user_name=(TextView) findViewById(R.id.tv_user_name);
		tv_user_type=(TextView) findViewById(R.id.tv_user_type);
		tv_user_close=(TextView) findViewById(R.id.tv_user_close);
		setTitleView(true, false, "账号信息");
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		user=MyApplication.getInstance().getUserList().get(0);
		tv_user_name.setText(user.getEmp_name());
		if(user.getPosition().equals("JL")){
			tv_user_type.setText("销售经理");
		}else{
			tv_user_type.setText("销售顾问");
		}
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		lay_userinfo.setOnClickListener(this);
		lay_user_update.setOnClickListener(this);
		lay_messger.setOnClickListener(this);
		tv_user_close.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.lay_userinfo:
				openNewActivity(UserInfoSettingActivity.class, 1);
				break;
			case R.id.lay_user_update:
				openNewActivity(UserInfoSettingActivity.class, 2);
				break;
			case R.id.lay_messger:

				break;
			case R.id.tv_user_close:
				Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				MainActivity.instance.finish();
				UserInfoActivity.this.finish();
				break;
		}
	}
	protected void openNewActivity(Class clazz,int toStatus){
		Intent intent = new Intent(this, clazz);
		intent.putExtra("toStatus", toStatus);
		startActivity(intent);
	}

}
