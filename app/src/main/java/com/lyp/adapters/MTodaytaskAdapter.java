package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.jsonbean.MAllTaskBean;
import com.lyp.jsonbean.MTodayBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
//今日任务
public class MTodaytaskAdapter extends RecyclerView.Adapter<MTodaytaskAdapter.MyViewHolder>{

    private List<MTodayBean.DataBean> mList;
    private MyItemClickListener mListener;
    public MTodaytaskAdapter(List<MTodayBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MTodaytaskAdapter.MyViewHolder holder = new MTodaytaskAdapter.MyViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mission_gw, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mName.setText(mList.get(i).getU_name());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        if (mList.get(i).getSubscriber_type().equals("0")){
            myViewHolder.mPhon.setImageResource(R.drawable.yiyuyue);
        }else if (mList.get(i).getSubscriber_idold().equals("1")){
            myViewHolder.mPhon.setImageResource(R.drawable.yuyueyiguoqi);
        }else if (mList.get(i).getSubscriber_isdate().equals("1")){
            myViewHolder.mPhon.setImageResource(R.drawable.yiyuyuejin);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mTime;
        ImageView mPhon;
        LinearLayout mTesttask;

        public MyViewHolder(View view)
        {
            super(view);
            mName = (TextView) view.findViewById(R.id.tv_mtask_name);
            mTime = (TextView) view.findViewById(R.id.tv_mtask_time);
            mPhon = (ImageView) view.findViewById(R.id.tv_mtask_phon);
            mTesttask = (LinearLayout) view.findViewById(R.id.ll_my_mission);
            mTesttask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,getPosition());
                }
            });
        }
   }

    /**
     * item点击接口
     */
    public interface MyItemClickListener {
        void onItemClick(View v, int position);
    }

}
