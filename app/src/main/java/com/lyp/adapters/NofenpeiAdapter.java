package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.jsonbean.NofenpeiBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
//未分配
public class NofenpeiAdapter extends RecyclerView.Adapter<NofenpeiAdapter.MyViewHolder> {

    private MyItemClickListener mListener;
    private List<NofenpeiBean.DataBean> mList;

    public NofenpeiAdapter(List<NofenpeiBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_undistributed, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mName.setText(mList.get(i).getUser_name());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
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

        CheckBox mcheckBox;
        TextView mName;
        TextView mTime;
        LinearLayout mNofenpei;

        public MyViewHolder(View view) {
            super(view);
            mcheckBox = (CheckBox) view.findViewById(R.id.checkBox);
            mName = (TextView) view.findViewById(R.id.tv_notaskname);
            mTime = (TextView) view.findViewById(R.id.tv_notasktime);
            mNofenpei = (LinearLayout) view.findViewById(R.id.ll_nofenpei);
            mNofenpei.setOnClickListener(new View.OnClickListener() {
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
