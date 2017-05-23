package com.tjl.yangxixi.activity;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.R.layout;
import com.tjl.yangxixi.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/***
 *
 * @author Administrator
 *  操作手册(经理/员工)
 */
public class ProceduresActivity extends Activity implements OnClickListener{

	private RelativeLayout mCustomerlogin,mGrabsingle,mReport,mTask,mMy;
	private ImageView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_procedures);
		initview();
	}

	void initview(){
		mBack = (ImageView) findViewById(R.id.iv_procedures_back);
		mCustomerlogin = (RelativeLayout) findViewById(R.id.rl_customerlogin);
		mGrabsingle = (RelativeLayout) findViewById(R.id.rl_procedures_grabsingle);
		mReport = (RelativeLayout) findViewById(R.id.rl_procedures_report);
		mTask = (RelativeLayout) findViewById(R.id.rl_procedures_task);
		mMy = (RelativeLayout) findViewById(R.id.rl_procedures_my);
		mCustomerlogin.setOnClickListener(this);
		mGrabsingle.setOnClickListener(this);
		mReport.setOnClickListener(this);
		mTask.setOnClickListener(this);
		mMy.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
			case R.id.iv_procedures_back:
				finish();
				break;
			case R.id.rl_customerlogin:
				Toast.makeText(this, "客服端登录", 0).show();
				intent = new Intent(this,CustomerloginActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_procedures_grabsingle:
				Toast.makeText(this, "抢单", 0).show();
				intent = new Intent(this,GrabsingleActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_procedures_report:
				Toast.makeText(this, "报告", 0).show();
				intent = new Intent(this,ReportActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_procedures_task:
				Toast.makeText(this, "任务", 0).show();
				intent = new Intent(this,TaskActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_procedures_my:
				Toast.makeText(this, "我的", 0).show();
				intent = new Intent(this,MyActivity.class);
				startActivity(intent);
				break;
		}
	}

}
