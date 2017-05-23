package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.ApplyDetailed;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.LogJY;

public class FieldPersonnelGwActivity extends BaseActivity implements OnClickListener{
	private RelativeLayout rel_passed_status,rel_query_signin;
	private TextView tv_wq_status,tv_wq_name,tv_reason_time,tv_user_reason,tv_cq_time,tv_cq_address,tv_have_passedxq,tv_refusexq,tv_signin,tv_query_signin;
	private String applyId;
	private HttpManager http;
	private List<ApplyDetailed> applyList;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_fiekd_personnel);
		setTitleView(true, false,"外勤管理");
		applyId=getIntent().getStringExtra("applyId");
		tv_wq_status=(TextView) findViewById(R.id.tv_wq_status);
		tv_wq_name=(TextView) findViewById(R.id.tv_wq_name);
		tv_reason_time=(TextView) findViewById(R.id.tv_reason_time);
		tv_user_reason=(TextView) findViewById(R.id.tv_user_reason);
		tv_cq_time=(TextView) findViewById(R.id.tv_cq_time);
		tv_cq_address=(TextView) findViewById(R.id.tv_cq_address);
		tv_have_passedxq=(TextView) findViewById(R.id.tv_have_passedxq);
		tv_refusexq=(TextView) findViewById(R.id.tv_refusexq);
		tv_signin=(TextView) findViewById(R.id.tv_signin);
		rel_query_signin=(RelativeLayout) findViewById(R.id.rel_query_signin);
		tv_query_signin=(TextView) findViewById(R.id.tv_query_signin);

		rel_passed_status=(RelativeLayout) findViewById(R.id.rel_passed_status);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		applyList=new ArrayList<ApplyDetailed>();
		http = new HttpManager(this);
		http.setHttpCallBackListener(listener);
		http.showProgressDialog();
		http.doGet(ConstantsUrl.getApplicationInfo(applyId), 1, false);

	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		tv_have_passedxq.setOnClickListener(this);
		tv_refusexq.setOnClickListener(this);
		tv_query_signin.setOnClickListener(this);
	}
	private void getInitData(ApplyDetailed applyDetailed){
		if(applyDetailed.getApp_state().equals("1")){
			tv_wq_status.setVisibility(View.GONE);
			rel_passed_status.setVisibility(View.GONE);
		}else if(applyDetailed.getApp_state().equals("3")){
			tv_wq_status.setVisibility(View.VISIBLE);
			rel_passed_status.setVisibility(View.GONE);
			tv_wq_status.setBackgroundResource(R.drawable.icon_refuse);
		}else{
			tv_wq_status.setVisibility(View.VISIBLE);
			rel_passed_status.setVisibility(View.GONE);
			rel_query_signin.setVisibility(View.VISIBLE);
			tv_signin.setVisibility(View.GONE);
			tv_wq_status.setBackgroundResource(R.drawable.icon_pass);
		}
		tv_wq_name.setText(applyDetailed.getEmp_name());
		tv_reason_time.setText(applyDetailed.getCreate_time());
		tv_user_reason.setText(applyDetailed.getApp_cause());
		tv_cq_time.setText(applyDetailed.getApp_time());
		tv_cq_address.setText(applyDetailed.getApp_addr());
	}
	HttpCallBackListener listener = new HttpCallBackListener(){

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				case 1:
					applyList=JSON.parseArray(result.getData(), ApplyDetailed.class);
					getInitData(applyList.get(0));
					break;

				case 2:
				case 3:
					Toast.makeText(FieldPersonnelGwActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
					finish();
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
		}

	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.tv_have_passedxq:
				http.doPost(ConstantsUrl.makeApplication("3",applyId), 2, false);
				break;

			case R.id.tv_refusexq:
				http.doPost(ConstantsUrl.makeApplication("2",applyId), 3, false);
				break;
			case R.id.tv_query_signin:
				if(applyList.get(0).getIsTrack()==0){
					showToast("没有签到信息!");
				}else{
					Intent intent = new Intent(FieldPersonnelGwActivity.this,SignInInfoActivity.class);
					intent.putExtra("id", applyList.get(0).getId());
					startActivity(intent);
				}
				break;
		}
	}

}
