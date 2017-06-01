package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lyp.jsonbean.JLCarBean;
import com.lyp.jsonbean.JlSingeDetectionBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/31 14: 26
 */
//接单(车内)
public class SingeCarAdapter extends RecyclerView.Adapter<SingeCarAdapter.MyViewHolder>{

    private List<JLCarBean.DataBean> mList;
    private SingeCarAdapter.MyItemClickListener mListener;
    public SingeCarAdapter(List<JLCarBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SingeCarAdapter.MyViewHolder holder = new SingeCarAdapter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_singe_item, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            myViewHolder.mType.setText(mList.get(i).getServer_type());//类型
            myViewHolder.mTime.setText(mList.get(i).getExpected_time());//期望时间
            myViewHolder.mMotorcycle.setText(mList.get(i).getShop_car_type());//车型
            myViewHolder.mCarnumber.setText(mList.get(i).getLicense_number());//车牌
            myViewHolder.mSingeRegion.setVisibility(View.GONE);
            myViewHolder.mSingeCar.setVisibility(View.VISIBLE);
            myViewHolder.mBaojia.setVisibility(View.GONE);
            myViewHolder.mGrab.setVisibility(View.GONE);
            myViewHolder.mJoin.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(SingeCarAdapter.MyItemClickListener listener){
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mType;
        TextView mTime;
        TextView mMotorcycle;//车型
        TextView mCarnumber;//车牌
        Button mJoin,mGrab;
        RelativeLayout mSingeRegion,mSingeCar,mBaojia,mSingeAll;

        public MyViewHolder(View view)
        {
            super(view);
            mType = (TextView) view.findViewById(R.id.tv_server_type);
            mTime = (TextView) view.findViewById(R.id.tv_qwtime);
            mGrab = (Button) view.findViewById(R.id.btn_grab_single);//抢单
            mJoin = (Button) view.findViewById(R.id.btn_join_single);//接单
            mMotorcycle = (TextView) view.findViewById(R.id.tv_carxing);
            mCarnumber = (TextView) view.findViewById(R.id.tv_carnumber);
            mSingeRegion = (RelativeLayout) view.findViewById(R.id.rl_singe_region);//地区
            mSingeCar = (RelativeLayout) view.findViewById(R.id.rl_singe_car);
            mBaojia = (RelativeLayout) view.findViewById(R.id.rl_baojia);//报价
            mSingeAll = (RelativeLayout) view.findViewById(R.id.rl_singeAll);
            mSingeAll.setOnClickListener(new View.OnClickListener() {
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
