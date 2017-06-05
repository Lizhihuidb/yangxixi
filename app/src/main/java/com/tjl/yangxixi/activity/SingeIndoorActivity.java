package com.tjl.yangxixi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.lyp.adapters.IndoorAdapter;
import com.lyp.jsonbean.IndoorDetailsBean;
import com.lyp.jsonbean.JLSingeIndoorBean;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.api.YangxixiApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//抢单(室内)详情
public class SingeIndoorActivity extends Activity {

    public JLSingeIndoorBean.DataBean indoorBean;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private IndoorAdapter mAdapter;//室内
    private List<IndoorDetailsBean.DataBean> mList = new ArrayList<>();
    TextView mTitle;
    Button mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_cardetails);
        init();
    }

    private void init(){
        indoorBean = (JLSingeIndoorBean.DataBean) getIntent().getExtras().get("databean");
        mRecyclerView= (RecyclerView) findViewById(R.id.cardetails_recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new IndoorAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        try {
            Indoordetail(indoorBean.getO_id());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mTitle = (TextView) findViewById(R.id.tv_cardetails_title);
        mOrder = (Button) findViewById(R.id.btn_order_singe);
        mTitle.setText("治理(居家服务)");
    }

    //室内抢单
    public void Indoordetail(String o_id) throws IOException{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YangxixiApi.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YangxixiApi api = retrofit.create(YangxixiApi.class);
        Call<IndoorDetailsBean> call = api.getIndoorOrders(o_id);
        call.enqueue(new Callback<IndoorDetailsBean>() {
            @Override
            public void onResponse(Call<IndoorDetailsBean> call, final Response<IndoorDetailsBean> response) {
                if (response.body().getResult() == 1 ) {
                    SingeIndoorActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mOrder.setVisibility(View.VISIBLE);
                            mList.addAll(response.body().getData());
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<IndoorDetailsBean> call, Throwable t) {
                LogUtils.i("tag","失败"+t.getMessage());
            }
        });
    }
}
