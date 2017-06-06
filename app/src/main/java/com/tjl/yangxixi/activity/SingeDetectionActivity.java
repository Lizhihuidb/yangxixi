package com.tjl.yangxixi.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.lyp.adapters.CarDetailsAdapter;
import com.lyp.adapters.DetectionDetailsAdapter;
import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.DetectionDetailsBean;
import com.lyp.jsonbean.JLCarBean;
import com.lyp.jsonbean.JLSingeIndoorBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
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

/**
 * 抢单(检测)详情
 */
public class SingeDetectionActivity extends Activity {

    public JlSingeDetectionBean.DataBean detectionBean;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private DetectionDetailsAdapter mAdapter;//检测
    private List<DetectionDetailsBean.DataBean> mList = new ArrayList<>();
    TextView mTitle;
    Button mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_cardetails);
        init();
    }

    private void init(){
        detectionBean = (JlSingeDetectionBean.DataBean) getIntent().getExtras().get("databean");
        mRecyclerView= (RecyclerView) findViewById(R.id.cardetails_recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DetectionDetailsAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        try {
            Detectiondetail(detectionBean.getO_id());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mTitle = (TextView) findViewById(R.id.tv_cardetails_title);
        mOrder = (Button) findViewById(R.id.btn_order_singe);
    }

    //检测抢单
    public void Detectiondetail(String o_id) throws IOException{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YangxixiApi.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YangxixiApi api = retrofit.create(YangxixiApi.class);
        Call<DetectionDetailsBean> call = api.getDetectionOrders(o_id);
        call.enqueue(new Callback<DetectionDetailsBean>() {
            @Override
            public void onResponse(Call<DetectionDetailsBean> call, final Response<DetectionDetailsBean> response) {
                if (response.body().getResult() == 1 ) {
                    SingeDetectionActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTitle.setText(detectionBean.getDetection_type());
                            if (detectionBean.getDetection_type().equals("复检")){
                                mOrder.setVisibility(View.GONE);
                            }else {
                                mOrder.setVisibility(View.VISIBLE);
                            }
                            mList.addAll(response.body().getData());
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DetectionDetailsBean> call, Throwable t) {
                LogUtils.i("tag","失败"+t.getMessage());
            }
        });
    }
}
