package com.lyp.yangxixi.missionmanagerfragment;

import com.lyp.adapters.NoOrderAdapter;
import com.lyp.jsonbean.LoginBean;
import com.lyp.jsonbean.NoOrderBean;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.MainActivity;
import com.tjl.yangxixi.api.YangxixiApi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Administrator
 *  未预约
 */
public class NoOrderMissionFragment extends OriginalFragment{


	View v;
	private RecyclerView mRecyclerView;
	private NoOrderAdapter mAdapter;
	private LinearLayoutManager mLinearLayoutManager;
	private List<NoOrderBean.DataBean> mList = new ArrayList<>();
	private LoginBean.DataBean bean;
	private TextView mCounts;
	//定义一个页码为1
	int pages=1;

	public View createView(LayoutInflater inflater, ViewGroup container,
						   Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v= inflater.inflate(R.layout.fragment_allmission, null,true);

		init();
		return v;
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}

	public void init(){
		mCounts = (TextView) v.findViewById(R.id.tv_counts);
		mRecyclerView = (RecyclerView) v.findViewById(R.id.grid_recycler);
		mLinearLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mAdapter = new NoOrderAdapter(mList);
		// 实例化 RecyclerView Adapter
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new NoOrderAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View v, int position) {
				Toast.makeText(getActivity(),"未预约",Toast.LENGTH_SHORT).show();
			}
		});
		bean = ((MainActivity)getActivity()).dataBean;

		try {
			NoOrder(bean.getC_id(),bean.getServer_select(),pages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void NoOrder(String c_id,String server_select,int page) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<NoOrderBean> call = github.getNoOrder(c_id, server_select,page);
		call.enqueue(new Callback<NoOrderBean>() {
			@Override
			public void onResponse(Call<NoOrderBean> call, final Response<NoOrderBean> response) {
				if (response.body().getResult()== 1 ) {
					mCounts.setText(response.body().getCounts());
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mList.addAll(response.body().getData());
							mAdapter.notifyDataSetChanged();
						}
					});
				}else {
					Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onFailure(Call<NoOrderBean> call, Throwable t) {
				Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
			}
		});
	}
}
