package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.adapter.ProcessedAdapte;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.ProcessedBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;

public class SignInApplyActivity extends BaseActivity{
	private ListView lv_have_passed;
	private HttpManager http;
	private UserInfoBean user;
	private List<ProcessedBean> noProcessedList;
	private ProcessedAdapte processedAdapte;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_have_passed);
		setTitleView(true, true,"外勤签到");
		user=MyApplication.getInstance().getUserList().get(0);
		http = new HttpManager(this);
		http.setHttpCallBackListener(listener);
		noProcessedList=new ArrayList<ProcessedBean>();
		processedAdapte=new ProcessedAdapte(this, noProcessedList, null,3);
		http.doPost(ConstantsUrl.saleGetApplicationList(user.getEmp_id()), 1, false);
		lv_have_passed = (ListView) findViewById(R.id.lv_sign_in);

		lv_have_passed.setAdapter(processedAdapte);

		lv_have_passed.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				TextView tv_processed_id = (TextView) view.findViewById(R.id.tv_processed_id);
				Intent intent = new Intent(SignInApplyActivity.this,FieldPersonnelSignActivity.class);
				intent.putExtra("applyId", tv_processed_id.getText().toString());
				startActivityForResult(intent, Activity.RESULT_FIRST_USER);
			}
		});
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==Activity.RESULT_FIRST_USER){
			http.doPost(ConstantsUrl.saleGetApplicationList(user.getEmp_id()), 1, false);
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
	HttpCallBackListener listener = new HttpCallBackListener(){

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				case 1:
					try {
						noProcessedList.clear();
						noProcessedList.addAll(JSON.parseArray(result.getData(), ProcessedBean.class));
						processedAdapte.notifyDataSetChanged();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						showToast(result.getMsg());
						processedAdapte.notifyDataSetChanged();
					}
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				case 1:

					break;
			}

		}

	};

}
