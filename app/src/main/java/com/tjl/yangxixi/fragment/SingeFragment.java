package com.tjl.yangxixi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.lyp.adapters.SingeAdapter;
import com.lyp.adapters.SingeCarAdapter;
import com.lyp.adapters.SingeIndoorAdapter;
import com.lyp.jsonbean.JLCarBean;
import com.lyp.jsonbean.JLSingeIndoorBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
import com.lyp.jsonbean.LoginBean;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.DetailsClueActivity;
import com.tjl.yangxixi.activity.MainActivity;
import com.tjl.yangxixi.activity.SingeDetailsActivity;
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
	private SingeAdapter mSingeAdapter; //检测
	private SingeCarAdapter mSingeCarAdapter;//车内
	private SingeIndoorAdapter mSingIndoorAdapter;//室内
	private List<JlSingeDetectionBean.DataBean> mList = new ArrayList<>();//检测
	private List<JLCarBean.DataBean> mCarList = new ArrayList<>();//车内
	private List<JLSingeIndoorBean.DataBean> mIndoorList = new ArrayList<>();//室内
	private LoginBean.DataBean bean;
    private TextView mTitle;

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
        mTitle = (TextView) v.findViewById(R.id.singe_title);
		mRecyclerView= (RecyclerView) v.findViewById(R.id.sing_grid_recycler);
		mLinearLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		bean = ((MainActivity)getActivity()).dataBean;
//		LogUtils.i("tag",bean.getC_id());
		try {
			//检测
		if (bean.getServer_select().equals("第三方检测服务")){
			mSingeAdapter = new SingeAdapter(mList);
			mRecyclerView.setAdapter(mSingeAdapter);
			SingeDetection(bean.getServer_select(),pages,bean.getC_id());
			mSingeAdapter.setOnItemClickListener(new SingeAdapter.MyItemClickListener() {
				@Override
				public void onItemClick(View v, int position) {
					Intent intent = new Intent(getActivity(),SingeDetailsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("databean",mList.get(0));
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
		}
			//车内
		else if (bean.getServer_select().equals("车内空气净化服务")) {
			mSingeCarAdapter = new SingeCarAdapter(mCarList);
			mRecyclerView.setAdapter(mSingeCarAdapter);
			SingeCar(bean.getC_id(),bean.getServer_select(),pages);
			mSingeCarAdapter.setOnItemClickListener(new SingeCarAdapter.MyItemClickListener() {
				@Override
				public void onItemClick(View v, int position) {
					Intent intentcar = new Intent(getActivity(),SingeDetailsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("databean",mCarList.get(0));
					LogUtils.i("tag",mCarList.get(0).getO_id());
					intentcar.putExtras(bundle);
					startActivity(intentcar);
				}
			});
			}
			//室内
		else if (bean.getServer_select().equals("室内空气净化服务")){
			mSingIndoorAdapter = new SingeIndoorAdapter(mIndoorList);
			mRecyclerView.setAdapter(mSingIndoorAdapter);
			SingeIndoor(bean.getServer_select(),pages,bean.getC_id());
			mSingIndoorAdapter.setOnItemClickListener(new SingeIndoorAdapter.MyItemClickListener() {
				@Override
				public void onItemClick(View v, int position) {
					Intent intentindoor = new Intent(getActivity(),SingeDetailsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("databean",mIndoorList.get(0));
					intentindoor.putExtras(bundle);
					startActivity(intentindoor);
				}
			});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//第三方检测
	public void SingeDetection(String server_select,int page,String c_id) throws IOException{
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
                            if (response.body().getData().get(0).getServer_type().equals("车内空气净化服务")){
                                mTitle.setText("接单");
                            }
                            else {
                                mTitle.setText("抢单");
                            }
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
	//车内
	public void SingeCar(String c_id,String server_select,int page) throws IOException{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<JLCarBean> call = github.getSingCar(c_id,server_select,page);
		call.enqueue(new Callback<JLCarBean>() {
			@Override
			public void onResponse(Call<JLCarBean> call, final Response<JLCarBean> response) {
				if (response.body().getResult()== 1 ) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mCarList.addAll(response.body().getData());
							mSingeCarAdapter.notifyDataSetChanged();
						}
					});
				}else {
					Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onFailure(Call<JLCarBean> call, Throwable t) {
				Log.i("tag",""+t.getMessage());
			}
		});
	}
	//室内
	public void SingeIndoor(String server_select,int page,String c_id) throws IOException{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<JLSingeIndoorBean> call = github.getSingIndoor(server_select,page,c_id);
		call.enqueue(new Callback<JLSingeIndoorBean>() {
			@Override
			public void onResponse(Call<JLSingeIndoorBean> call, final Response<JLSingeIndoorBean> response) {
				if (response.body().getResult()== 1 ) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mIndoorList.addAll(response.body().getData());
							mSingIndoorAdapter.notifyDataSetChanged();
						}
					});
				}else {
					Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			public void onFailure(Call<JLSingeIndoorBean> call, Throwable t) {
				Log.i("tag",""+t.getMessage());
			}
		});
	}
}
