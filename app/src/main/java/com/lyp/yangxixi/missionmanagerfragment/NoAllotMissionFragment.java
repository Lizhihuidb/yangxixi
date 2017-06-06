package com.lyp.yangxixi.missionmanagerfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.adapters.NofenpeiAdapter;
import com.lyp.jsonbean.LoginBean;
import com.lyp.jsonbean.NofenpeiBean;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.IndependentActivity;
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

//未分配
public class NoAllotMissionFragment extends OriginalFragment implements View.OnClickListener{

	View v;
	private RecyclerView mRecyclerView;
	private NofenpeiAdapter mAdapter;
	private GridLayoutManager mGridLayoutManager;
	private List<NofenpeiBean.DataBean> mList = new ArrayList<>();
	private LoginBean.DataBean bean;
	private TextView mCounts,mAllTask;
	//定义一个页码为1
	int pages=1;
	private LinearLayout mQx;
	private CheckBox mCheckbox;
	private Button mBtnZ,mBtnP;

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
		mQx = (LinearLayout) v.findViewById(R.id.ll_quanxuan);
		mCheckbox = (CheckBox) v.findViewById(R.id.cb_missionall);
		mAllTask = (TextView) v.findViewById(R.id.tv_missionall);
		mBtnZ = (Button) v.findViewById(R.id.btn_zf);
		mBtnP = (Button) v.findViewById(R.id.btn_zf);
		mQx.setVisibility(View.VISIBLE);
		mCounts = (TextView) v.findViewById(R.id.tv_counts);
		mRecyclerView = (RecyclerView) v.findViewById(R.id.grid_recycler);
		mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
		mRecyclerView.setLayoutManager(mGridLayoutManager);
		mAdapter = new NofenpeiAdapter(mList);
		// 实例化 RecyclerView Adapter
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(new NofenpeiAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View v, int position) {
				Toast.makeText(getActivity(),"当前点击的是:"+position,Toast.LENGTH_SHORT).show();
			}
		});
		bean = ((MainActivity)getActivity()).dataBean;
		try {
			Nofenpei(bean.getC_id(),bean.getServer_select(),pages);
		} catch (IOException e) {
			e.printStackTrace();
		}


		mAllTask.setOnClickListener(this);
		mBtnZ.setOnClickListener(this);
		mBtnP.setOnClickListener(this);
	}

	public void Nofenpei(String c_id,String server_select,int page) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<NofenpeiBean> call = github.getNoTask(c_id, server_select,page);
		call.enqueue(new Callback<NofenpeiBean>() {
			@Override
			public void onResponse(Call<NofenpeiBean> call, final Response<NofenpeiBean> response) {
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
			public void onFailure(Call<NofenpeiBean> call, Throwable t) {
				Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tv_missionall:

				break;
			case R.id.btn_zf:
					Intent intentzf = new Intent(getActivity(),IndependentActivity.class);
					startActivity(intentzf);
				break;
			case R.id.btn_pf:

				break;
		}
	}
}
