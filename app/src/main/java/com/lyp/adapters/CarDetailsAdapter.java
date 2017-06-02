package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/2 13: 37
 */
//接单详情(车内)
public class CarDetailsAdapter extends RecyclerView.Adapter<CarDetailsAdapter.MyViewHolder>{

    private List<CarOrdersDetailsBean.DataBean> mList;

    public CarDetailsAdapter(List<CarOrdersDetailsBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CarDetailsAdapter.MyViewHolder holder = new CarDetailsAdapter.MyViewHolder(LayoutInflater.from
                (viewGroup.getContext()).inflate(R.layout.activity_singe_details, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mNmae.setText(mList.get(i).getUser_name());
        myViewHolder.mPhone.setText(mList.get(i).getU_phone());
        myViewHolder.mCarType.setText(mList.get(i).getUser_car_type());
        myViewHolder.mBrand.setText(mList.get(i).getCar_type());
        myViewHolder.mAge.setText(mList.get(i).getCar_age());
        myViewHolder.mNumber.setText(mList.get(i).getLicense_number());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        myViewHolder.mOrderNumber.setText(mList.get(i).getOrder_number());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNmae;
        TextView mPhone;
        TextView mCarType;
        TextView mBrand;
        TextView mAge;
        TextView mNumber;
        TextView mTime;
        TextView mOrderNumber;

        public MyViewHolder(View view) {
            super(view);
            mNmae = (TextView) view.findViewById(R.id.tv_cardetailsname);
            mPhone = (TextView) view.findViewById(R.id.tv_singedatailsphone);
            mCarType = (TextView) view.findViewById(R.id.tv_cardetailstype);
            mBrand = (TextView) view.findViewById(R.id.tv_cardetailsbrand);
            mAge = (TextView) view.findViewById(R.id.tv_cardetailsage);
            mNumber = (TextView) view.findViewById(R.id.tv_cardetailsnumber);
            mTime = (TextView) view.findViewById(R.id.tv_cardetailstime);
            mOrderNumber = (TextView) view.findViewById(R.id.tv_cardetailsordernumber);
        }
    }
}
