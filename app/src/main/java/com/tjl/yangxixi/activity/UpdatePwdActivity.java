package com.tjl.yangxixi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.tjl.yangxixi.R;

/**
 *
 * @author Administrator
 *  修改密码(销售经理,销售员)的界面
 *
 */
public class UpdatePwdActivity extends Activity implements OnClickListener{

	private ImageView mBack;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_pwd);
		initview();
	}


	public void initview(){
		mBack = (ImageView) findViewById(R.id.iv_updatepwd_back);
		mBack.setOnClickListener(this);

	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.iv_updatepwd_back:
				finish();//返回上个界面
				break;
		}
	}

}
