package com.tjl.yangxixi.activity;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 *
 * @author Administrator
 *  销售员的个人中心
 */
public class PersonageMyMessageActivity extends Activity implements OnClickListener{

	private ImageView mBack;
	private RelativeLayout mPwdsetting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personage_my_message);
		initview();
	}

	private void initview(){
		mBack = (ImageView) findViewById(R.id.iv_message_my_back);
		mPwdsetting = (RelativeLayout) findViewById(R.id.rl_my_message_pwodsetting);
		mBack.setOnClickListener(this);
		mPwdsetting.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.iv_message_back:
				finish();//返回上个界面
				break;
			case R.id.rl_message_pwodsetting:
				Toast.makeText(this, "修改密码", 0).show();
				Intent intent = new Intent(this,UpdatePwdActivity.class);
				startActivity(intent);
				break;
		}
	}

}
