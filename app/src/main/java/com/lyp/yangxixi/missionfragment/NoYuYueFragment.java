package com.lyp.yangxixi.missionfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.adapters.MNoyuyueAdapter;
import com.lyp.adapters.NoOrderAdapter;
import com.lyp.jsonbean.LoginBean;
import com.lyp.jsonbean.MNoyuyueBean;
import com.lyp.jsonbean.NoOrderBean;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.MainActivity;
import com.tjl.yangxixi.api.YangxixiApi;

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
public class NoYuYueFragment extends OriginalFragment{


	View v;
	private RecyclerView mRecyclerView;
	private MNoyuyueAdapter mAdapter;
	private LinearLayoutManager mLinearLayoutManager;
	private List<MNoyuyueBean.DataBean> mList = new ArrayList<>();
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
		mAdapter = new MNoyuyueAdapter(mList);
		// 实例化 RecyclerView Adapter
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new MNoyuyueAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View v, int position) {
				Toast.makeText(getActivity(),"未预约",Toast.LENGTH_SHORT).show();
			}
		});
		bean = ((MainActivity)getActivity()).dataBean;

		try {
			NoOrder(bean.getC_id(),pages);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void NoOrder(String c_id,int page) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<MNoyuyueBean> call = github.getMNotask(c_id,page);
		call.enqueue(new Callback<MNoyuyueBean>() {
			@Override
			public void onResponse(Call<MNoyuyueBean> call, final Response<MNoyuyueBean> response) {
				if (response.body().getResult()== 1 ) {
					mCounts.setText(response.body().getCount());
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
			public void onFailure(Call<MNoyuyueBean> call, Throwable t) {
				Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
			}
		});
	}
}
