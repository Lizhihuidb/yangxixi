package com.tjl.yangxixi.activity;

import com.blankj.utilcode.util.LogUtils;
import com.lyp.adapters.CarDetailsAdapter;
import com.lyp.adapters.DetectionDetailsAdapter;
import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.JLCarBean;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.api.YangxixiApi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

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
 *  接单(车内)详情
 */
public class SingeDetailsActivity extends Activity{

	public JLCarBean.DataBean carBean;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private CarDetailsAdapter mAdapter;//车内
	private List<CarOrdersDetailsBean.DataBean> mList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_cardetails);
		init();
	}

	private void init(){
		carBean = (JLCarBean.DataBean) getIntent().getExtras().get("databean");
		mRecyclerView= (RecyclerView) findViewById(R.id.cardetails_recycler);
		mLinearLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		mAdapter = new CarDetailsAdapter(mList);
		mRecyclerView.setAdapter(mAdapter);

		try {
			Cardetail(carBean.getO_id());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//车内接单
	public void Cardetail(String o_id) throws IOException{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(YangxixiApi.APP_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		YangxixiApi api = retrofit.create(YangxixiApi.class);
		Call<CarOrdersDetailsBean> call = api.getCarOrders(o_id);
		call.enqueue(new Callback<CarOrdersDetailsBean>() {
			@Override
			public void onResponse(Call<CarOrdersDetailsBean> call, final Response<CarOrdersDetailsBean> response) {
				if (response.body().getResult() == 1 ) {
					SingeDetailsActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mList.addAll(response.body().getData());
							mAdapter.notifyDataSetChanged();
						}
					});
				}
			}

			@Override
			public void onFailure(Call<CarOrdersDetailsBean> call, Throwable t) {
				LogUtils.i("tag","失败"+t.getMessage());
			}
		});
	}

}
