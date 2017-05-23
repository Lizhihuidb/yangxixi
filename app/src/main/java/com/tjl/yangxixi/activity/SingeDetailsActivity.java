package com.tjl.yangxixi.activity;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.utils.ImmersedStatusbarUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


/**
 *
 * @author Administrator
 *  抢单详情
 */
public class SingeDetailsActivity extends Activity implements OnClickListener{

	private Button percent_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_singe_details);
		init();
	}

	private void init(){
		percent_button = (Button) findViewById(R.id.percent_button);
		percent_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.percent_button:
				Toast.makeText(SingeDetailsActivity.this, "抢单成功,已添加至任务列表", 0).show();
				finish();
				break;
		}
	}
}
