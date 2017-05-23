package com.tjl.yangxixi.activity;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.R.layout;
import com.tjl.yangxixi.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


/**
 *
 * @author Administrator
 *  操作手册--客服端登录
 */
public class CustomerloginActivity extends Activity {

	private ImageView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customerlogin);
		mBack = (ImageView) findViewById(R.id.iv_customerlogin_back);
		mBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}



}
