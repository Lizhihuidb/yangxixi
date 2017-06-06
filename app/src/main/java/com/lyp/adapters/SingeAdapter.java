package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lyp.jsonbean.JlSingeDetectionBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * 作者:ChenXi 时间:2017/5/31 14: 26
 */
//抢单(检测)
public class SingeAdapter extends RecyclerView.Adapter<SingeAdapter.MyViewHolder>{

    private List<JlSingeDetectionBean.DataBean> mList;
    private SingeAdapter.MyItemClickListener mListener;
    public SingeAdapter(List<JlSingeDetectionBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SingeAdapter.MyViewHolder holder = new SingeAdapter.MyViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_singe_item, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            myViewHolder.mTime.setText(mList.get(i).getExpected_time());//期望时间
            myViewHolder.mType.setText(mList.get(i).getDetection_type());//检测类型
            myViewHolder.mRegion.setText(mList.get(i).getU_addres());//地区
            myViewHolder.mQuote.setText(mList.get(i).getDetection_price());//报价
            myViewHolder.mHome.setVisibility(View.VISIBLE);
            if (mList.get(i).getDetection_type().equals("复检")){
                myViewHolder.mBtnjoin.setVisibility(View.VISIBLE);
            }else if (mList.get(i).getDetection_type().equals("初检")){
                myViewHolder.mBtnjoin.setVisibility(View.GONE);
            }else if (mList.get(i).getDetection_type().equals("检测")){
                myViewHolder.mBtnjoin.setVisibility(View.GONE);
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
    public void setOnItemClickListener(SingeAdapter.MyItemClickListener listener){
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mType;
        TextView mTime;
        TextView mRegion;//地区
        TextView mQuote;//报价
        TextView mHome;//是否居家
        Button mGrab;
        RelativeLayout mSingeAll;
        Button mBtnjoin,mBtngrab;

        public MyViewHolder(View view)
        {
            super(view);
            mType = (TextView) view.findViewById(R.id.tv_server_type);
            mTime = (TextView) view.findViewById(R.id.tv_qwtime);
            mRegion = (TextView) view.findViewById(R.id.tv_region);
            mQuote = (TextView) view.findViewById(R.id.tv_quote);
            mGrab = (Button) view.findViewById(R.id.btn_grab_single);
            mHome = (TextView) view.findViewById(R.id.tv_jujia);
            mBtngrab = (Button) view.findViewById(R.id.btn_grab_single);
            mBtnjoin = (Button) view.findViewById(R.id.btn_join_single);
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
