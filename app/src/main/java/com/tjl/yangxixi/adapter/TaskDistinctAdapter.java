package com.tjl.yangxixi.adapter;


import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.StoreEmployeeInfoBean;

/**
 *  門店顧問
 * @author Administrator
 *
 */
public class TaskDistinctAdapter extends BaseAdapter {

	private List<StoreEmployeeInfoBean> seList;
	private Context context;
	private LayoutInflater inflater;
	private Handler handler;

	public TaskDistinctAdapter(List<StoreEmployeeInfoBean> seList, Context context,Handler handler){
		this.seList = seList;
		this.context = context;
		this.handler=handler;
		inflater = LayoutInflater.from(context);

	}
	@Override
	public int getCount() {
		return seList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return seList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Halder mHalder = null;
		if(arg1 == null){
			mHalder = new Halder();
			arg1 = inflater.inflate(R.layout.adapter_manager_taskdist, null);
			mHalder.tv_name = (TextView) arg1.findViewById(R.id.tv_name);
			mHalder.iv_check = (ImageView) arg1.findViewById(R.id.iv_check);
			mHalder.tv_position = (TextView) arg1.findViewById(R.id.tv_position);
			arg1.setTag(mHalder);
		} else
			mHalder = (Halder) arg1.getTag();
		final StoreEmployeeInfoBean se = seList.get(arg0);

		mHalder.tv_name.setText(se.getEmp_name());
		mHalder.tv_position.setText(se.getPosition());
		if(se.isCheck()){
			mHalder.iv_check.setImageResource(R.drawable.task_click);
			Message msg=Message.obtain();
			msg.what=1;
			msg.obj=se.getEmp_id();
			handler.sendMessage(msg);
		} else
			mHalder.iv_check.setImageResource(R.drawable.task_no);
		mHalder.iv_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				for (StoreEmployeeInfoBean s : seList) {
					if(s.getEmp_id().equals(se.getEmp_id())){
						s.setCheck(true);
					}else s.setCheck(false);
				}
				TaskDistinctAdapter.this.notifyDataSetChanged();
			}
		});
		return arg1;
	}


	private class Halder{
		private TextView tv_name;
		private ImageView iv_check;
		private TextView tv_position;

	}
}
