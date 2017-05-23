package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.exception.DbException;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.AppInfoBean;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.DbManager;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.SharePreferenceManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.service.UpdateAppService;


/**
 *
 * @author Administrator
 *	登录界面
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
	private TextView img_start;
	private EditText et_userName;
	private EditText et_userPwd;
	private Button btn_login;
	private TextView tv_forgetPwd;

	private String username;
	private String userpwd;
	private SharedPreferences spf;
	private HttpManager http;
	List<AppInfoBean> appInfoList;
	private CheckBox checkBox_remember;

	private void initView() {
		/**
		 * 控件初始化/设置监听
		 */
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_userPwd = (EditText) findViewById(R.id.et_userpwd);
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_forgetPwd = (TextView) findViewById(R.id.tv_forgetPwd);
		tv_forgetPwd.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		/**
		 * 初始化集合/
		 */
		appInfoList = new ArrayList<AppInfoBean>();
		/**
		 * 初始化网络请求工具
		 */
		http = new HttpManager(this);
		/**
		 * 传递回调对象
		 */
		http.setHttpCallBackListener(listener);
		/**
		 * 记住用户名
		 */
		if (SharePreferenceManager.checkExistPreference(LoginActivity.this,
				"uName")) {
			et_userName.setText(SharePreferenceManager.getStringPreference(
					this, "uName"));
		}
	}

	/**
	 * 初始化控件
	 *
	 * 此类由此处开始
	 *
	 * 逻辑来自父类
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login);
		/**
		 * 初始化启动图
		 */
		img_start = (TextView) findViewById(R.id.start_img);
		/**
		 * 记住用户名复选框
		 */
		checkBox_remember = (CheckBox) findViewById(R.id.checkBox_remember);
		initView();
		doUpdate();
