package com.lyp.yangxixi.missionmanagerfragment;

import com.lyp.jsonbean.LoginBean;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.DetailsClueActivity;
import com.tjl.yangxixi.activity.MainActivity;
import com.tjl.yangxixi.adapter.AlltaskAdapter;
import com.tjl.yangxixi.api.YangxixiApi;
import com.lyp.jsonbean.AllTaskBean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class AllMissionFragment extends OriginalFragment{
	View v;
	private RecyclerView mRecyclerView;
	private AlltaskAdapter mAdapter;
	private GridLayoutManager mGridLayoutManager;
	private List<AllTaskBean.DataBean> mList = new ArrayList<>();
	private LoginBean.DataBean bean;
	TextView mCounts;
	//定义一个页码为1
	int pages=1;

	public View createView(LayoutInflater inflater, ViewGroup container,
						   Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_allmission, null,false);
		init();
		return v;
	}

	public void init(){
		mCounts = (TextView) v.findViewById(R.id.tv_counts);
		mRecyclerView = (RecyclerView) v.findViewById(R.id.grid_recycler);
		mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
		mRecyclerView.setLayoutManager(mGridLayoutManager);
		mAdapter = new AlltaskAdapter(mList);
		// 实例化 RecyclerView Adapter
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new AlltaskAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View v, int position) {
				Intent intent = new Intent(getActivity(),DetailsClueActivity.class);
				startActivity(intent);
			}
		});
		bean = ((MainActivity)getActivity()).dataBean;

		try {
			Task(bean.getC_id(),bean.getServer_select(),pages);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
	}

	public void Task(String c_id,String server_select,int page) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<AllTaskBean> call = github.getAllTask(c_id, server_select,page);
		call.enqueue(new Callback<AllTaskBean>() {
			@Override
			public void onResponse(Call<AllTaskBean> call, final Response<AllTaskBean> response) {
                if (response.body().getResult()== 1 ) {
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
			public void onFailure(Call<AllTaskBean> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
			}
		});
	}

}
