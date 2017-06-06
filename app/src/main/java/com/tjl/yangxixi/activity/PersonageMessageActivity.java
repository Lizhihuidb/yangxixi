package com.tjl.yangxixi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tjl.yangxixi.R;

/**
 *
 * @author Administrator
 * 销售经理的个人中心
 */
public class PersonageMessageActivity extends Activity implements OnClickListener{

	private ImageView mBack;
	private RelativeLayout mPwdsetting,mStaffmanagement;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personage_message);
		initview();
	}

	private void initview(){
		mBack = (ImageView) findViewById(R.id.iv_message_back);
		mPwdsetting = (RelativeLayout) findViewById(R.id.rl_message_pwodsetting);
		mStaffmanagement = (RelativeLayout) findViewById(R.id.rl_message_staffmanagement);
		mBack.setOnClickListener(this);
		mPwdsetting.setOnClickListener(this);
		mStaffmanagement.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
			case R.id.iv_message_back:
				finish();//返回上个界面
				break;
			case R.id.rl_message_staffmanagement:
				intent = new Intent(this,ResetPwdActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_message_pwodsetting:
				Toast.makeText(this, "修改密码", 0).show();
				intent = new Intent(this,UpdatePwdActivity.class);
				startActivity(intent);
				break;
		}
	}



}
