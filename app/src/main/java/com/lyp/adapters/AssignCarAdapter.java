package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyp.jsonbean.AssignCarBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/5 17: 54
 */
//车内任务详情
public class AssignCarAdapter extends RecyclerView.Adapter<AssignCarAdapter.MyViewHolder> {

    private List<AssignCarBean.DataBean> mList;

    public AssignCarAdapter(List<AssignCarBean.DataBean> list) {
        mList = list;
    }

    @Override
    public AssignCarAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AssignCarAdapter.MyViewHolder holder = new AssignCarAdapter.MyViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assigntask, viewGroup,
                        false));
        return holder;
    }

    @Override
    public void onBindViewHolder(AssignCarAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mName.setText(mList.get(i).getUser_name());
        myViewHolder.mPhone.setText(mList.get(i).getU_phone());
        myViewHolder.mType.setText(mList.get(i).getCar_type());
        myViewHolder.mBrand.setText(mList.get(i).getUser_car_type());
        myViewHolder.mAge.setText(mList.get(i).getCar_age());
        myViewHolder.mNumber.setText(mList.get(i).getLicense_number());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        myViewHolder.mOrderNumber.setText(mList.get(i).getOrder_number());
        myViewHolder.mOrder.setText(mList.get(i).getSubscriber_time());
        myViewHolder.mRemark.setText(mList.get(i).getNote());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mName,mPhone,mType,mBrand,mAge;
        TextView mNumber,mTime,mOrderNumber,mOrder,mRemark;


        public MyViewHolder(View view) {
            super(view);
            mName = (TextView) view.findViewById(R.id.tv_assigncar_name);
            mPhone = (TextView) view.findViewById(R.id.tv_assigncar_phone);
            mType = (TextView) view.findViewById(R.id.tv_assigncar_type);
            mBrand = (TextView) view.findViewById(R.id.tv_assigncar_brand);
            mAge = (TextView) view.findViewById(R.id.tv_assigncar_age);
            mNumber = (TextView) view.findViewById(R.id.tv_assigncar_number);
            mTime = (TextView) view.findViewById(R.id.tv_assigncar_time);
            mOrderNumber = (TextView) view.findViewById(R.id.tv_assigncar_ordernumber);
            mOrder = (TextView) view.findViewById(R.id.tv_assigncar_order);
            mRemark = (TextView) view.findViewById(R.id.tv_assigncar_remark);
        }
    }

}
