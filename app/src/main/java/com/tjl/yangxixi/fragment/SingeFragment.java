package com.tjl.yangxixi.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lyp.adapters.AlltaskAdapter;
import com.lyp.adapters.SingeAdapter;
import com.lyp.jsonbean.AllTaskBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
import com.lyp.jsonbean.LoginBean;
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
 *	销售经理的抢单界面
 */
public class SingeFragment extends OriginalFragment{

	View v;
	int pages=1;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private SingeAdapter mSingeAdapter;
	private List<JlSingeDetectionBean.DataBean> mList = new ArrayList<>();
	private LoginBean.DataBean bean;

	@Override
	protected void lazyLoad() {

	}

	public View createView(LayoutInflater inflater, ViewGroup container,
						   Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v= inflater.inflate(R.layout.fragment_singe, null,true);
		init();
		return v;
	}

	private void init(){
		mRecyclerView= (RecyclerView) v.findViewById(R.id.sing_grid_recycler);
		mLinearLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mSingeAdapter = new SingeAdapter(mList);
		mRecyclerView.setAdapter(mSingeAdapter);
		bean = ((MainActivity)getActivity()).dataBean;
		try {
			Singe(bean.getServer_select(),pages,bean.getC_id());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Singe(String server_select,int page,String c_id) throws IOException{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<JlSingeDetectionBean> call = github.getSingDetection(server_select,page,c_id);
		call.enqueue(new Callback<JlSingeDetectionBean>() {
			@Override
			public void onResponse(Call<JlSingeDetectionBean> call, final Response<JlSingeDetectionBean> response) {
				if (response.body().getResult()== 1 ) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mList.addAll(response.body().getData());
							mSingeAdapter.notifyDataSetChanged();
						}
					});
				}else {
					Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<JlSingeDetectionBean> call, Throwable t) {
				Log.i("tag",""+t.getMessage());
			}
		});
	}

}