//		/**
//		 * 五秒后隐藏启动图/显示登陆页
//		 */
		int status = getIntent().getIntExtra("status", 0);
		if (status == 1) {
			img_start.setVisibility(View.GONE);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					img_start.setVisibility(View.GONE);
				}
			}, 5000);
		}

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.tv_forgetPwd:
				startActivity(new Intent(LoginActivity.this,
						ForgetPwdActivity.class));
				break;

			case R.id.btn_login:
				checkInput();
				break;
		}
	}

	private void remember(String name, String pwd) {
		http.showProgressDialog();
		http.doPost(ConstantsUrl.loging(name, pwd), 1, false);
	}

	/**
	 * 登录前判断控件状态
	 */
	private void checkInput() {
		username = et_userName.getText().toString();
		/**
		 * 用户名为空
		 */
		if (TextUtils.isEmpty(username)) {
			showToast(R.string.login_userName_null);
			return;
		}
		/**
		 * 密码为空
		 */
		userpwd = et_userPwd.getText().toString();
		if (TextUtils.isEmpty(userpwd)) {
			showToast(R.string.login_userPwd_null);
			return;
		}
		/**
		 * 等待框
		 */
		http.showProgressDialog();
		http.doPost(ConstantsUrl.loging(username, userpwd), 1, false);
		// http.doPost(ConstantsUrl.loging("emp01", "123456aA"), 1, false);
		// MyApplication.getInstance().setUserList(getFalseData());
		// MyApplication.getInstance().setUserName("用户名");
		// if (checkBox_remember.isChecked()) {
		// SharePreferenceManager.saveStringPreference(LoginActivity.this,
		// "uName", "古月仙");
		// } else {
		// if (SharePreferenceManager.checkExistPreference(LoginActivity.this,
		// "uName")) {
		// SharePreferenceManager.removeKeyPreference(LoginActivity.this,
		// "uName");
		// }
		// }
		// openActivityFinish(MainActivity.class);
	}

	/**
	 * 给一堆假数据
	 */
	private List<UserInfoBean> getFalseData() {
		List<UserInfoBean> user = new ArrayList<UserInfoBean>();
		for (int i = 0; i < 2; i++) {
			UserInfoBean infoBean = new UserInfoBean();
			infoBean.setEmp_id("1" + i);
			infoBean.setEmp_name("零零" + i);
			infoBean.setPhone("13455657665");
			infoBean.setPosition("JL");
			infoBean.setPwd("123456");
			infoBean.setStore("1");
			user.add(infoBean);
		}
		return user;
	}

	/**
	 * 请求网络回掉
	 */
	HttpCallBackListener listener = new HttpCallBackListener() {
		/**
		 * 请求成功回掉
		 */
		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				/**
				 * 数据获取
				 */
				case 1:
					try {
						List<UserInfoBean> user = JSON.parseArray(result.getData(),
								UserInfoBean.class);
						MyApplication.getInstance().setUserList(user);
						MyApplication.getInstance().setUserName(username);
						if (checkBox_remember.isChecked()) {
							SharePreferenceManager.saveStringPreference(
									LoginActivity.this, "uName", username);
						} else {
							if (SharePreferenceManager.checkExistPreference(
									LoginActivity.this, "uName")) {
								SharePreferenceManager.removeKeyPreference(
										LoginActivity.this, "uName");
							}
						}
						Toast.makeText(LoginActivity.this, "登录成功..",0).show();
						openActivityFinish(MainActivity.class);//跳转到MainActivity
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if (result.getResult() == 0) {
							showToast(R.string.login_errr);
						}
					}
					break;

				case 2:
					if (result.getResult() == 0) {
						return;
					} else {
						appInfoList.addAll(JSON.parseArray(result.getData(),
								AppInfoBean.class));
						final AppInfoBean bean = appInfoList.get(0);
						try {
							DbManager.deleteAll(LoginActivity.this,
									AppInfoBean.class);
							DbManager.save(LoginActivity.this, bean);
						} catch (DbException e) {
							e.printStackTrace();
						}
						showUpdateAppDialog(appInfoList.get(0),
								new OnClickListener() {
									@Override
									public void onClick(View v) {
										Intent intent = new Intent(
												LoginActivity.this,
												UpdateAppService.class);
										intent.putExtra("data", bean);
										startService(intent);
										LoginActivity.this.finish();
									}
								}, false);
					}
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			showToast("是吧");
			http.closeProgressDialog();

		}
	};

	/**
	 * 软件更新
	 */
	private void doUpdate() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			http.doPost(ConstantsUrl.saleGetApplicationList(info.versionCode),
					2, false);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateAppDialog(AppInfoBean bean,
									final OnClickListener click, final boolean isExit) {
		final Dialog dia = new Dialog(LoginActivity.this);
		dia.setCanceledOnTouchOutside(false);
		dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
		View v = LayoutInflater.from(this).inflate(
				R.layout.dialog_version_upgrade, null);
		TextView tvVersionUpgrade = (TextView) v
				.findViewById(R.id.tvVersionUpgrade);
		TextView tvVersionNumber = (TextView) v
				.findViewById(R.id.tvVersionNumber);
		TextView tvVersionTime = (TextView) v.findViewById(R.id.tvVersionTime);
		TextView txMsg = (TextView) v.findViewById(R.id.txMsg);
		TextView btnVersionCancel = (TextView) v
				.findViewById(R.id.btnVersionCancel);
		TextView btnVersionInstall = (TextView) v
				.findViewById(R.id.btnVersionInstall);

		tvVersionNumber.setText("" + bean.getVersionName());
		tvVersionTime.setText("" + bean.getCreate_time());
		txMsg.setText("" + bean.getContents());

		btnVersionInstall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dia.dismiss();
				dia.cancel();
				if (click != null) {
					click.onClick(v);
				}
			}
		});
		btnVersionCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dia.dismiss();
				dia.cancel();
				if (isExit) {
					System.exit(0);
				}
			}
		});
		dia.addContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		dia.show();
	}
	/*
	 * @Override public void onBackPressed() { exit(); }
	 */
}
