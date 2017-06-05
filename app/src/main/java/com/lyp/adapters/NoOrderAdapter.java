package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.jsonbean.NoOrderBean;
import com.lyp.jsonbean.YesOrderBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
//未预约
public class NoOrderAdapter extends RecyclerView.Adapter<NoOrderAdapter.MyViewHolder> {

    private NoOrderAdapter.MyItemClickListener mListener;
    private List<NoOrderBean.DataBean> mList;

    public NoOrderAdapter(List<NoOrderBean.DataBean> list) {
        mList = list;
    }

    @Override
    public NoOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        NoOrderAdapter.MyViewHolder holder = new NoOrderAdapter.MyViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_no_order, viewGroup,
                        false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mName.setText(mList.get(i).getUser_name());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        myViewHolder.mMemberName.setText(mList.get(i).getUser());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(NoOrderAdapter.MyItemClickListener listener){
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mTime;
        TextView mMemberName;
        TextView mNoOrder,mYesOrder;
        LinearLayout mOrder;

        public MyViewHolder(View view) {
            super(view);
            mName = (TextView) view.findViewById(R.id.tv_yesorder_name);
            mTime = (TextView) view.findViewById(R.id.tv_yesorder_time);
            mMemberName = (TextView) view.findViewById(R.id.tv_membername);
            mNoOrder = (TextView) view.findViewById(R.id.tv_no_yuyue);
            mYesOrder = (TextView) view.findViewById(R.id.tv_yes_yuyue);
            mOrder = (LinearLayout) view.findViewById(R.id.ll_yuyue);
            mYesOrder.setVisibility(View.GONE);
            mNoOrder.setVisibility(View.VISIBLE);
            mOrder.setOnClickListener(new View.OnClickListener() {
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
