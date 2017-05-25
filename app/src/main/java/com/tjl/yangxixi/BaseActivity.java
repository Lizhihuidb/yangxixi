package com.tjl.yangxixi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 基类
 *
 * Activity
 *
 *
 */
public abstract class BaseActivity extends FragmentActivity {
	public Context context;
	private TextView tvTitleText;
	private RelativeLayout rel_back, rel_fresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		super.onCreate(savedInstanceState);
		context = this;
		/*
		 * //开启友盟推送消息服务 PushAgent mPushAgent = PushAgent.getInstance(this);
		 * mPushAgent.enable(); //统计应用启动数据
		 * PushAgent.getInstance(context).onAppStart(); //获取device_token String
		 * device_token = UmengRegistrar.getRegistrationId(this);
		 * LogJY.d("ffff", device_token);
		 */

		// 初始化视图
		initView(savedInstanceState);
		// 初始化数据
		initData();
		initOnclik();
	}

	protected void setTitleView(final boolean isShowBack, final String titleText) {
		tvTitleText = (TextView) findViewById(R.id.tv_title_title);
		tvTitleText.setText(titleText);
		if (isShowBack) {
			rel_back = (RelativeLayout) findViewById(R.id.rel_back);
			rel_back.setVisibility(View.VISIBLE);
			rel_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					finish();
				}
			});
		}

	}

	protected void setTitleView(final boolean isShowBack,
								final boolean isFresh, final String titleText) {
		tvTitleText = (TextView) findViewById(R.id.tv_title_title);
		tvTitleText.setText(titleText);
		if (isShowBack) {
			rel_back = (RelativeLayout) findViewById(R.id.rel_back);
			rel_back.setVisibility(View.VISIBLE);
			rel_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					finish();
				}
			});
		}
		if (isFresh == false) {
			rel_fresh = (RelativeLayout) findViewById(R.id.rel_fresh);
			rel_fresh.setVisibility(View.GONE);
		}

	}

	// 正常跳转
	protected void openActivity(Class clazz) {
		openActivity(clazz, null, -1);
	}

	// 正常跳转
	protected void openActivityFinish(Class clazz) {
		openActivity(clazz, null, -1);
		finish();
	}

	// 传值跳转
	protected void openActivity(Class clazz, Bundle data) {
		openActivity(clazz, data, -1);
	}

	protected void openActivity(Class clazz, Bundle data, int requestCode) {
		Intent intent = new Intent(this, clazz);

		// 如果你的bundle有数据就应该要带过去
		if (data != null) {
			intent.putExtras(data);
		}

		// 以什么方式开启
		if (requestCode == -1) {
			startActivity(intent);
		} else {
			startActivityForResult(intent, requestCode);
		}
	}

	// 得到Editext中输入的值
	public String getEditextvalue(EditText etName) {
		return etName.getText().toString().trim();
	}

	// 判断字符串是否为空
	public static boolean IsNotEmpty(String str) {
		if (!TextUtils.isEmpty(str)) {
			if (!"null".equals(str)) {
				return true;
			}
		}
		return false;

	}

	protected void showToast(final String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(final int textId) {
		showToast(getString(textId));
	}

	private long currentBackPressedTime = 0;
	private static final int BACK_PRESSED_INTERVAL = 2000;

	protected void exit() {
		if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
			currentBackPressedTime = System.currentTimeMillis();
			showToast("再按一次返回键退出程序");
		} else {
			finish();
		}
	}

	protected abstract void initView(Bundle savedInstanceState);

	protected abstract void initData();

	protected abstract void initOnclik();
}
