package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.DetectionDetailsBean;
import com.lyp.jsonbean.IndoorDetailsBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/2 13: 37
 */
//抢单详情(室内)
public class IndoorAdapter extends RecyclerView.Adapter<IndoorAdapter.MyViewHolder>{

    private List<IndoorDetailsBean.DataBean> mList;

    public IndoorAdapter(List<IndoorDetailsBean.DataBean> list) {
        mList = list;
    }

    @Override
    public IndoorAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        IndoorAdapter.MyViewHolder holder = new IndoorAdapter.MyViewHolder(LayoutInflater.from
                (viewGroup.getContext()).inflate(R.layout.activity_singe_details, viewGroup, false));
        return holder;
    }


    @Override
    public void onBindViewHolder(IndoorAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mNmae.setText(mList.get(i).getUser_name());
        myViewHolder.mPhone.setText(mList.get(i).getU_phone());
        myViewHolder.mAddress.setText(mList.get(i).getU_addres());
        myViewHolder.mLocation.setText(mList.get(i).getPoint_position());
        myViewHolder.mLsarea.setText(mList.get(i).getCovered_area());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        myViewHolder.mOrderNumber.setText(mList.get(i).getOrder_number());

        myViewHolder.mCar.setVisibility(View.GONE);
        myViewHolder.mCarbrand.setVisibility(View.GONE);
        myViewHolder.mCarAge.setVisibility(View.GONE);
        myViewHolder.mCarLsnumber.setVisibility(View.GONE);
        myViewHolder.mArea.setVisibility(View.VISIBLE);
        myViewHolder.mLocations.setVisibility(View.VISIBLE);
        myViewHolder.mAddresss.setVisibility(View.VISIBLE);
        myViewHolder.mAgeType.setVisibility(View.GONE);
        myViewHolder.mNumberType.setVisibility(View.GONE);

        myViewHolder.mViewArea.setVisibility(View.VISIBLE);
        myViewHolder.mViewAge.setVisibility(View.GONE);
        myViewHolder.mViewBrand.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNmae,mPhone,mAddress,mLocation,mLsarea,mTime,mOrderNumber;
        LinearLayout mCar,mCarbrand,mCarAge,mCarLsnumber;
        LinearLayout mArea,mLocations,mAddresss,mAgeType,mNumberType;
        TextView mViewArea,mViewBrand,mViewAge;

        public MyViewHolder(View view) {
            super(view);
            mNmae = (TextView) view.findViewById(R.id.tv_cardetailsname);
            mPhone = (TextView) view.findViewById(R.id.tv_singedatailsphone);
            mAddress = (TextView) view.findViewById(R.id.tv_detailsaddress);
            mLocation = (TextView) view.findViewById(R.id.tv_detailslocation);
            mLsarea= (TextView) view.findViewById(R.id.tv_detailsarea);
            mTime = (TextView) view.findViewById(R.id.tv_cardetailstime);
            mOrderNumber = (TextView) view.findViewById(R.id.tv_cardetailsordernumber);
            mCar = (LinearLayout) view.findViewById(R.id.ll_car);
            mCarbrand = (LinearLayout) view.findViewById(R.id.ll_carbrand);
            mCarAge = (LinearLayout) view.findViewById(R.id.ll_carage);
            mCarLsnumber = (LinearLayout) view.findViewById(R.id.ll_carlsnumber);
            mArea = (LinearLayout) view.findViewById(R.id.ll_area);
            mLocations = (LinearLayout) view.findViewById(R.id.ll_locations);
            mAddresss = (LinearLayout) view.findViewById(R.id.ll_address);

            mAgeType = (LinearLayout) view.findViewById(R.id.ll_age_type);
            mNumberType = (LinearLayout) view.findViewById(R.id.ll_number_type);
            //View
            mViewArea = (TextView) view.findViewById(R.id.view_area);
            mViewBrand = (TextView) view.findViewById(R.id.view_carbrand);
            mViewAge = (TextView) view.findViewById(R.id.view_age);
        }
    }
}