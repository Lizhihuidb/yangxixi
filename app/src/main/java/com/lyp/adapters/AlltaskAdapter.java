package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tjl.yangxixi.R;
import com.lyp.jsonbean.AllTaskBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class AlltaskAdapter extends RecyclerView.Adapter<AlltaskAdapter.MyViewHolder>{
    private List<AllTaskBean.DataBean> mList;
    private MyItemClickListener mListener;
    public AlltaskAdapter(List<AllTaskBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testtask, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mState.setText(mList.get(i).getDistribute_type());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        myViewHolder.mName.setText(mList.get(i).getUser_name());
        if (mList.get(i).getDistribute_type().equals("0")){
            myViewHolder.mState.setText("未分配");
        }else if(mList.get(i).getSubscriber_type().equals("0")){
            myViewHolder.mState.setText("未预约");
        }else if (mList.get(i).getSubscriber_type().equals("1")){
            myViewHolder.mState.setText("已预约");
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
        TextView mState;
        TextView mTime;
        LinearLayout mTesttask;

        public MyViewHolder(View view)
        {
            super(view);
            mName = (TextView) view.findViewById(R.id.tv_taskname);
            mState = (TextView) view.findViewById(R.id.tv_taskallocation);
            mTime = (TextView) view.findViewById(R.id.tv_tasktime);
            mTesttask = (LinearLayout) view.findViewById(R.id.ll_testtask);
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
