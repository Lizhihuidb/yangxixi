package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.adapter.ConsultantMAdapter;
import com.tjl.yangxixi.bean.DistributionBean;
import com.tjl.yangxixi.bean.DistributionScreeningBean;
import com.tjl.yangxixi.bean.FollManClueSaleInfo;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.NoDistributionBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.ToolsUtils;

public class FollowUpActiivity extends BaseActivity implements OnClickListener,OnRefreshListener2<ListView>{
	private String clueState;//区分跟进中和未跟进
	private PullToRefreshListView lv_follow_status;
	private HttpManager http;
	private UserInfoBean user;
	private List<FollManClueSaleInfo> follManList;
	private ConsultantMAdapter consultantMAdapter;
	private int indexPage=1;
	private ImageView iv_search;
	private EditText et_name;
	private int statusId=0;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_manager_m);
		user=MyApplication.getInstance().getUserList().get(0);
		http=new HttpManager(this);
		http.setHttpCallBackListener(listener);
		clueState=getIntent().getStringExtra("data");
		if(clueState.equals("2")){
			setTitleView(true, "跟进中线索");
		}else{
			setTitleView(true, "未跟进线索");
		}
		http.doPost(ConstantsUrl.getFollManClueSale(user.getEmp_id(), clueState, "1"), 1,false);
		lv_follow_status=(PullToRefreshListView) findViewById(R.id.lv_follow_status);
		iv_search=(ImageView) findViewById(R.id.iv_search);
		et_name=(EditText) findViewById(R.id.et_name);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		follManList=new ArrayList<FollManClueSaleInfo>();
		consultantMAdapter=new ConsultantMAdapter(follManList, this);
		lv_follow_status.setAdapter(consultantMAdapter);
		lv_follow_status.setOnClickListener(this);
		lv_follow_status.setMode(Mode.PULL_FROM_END);
		lv_follow_status.setOnRefreshListener(this);// 记得绑定一下 监听类
		lv_follow_status.setOnItemClickListener(itemClickListener2);

	}
	private OnItemClickListener itemClickListener2 = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			// TODO Auto-generated method stub
			TextView tv_id=(TextView) view.findViewById(R.id.tv_id);
			TextView phone=(TextView) view.findViewById(R.id.tv_con_phone);
			Intent intent = new Intent(FollowUpActiivity.this,DetailsClueActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("cludeId", tv_id.getText().toString());
			if(clueState.equals("2")){
				bundle.putInt("userPosition", 3);
			}else{
				bundle.putInt("userPosition", 2);
			}
			intent.putExtra("phone", phone.getText().toString());
			intent.putExtra("data", bundle);
			startActivityForResult(intent, Activity.RESULT_FIRST_USER);
		}
	};
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==Activity.RESULT_FIRST_USER){
			http.doPost(ConstantsUrl.getFollManClueSale(user.getEmp_id(), clueState, "1"), 1,false);
		}

	}
	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		iv_search.setOnClickListener(this);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		if(statusId==0){
			http.doPost(ConstantsUrl.getFollManClueSale(user.getEmp_id(), clueState, indexPage+""), 2,false);
		}else{
			http.doPost(ConstantsUrl.getBlurClueByName(user.getEmp_id(),et_name.getText().toString(), clueState, indexPage+""), 2,false);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.iv_search:
				if(!TextUtils.isEmpty(et_name.getText())){
					http.doPost(ConstantsUrl.getBlurClueByName(user.getEmp_id(),et_name.getText().toString(), clueState, 1+""), 3,false);
				}else{
					http.doPost(ConstantsUrl.getFollManClueSale(user.getEmp_id(), clueState, 1+""), 1,false);
				}
				break;

			default:
				break;
		}
	}
	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			lv_follow_status.onRefreshComplete();
			switch (tag) {
				case 1:
					try {
						follManList.clear();
						follManList.addAll(JSON.parseArray(result.getData(), FollManClueSaleInfo.class));
						consultantMAdapter.notifyDataSetChanged();
						indexPage=1;
						indexPage++;
						statusId=0;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						consultantMAdapter.notifyDataSetChanged();
					}
					break;
				case 2:
					follManList.addAll(JSON.parseArray(result.getData(), FollManClueSaleInfo.class));
					consultantMAdapter.notifyDataSetChanged();
					indexPage++;
					break;
				case 3:
					try {
						follManList.clear();
						follManList.addAll(JSON.parseArray(result.getData(), FollManClueSaleInfo.class));
						consultantMAdapter.notifyDataSetChanged();
						indexPage=1;
						indexPage++;
						statusId=1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						consultantMAdapter.notifyDataSetChanged();
					}
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			lv_follow_status.onRefreshComplete();
			switch (tag) {
				case 1:

					break;

				case 2:
					break;
			}

		}
	};

}
