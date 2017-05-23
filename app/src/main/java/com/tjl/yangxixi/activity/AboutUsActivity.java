package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.exception.DbException;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.AppInfoBean;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.manager.DbManager;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.service.UpdateAppService;

public class AboutUsActivity extends BaseActivity implements OnClickListener{
	private LinearLayout lay_check_up,lay_protocol,lay_feedback;
	private TextView tv_current_version;
	private HttpManager http;
	List<AppInfoBean> appInfoList;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_about_us);
		lay_check_up=(LinearLayout) findViewById(R.id.lay_check_up);
		lay_protocol=(LinearLayout) findViewById(R.id.lay_protocol);
		lay_feedback=(LinearLayout) findViewById(R.id.lay_feedback);
		tv_current_version=(TextView) findViewById(R.id.tv_current_version);
		setTitleView(true, true, "关于我们");

		appInfoList = new ArrayList<AppInfoBean>();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		http = new HttpManager(this);
		http.setHttpCallBackListener(listener);
		tv_current_version.setText("当前版本"+getString(R.string.versionName));
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		lay_check_up.setOnClickListener(this);
		lay_protocol.setOnClickListener(this);
		lay_feedback.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.lay_check_up:
				http.showProgressDialog("正在检查更新");
				doUpdate();
				break;

			case R.id.lay_protocol:
				Intent intent = new Intent(this,ProtocolActivity.class);
				intent.putExtra("toStatus", 1);
				startActivity(intent);
				break;
			case R.id.lay_feedback:
				Intent intent2 = new Intent(this,ProtocolActivity.class);
				intent2.putExtra("toStatus", 2);
				startActivity(intent2);
				break;
		}
	}
	/**
	 * 软件更新
	 */
	private void doUpdate() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			http.doPost(ConstantsUrl.saleGetApplicationList(info.versionCode),1, false);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void showUpdateAppDialog(AppInfoBean bean,final OnClickListener click,final boolean isExit){
		final Dialog dia=new Dialog(this);
		dia.setCanceledOnTouchOutside(false);
		dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
		View v=LayoutInflater.from(this).inflate(R.layout.dialog_version_upgrade, null);
		TextView tvVersionUpgrade=(TextView) v.findViewById(R.id.tvVersionUpgrade);
		TextView tvVersionNumber=(TextView) v.findViewById(R.id.tvVersionNumber);
		TextView tvVersionTime=(TextView) v.findViewById(R.id.tvVersionTime);
		TextView txMsg=(TextView) v.findViewById(R.id.txMsg);
		TextView btnVersionCancel=(TextView) v.findViewById(R.id.btnVersionCancel);
		TextView btnVersionInstall=(TextView) v.findViewById(R.id.btnVersionInstall);

		tvVersionNumber.setText(""+bean.getVersionName());
		tvVersionTime.setText(""+bean.getCreate_time());
		txMsg.setText(""+bean.getContents());

		btnVersionInstall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dia.dismiss();
				dia.cancel();
				if(click!=null){
					click.onClick(v);
				}
			}
		});
		btnVersionCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dia.dismiss();
				dia.cancel();
				if(isExit){
					System.exit(0);
				}
			}
		});
		dia.addContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dia.show();
	}
	HttpCallBackListener listener=new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				case 1:
					if(result.getResult()==0){
						showToast("您已经是最新版本!");
						return;
					}else{
						appInfoList.addAll(JSON.parseArray(result.getData(), AppInfoBean.class));
						final AppInfoBean bean=appInfoList.get(0);
						try {
							DbManager.deleteAll(AboutUsActivity.this, AppInfoBean.class);
							DbManager.save(AboutUsActivity.this, bean);
						} catch (DbException e) {
							e.printStackTrace();
						}
						showUpdateAppDialog(appInfoList.get(0),
								new OnClickListener() {
									@Override
									public void onClick(View v) {
										Intent intent = new Intent(
												AboutUsActivity.this,
												UpdateAppService.class);
										intent.putExtra("data", bean);
										startService(intent);
									}
								}, false);
					}
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			//showToast(msg);
			http.closeProgressDialog();

		}
	};

}
