package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lyp.jsonbean.JLSingeIndoorBean;
import com.tjl.yangxixi.R;

import java.util.List;
/**
 * 作者:ChenXi 时间:2017/5/31 14: 26
 */
//抢单(室内)
public class SingeIndoorAdapter extends RecyclerView.Adapter<SingeIndoorAdapter.MyViewHolder>{

    private List<JLSingeIndoorBean.DataBean> mList;
    private SingeIndoorAdapter.MyItemClickListener mListener;
    public SingeIndoorAdapter(List<JLSingeIndoorBean.DataBean> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SingeIndoorAdapter.MyViewHolder holder = new SingeIndoorAdapter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_singe_item, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            myViewHolder.mType.setText("治理");//检测类型
            myViewHolder.mTime.setText(mList.get(i).getExpected_time());//期望时间
            myViewHolder.mRegion.setText(mList.get(i).getU_addres());//地区
            myViewHolder.mQuote.setText(mList.get(i).getSquare_meter_price());//报价
            myViewHolder.mArea.setText(mList.get(i).getCovered_area());//建筑面积
            myViewHolder.mHome.setVisibility(View.VISIBLE);
            myViewHolder.mSingeArea.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(SingeIndoorAdapter.MyItemClickListener listener){
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mType;
        TextView mTime;
        TextView mRegion;//地区
        TextView mQuote;//报价
        TextView mHome;//是否居家
        Button mGrab;
        RelativeLayout mSingeArea;
        TextView mArea;
        RelativeLayout mSingeAll;

        public MyViewHolder(View view)
        {
            super(view);
            mType = (TextView) view.findViewById(R.id.tv_server_type);
            mTime = (TextView) view.findViewById(R.id.tv_qwtime);
            mRegion = (TextView) view.findViewById(R.id.tv_region);
            mQuote = (TextView) view.findViewById(R.id.tv_quote);
            mGrab = (Button) view.findViewById(R.id.btn_grab_single);
            mHome = (TextView) view.findViewById(R.id.tv_jujia);
            mSingeArea = (RelativeLayout) view.findViewById(R.id.rl_singe_area);
            mArea = (TextView) view.findViewById(R.id.tv_area);
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
