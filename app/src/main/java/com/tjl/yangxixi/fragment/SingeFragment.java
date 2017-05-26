package com.tjl.yangxixi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.lyp.jsonbean.AllTaskBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
import com.lyp.percent.PercentRelativeLayout;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.SingeDetailsActivity;
import com.tjl.yangxixi.api.YangxixiApi;
import com.tjl.yangxixi.bean.UserInfoBean;

import java.io.IOException;
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
public class SingeFragment extends OriginalFragment implements OnClickListener{

	View v;
	private UserInfoBean user;
	private PercentRelativeLayout percent_a;
	public SingeFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void lazyLoad() {

	}

	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_singe_item, null);
		init();
		try {
			Singe();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return v;
	}

	private void init(){
		percent_a = (PercentRelativeLayout) v.findViewById(R.id.percent_singedetection);
		percent_a.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
			case R.id.percent_detauls:
				intent = new Intent(getActivity(),SingeDetailsActivity.class);
				startActivity(intent);
				break;
		}
	}

	public void Singe() throws IOException{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		YangxixiApi github = retrofit.create(YangxixiApi.class);
		Call<JlSingeDetectionBean> call = github.getSingDetection("室内空气净化服务",1,1);
		call.enqueue(new Callback<JlSingeDetectionBean>() {
			@Override
			public void onResponse(Call<JlSingeDetectionBean> call, Response<JlSingeDetectionBean> response) {
				Log.i("tag",""+response.body().getResult());
			}

			@Override
			public void onFailure(Call<JlSingeDetectionBean> call, Throwable t) {
				Log.i("tag",""+t.getMessage());
			}
		});
	}

}
