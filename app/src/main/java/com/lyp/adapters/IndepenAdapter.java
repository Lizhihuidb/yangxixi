package com.lyp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyp.jsonbean.AllTaskBean;
import com.lyp.jsonbean.IndependentBean;
import com.tjl.yangxixi.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
//自主分配
public class IndepenAdapter extends RecyclerView.Adapter<IndepenAdapter.MyViewHolder>{
    private IndependentBean mList;
    public IndepenAdapter(IndependentBean list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.itme_indepen, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mNumber;

        public MyViewHolder(View view)
        {
            super(view);
            mNumber = (TextView) view.findViewById(R.id.tv_indepen_number);
            mName = (TextView) view.findViewById(R.id.tv_indepen_name);

        }
   }

}
