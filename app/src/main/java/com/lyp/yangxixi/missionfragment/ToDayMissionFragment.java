package com.lyp.yangxixi.missionfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.adapters.MTodaytaskAdapter;
import com.lyp.jsonbean.LoginBean;
import com.lyp.jsonbean.MAllTaskBean;
import com.lyp.jsonbean.MTodayBean;
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
 * Created by Administrator on 2017/6/5.
 */
//今日任务
public class ToDayMissionFragment extends OriginalFragment{

    View v;
    private RecyclerView mRecyclerView;
    private MTodaytaskAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<MTodayBean.DataBean> mList = new ArrayList<>();
    private LoginBean.DataBean bean;
    private TextView mCounts;
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
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MTodaytaskAdapter(mList);
        // 实例化 RecyclerView Adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MTodaytaskAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//				Intent intentcar = new Intent(getActivity(),AssignmentdetailsCarActivity.class);
////				Bundle bundle = new Bundle();
////				bundle.putSerializable("databean",mList.get(0));
////				intentcar.putExtras(bundle);
//				startActivity(intentcar);
                Toast.makeText(getActivity(),"今日任务",Toast.LENGTH_SHORT).show();
            }
        });
        bean = ((MainActivity)getActivity()).dataBean;
        try {
            TodayTask(bean.getC_id(),pages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void lazyLoad() {
        // TODO Auto-generated method stub
    }

    public void TodayTask(String c_id,int page) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YangxixiApi.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YangxixiApi github = retrofit.create(YangxixiApi.class);
        Call<MTodayBean> call = github.getMToday(c_id,page);
        call.enqueue(new Callback<MTodayBean>() {
            @Override
            public void onResponse(Call<MTodayBean> call, final Response<MTodayBean> response) {
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
            public void onFailure(Call<MTodayBean> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
