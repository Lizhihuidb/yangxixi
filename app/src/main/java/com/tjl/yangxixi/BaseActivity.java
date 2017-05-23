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
 * ����
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
		 * //��������������Ϣ���� PushAgent mPushAgent = PushAgent.getInstance(this);
		 * mPushAgent.enable(); //ͳ��Ӧ����������
		 * PushAgent.getInstance(context).onAppStart(); //��ȡdevice_token String
		 * device_token = UmengRegistrar.getRegistrationId(this);
		 * LogJY.d("ffff", device_token);
		 */

		// ��ʼ����ͼ
		initView(savedInstanceState);
		// ��ʼ������
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

	// ������ת
	protected void openActivity(Class clazz) {
		openActivity(clazz, null, -1);
	}

	// ������ת
	protected void openActivityFinish(Class clazz) {
		openActivity(clazz, null, -1);
		finish();
	}

	// ��ֵ��ת
	protected void openActivity(Class clazz, Bundle data) {
		openActivity(clazz, data, -1);
	}

	protected void openActivity(Class clazz, Bundle data, int requestCode) {
		Intent intent = new Intent(this, clazz);

		// ������bundle�����ݾ�Ӧ��Ҫ����ȥ
		if (data != null) {
			intent.putExtras(data);
		}

		// ��ʲô��ʽ����
		if (requestCode == -1) {
			startActivity(intent);
		} else {
			startActivityForResult(intent, requestCode);
		}
	}

	// �õ�Editext�������ֵ
	public String getEditextvalue(EditText etName) {
		return etName.getText().toString().trim();
	}

	// �ж��ַ����Ƿ�Ϊ��
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
			showToast("�ٰ�һ�η��ؼ��˳�����");
		} else {
			finish();
		}
	}

	protected abstract void initView(Bundle savedInstanceState);

	protected abstract void initData();

	protected abstract void initOnclik();
}
