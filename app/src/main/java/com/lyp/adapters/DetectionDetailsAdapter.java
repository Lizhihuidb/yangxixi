package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.jsonbean.CarOrdersDetailsBean;
import com.lyp.jsonbean.DetectionDetailsBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/6/2 13: 37
 */
//抢单详情(检测)
public class DetectionDetailsAdapter extends RecyclerView.Adapter<DetectionDetailsAdapter.MyViewHolder>{

    private List<DetectionDetailsBean.DataBean> mList;

    public DetectionDetailsAdapter(List<DetectionDetailsBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        DetectionDetailsAdapter.MyViewHolder holder = new DetectionDetailsAdapter.MyViewHolder(LayoutInflater.from
                (viewGroup.getContext()).inflate(R.layout.activity_singe_details, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.mNmae.setText(mList.get(i).getUser_name());
        myViewHolder.mPhone.setText(mList.get(i).getU_phone());
        myViewHolder.mAddress.setText(mList.get(i).getU_addres());
        myViewHolder.mSelection.setText(mList.get(i).getPoint_position());
        myViewHolder.mAssaytype.setText(mList.get(i).getBit_type());
        myViewHolder.mLsarea.setText(mList.get(i).getCovered_area());
        myViewHolder.mTime.setText(mList.get(i).getPay_time());
        myViewHolder.mOrderNumber.setText(mList.get(i).getOrder_number());

        myViewHolder.mCar.setVisibility(View.GONE);
        myViewHolder.mCarbrand.setVisibility(View.GONE);
        myViewHolder.mAgeType.setVisibility(View.GONE);
        myViewHolder.mCarLsnumber.setVisibility(View.GONE);

        myViewHolder.mAreas.setVisibility(View.VISIBLE);
        myViewHolder.mAddresss.setVisibility(View.VISIBLE);
        myViewHolder.mSelections.setVisibility(View.VISIBLE);
        myViewHolder.mAssaytypes.setVisibility(View.VISIBLE);

        myViewHolder.mViewArea.setVisibility(View.VISIBLE);
        myViewHolder.mViewAge.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNmae,mPhone,mAddress,mSelection,mAssaytype,mLsarea,mTime,mOrderNumber;
        LinearLayout mCar,mCarbrand,mAgeType,mCarLsnumber;
        LinearLayout mAreas,mAddresss,mSelections,mAssaytypes;
        TextView mViewArea,mViewAge;

        public MyViewHolder(View view) {
            super(view);
            mNmae = (TextView) view.findViewById(R.id.tv_cardetailsname);
            mPhone = (TextView) view.findViewById(R.id.tv_singedatailsphone);
            mAddress = (TextView) view.findViewById(R.id.tv_detailsaddress);
            mSelection = (TextView) view.findViewById(R.id.tv_selection);
            mAssaytype = (TextView) view.findViewById(R.id.tv_detailsassaytype);
            mLsarea= (TextView) view.findViewById(R.id.tv_detailsarea);
            mTime = (TextView) view.findViewById(R.id.tv_cardetailstime);
            mOrderNumber = (TextView) view.findViewById(R.id.tv_cardetailsordernumber);


            mCar = (LinearLayout) view.findViewById(R.id.ll_car);
            mCarbrand = (LinearLayout) view.findViewById(R.id.ll_carbrand);
            mAgeType = (LinearLayout) view.findViewById(R.id.ll_age_type);
            mCarLsnumber = (LinearLayout) view.findViewById(R.id.ll_carlsnumber);

            mAreas = (LinearLayout) view.findViewById(R.id.ll_area);
            mAddresss = (LinearLayout) view.findViewById(R.id.ll_address);
            mSelections = (LinearLayout) view.findViewById(R.id.ll_selection);
            mAssaytypes = (LinearLayout) view.findViewById(R.id.ll_assaytype);

            //View
            mViewArea = (TextView) view.findViewById(R.id.view_area);
            mViewAge = (TextView) view.findViewById(R.id.view_age);
        }
    }
}
