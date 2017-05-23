package com.tjl.yangxixi.fragment.waiqin;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.FieldPersonnelGwActivity;
import com.tjl.yangxixi.adapter.ProcessedAdapte;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.ProcessedBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HavePassedGwFragment extends OriginalFragment implements OnRefreshListener2<ListView>{
	private PullToRefreshListView lv_have_passed;
	private HttpManager http;
	private int indexPage=1;
	private UserInfoBean user;
	private List<ProcessedBean> noProcessedList;
	private ProcessedAdapte processedAdapte;
	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_have_passed, null,true);
		init(v);
		return v;
	}


	private void init(View v){
		user=MyApplication.getInstance().getUserList().get(0);
		http = new HttpManager(getActivity());
		http.setHttpCallBackListener(listener);
		noProcessedList=new ArrayList<ProcessedBean>();
		processedAdapte=new ProcessedAdapte(getActivity(), noProcessedList, null,3);
		http.doPost(ConstantsUrl.getApplicationByUser(user.getEmp_id(), "2", indexPage+""), 1, false);
		lv_have_passed = (PullToRefreshListView) v.findViewById(R.id.lv_have_passed);

		lv_have_passed.setMode(Mode.PULL_FROM_END);
		lv_have_passed.setOnRefreshListener(this);// 记得绑定一下 监听类
		lv_have_passed.setAdapter(processedAdapte);

		lv_have_passed.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				TextView tv_processed_id = (TextView) view.findViewById(R.id.tv_processed_id);
				Intent intent = new Intent(getActivity(),FieldPersonnelGwActivity.class);
				intent.putExtra("applyId", tv_processed_id.getText().toString());
				startActivity(intent);
			}
		});

	}
	HttpCallBackListener listener = new HttpCallBackListener(){

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			lv_have_passed.onRefreshComplete();
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
			lv_have_passed.onRefreshComplete();
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
		http.doPost(ConstantsUrl.getApplicationByUser(user.getEmp_id(), "2", indexPage+""), 2, false);
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}
}
