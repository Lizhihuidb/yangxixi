package com.tjl.yangxixi.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.adapter.TaskDistinctAdapter;
import com.tjl.yangxixi.bean.DistributionBean;
import com.tjl.yangxixi.bean.DistributionScreeningBean;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.MissionGwBean;
import com.tjl.yangxixi.bean.StoreEmployeeInfoBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.ToolsUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 任务分配
 * @author zzw
 *
 */
public class TaskDistActivity extends BaseActivity implements OnClickListener{
	private HttpManager http;
	private ListView lv_store;
	private TextView tv_title_operation;
	private UserInfoBean user;
	private List<StoreEmployeeInfoBean> seList;
	private TaskDistinctAdapter taskDistinctAdapter;
	private String clueId;
	private RelativeLayout rel_taskdist_back;
	private String distributionId="";
	private int toStatus;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_manager_taskdist);
		lv_store = (ListView) findViewById(R.id.lv_store);
		tv_title_operation = (TextView) findViewById(R.id.tv_title_operation);
		rel_taskdist_back=(RelativeLayout) findViewById(R.id.rel_taskdist_back);
		user=MyApplication.getInstance().getUserList().get(0);
		clueId=getIntent().getStringExtra("data");
		toStatus=getIntent().getIntExtra("toStatus", 0);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		http=new HttpManager(this);
		http.setHttpCallBackListener(listener);
		http.doPost(ConstantsUrl.getStoreEmployeeList(user.getEmp_id()), 1, false);
		seList=new ArrayList<StoreEmployeeInfoBean>();
		taskDistinctAdapter=new TaskDistinctAdapter(seList, this,handler);
		lv_store.setAdapter(taskDistinctAdapter);
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		tv_title_operation.setOnClickListener(this);
		rel_taskdist_back.setOnClickListener(this);
	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1:
					distributionId=msg.obj.toString();
					break;

				default:
					break;
			}
		};
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.tv_title_operation:
				if(distributionId.equals("")){
					showToast("请选择需要分配的顾问！");
				}else{
					http.doPost(ConstantsUrl.getAssigStore(distributionId,clueId.substring(0,clueId.length()-1)), 2, false);
				}
				break;

			case R.id.rel_taskdist_back:
				finish();
				break;
		}
	}
	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			switch (tag) {
				case 1:
					seList.addAll(JSON.parseArray(result.getData(), StoreEmployeeInfoBean.class));
					taskDistinctAdapter.notifyDataSetChanged();
					break;
				case 2:
					if(result.getResult()==1){
						showToast("分配任务成功");
					}else{
						showToast("分配任务失败");
					}
					if(toStatus==1){
						Intent intent=new Intent(TaskDistActivity.this, MainActivity.class);
						intent.putExtra("status", false);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
					if(toStatus==2){
						Intent intent=new Intent(TaskDistActivity.this, LibraryMissionActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
					TaskDistActivity.this.finish();
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			switch (tag) {
				case 1:

					break;

			}

		}
	};
}
