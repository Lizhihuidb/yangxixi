package com.tjl.yangxixi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.lyp.adapters.AssignCarAdapter;
import com.lyp.adapters.CarDetailsAdapter;
import com.lyp.jsonbean.AssignCarBean;
import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.JLCarBean;
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

//分配任务详情
public class AssignmentdetailsCarActivity extends Activity {

    public JLCarBean.DataBean carBean;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private AssignCarAdapter mAdapter;//车内
    private List<AssignCarBean.DataBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmentdetails);
        init();
    }

    private void init(){
        carBean = (JLCarBean.DataBean) getIntent().getExtras().get("databean");
        mRecyclerView= (RecyclerView) findViewById(R.id.assign_recycler);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new AssignCarAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        try {
            AssigCar(carBean.getO_id());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //车内接单
    public void AssigCar(String o_id) throws IOException{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YangxixiApi.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YangxixiApi api = retrofit.create(YangxixiApi.class);
        Call<AssignCarBean> call = api.getAssignCar(o_id);
        call.enqueue(new Callback<AssignCarBean>() {
            @Override
            public void onResponse(Call<AssignCarBean> call, final Response<AssignCarBean> response) {
                if (response.body().getResult() == 1 ) {
                    AssignmentdetailsCarActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mList.addAll(response.body().getData());
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AssignCarBean> call, Throwable t) {
                LogUtils.i("tag","失败"+t.getMessage());
            }
        });
    }

}
