package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lyp.jsonbean.JlSingeDetectionBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/31 14: 26
 */
//抢单
public class SingeAdapter extends RecyclerView.Adapter<SingeAdapter.MyViewHolder>{

    private List<JlSingeDetectionBean.DataBean> mList;

    public SingeAdapter(List<JlSingeDetectionBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SingeAdapter.MyViewHolder holder = new SingeAdapter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_singe_item, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mType.setText(mList.get(i).getServer_type());//服务类型
        myViewHolder.mTime.setText(mList.get(i).getExpected_time());//期望时间
        myViewHolder.mRegion.setText(mList.get(i).getU_addres());//地区
        myViewHolder.mQuote.setText(mList.get(i).getDetection_price());//报价

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mType;
        TextView mTime;
        TextView mRegion;//地区
        TextView mQuote;//报价
        Button mGrab;

        public MyViewHolder(View view)
        {
            super(view);
            mType = (TextView) view.findViewById(R.id.tv_server_type);
            mTime = (TextView) view.findViewById(R.id.tv_qwtime);
            mRegion = (TextView) view.findViewById(R.id.tv_region);
            mQuote = (TextView) view.findViewById(R.id.tv_quote);
            mGrab = (Button) view.findViewById(R.id.btn_grab_single);
        }
    }
}
