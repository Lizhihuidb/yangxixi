package com.tjl.yangxixi.fragment.waiqin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.DetailsClueActivity;
import com.tjl.yangxixi.activity.FieldPersonnelActivity;
import com.tjl.yangxixi.activity.FieldPersonnelGwActivity;
import com.tjl.yangxixi.adapter.ProcessedAdapte;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.NoDistributionBean;
import com.tjl.yangxixi.bean.ProcessedBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.fragment.FieldPersonnelFragment;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.LogJY;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class NoProcessedGwFragment extends OriginalFragment implements OnClickListener,OnRefreshListener2<ListView>{
	private PullToRefreshListView lv_noprocessed;
	private LinearLayout lay_noprocessed_checkall;
	private HttpManager http;
	private int indexPage=1;
	private UserInfoBean user;
	private String dateTime;
	private List<ProcessedBean> noProcessedList;
	private ProcessedAdapte processedAdapte;
	private View view_line;
	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_noprocessed, null,true);
		init(v);
		return v;
	}
	private void init(View v){
		user=MyApplication.getInstance().getUserList().get(0);
		http = new HttpManager(getActivity());
		http.setHttpCallBackListener(listener);
		noProcessedList=new ArrayList<ProcessedBean>();
		processedAdapte=new ProcessedAdapte(getActivity(), noProcessedList, handler,1);
		http.showProgressDialog();
		http.doPost(ConstantsUrl.getApplicationByUser(user.getEmp_id(), "1", indexPage+""), 1, false);
		lv_noprocessed = (PullToRefreshListView) v.findViewById(R.id.lv_noprocessed);
		lay_noprocessed_checkall=(LinearLayout) v.findViewById(R.id.lay_noprocessed_checkall);
		view_line=v.findViewById(R.id.view_line);

		lay_noprocessed_checkall.setVisibility(View.GONE);
		view_line.setVisibility(View.GONE);

		lv_noprocessed.setMode(Mode.PULL_FROM_END);
		lv_noprocessed.setOnRefreshListener(this);// 记得绑定一下 监听类
		lv_noprocessed.setAdapter(processedAdapte);
		lv_noprocessed.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				TextView tv_processed_id = (TextView) view.findViewById(R.id.tv_processed_id);
				Intent intent = new Intent(getActivity(),FieldPersonnelGwActivity.class);
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

		}

	}
	public Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:

					break;

				case 2:

					break;
			}

		};
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		}
	}
	HttpCallBackListener listener = new HttpCallBackListener(){

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			lv_noprocessed.onRefreshComplete();
			switch (tag) {
				case 1:
					try {
						noProcessedList.clear();
						noProcessedList.addAll(JSON.parseArray(result.getData(), ProcessedBean.class));
						processedAdapte.notifyDataSetChanged();
						indexPage=1;
						indexPage++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						processedAdapte.notifyDataSetChanged();
					}
					break;
				case 2:
					noProcessedList.addAll(JSON.parseArray(result.getData(), ProcessedBean.class));
					processedAdapte.notifyDataSetChanged();
					indexPage++;
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			lv_noprocessed.onRefreshComplete();
			switch (tag) {
				case 1:

					break;

				case 2:
					break;
			}

		}

	};
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		http.showProgressDialog();
		http.doPost(ConstantsUrl.getApplicationByUser(user.getEmp_id(), "1", indexPage+""), 2, false);
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}
}
