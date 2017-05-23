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
 *   任务
 */

public class TaskActivity extends Activity {

	ImageView mTackback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		mTackback = (ImageView) findViewById(R.id.iv_tack_back);
		mTackback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}


}
