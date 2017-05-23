package com.tjl.yangxixi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.ConsultantInfo;


/**
 * 计划安排
 * @author Administrator
 *
 */
public class PlanArrangeAdapter extends BaseAdapter {

	private List<ConsultantInfo> consultantList;
	private Context context;
	private LayoutInflater inflater;

	public PlanArrangeAdapter(List<ConsultantInfo> consultantList, Context context){
		this.consultantList = consultantList;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}
	@Override
	public int getCount() {
		return consultantList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return consultantList.get(arg0);
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
			arg1 = inflater.inflate(R.layout.adapter_planarrange, null);
			mHalder.tv_name = (TextView) arg1.findViewById(R.id.tv_name);
			mHalder.iv_sex = (ImageView) arg1.findViewById(R.id.iv_sex);
			mHalder.tv_age = (TextView) arg1.findViewById(R.id.tv_age);
			mHalder.iv_phone = (ImageView) arg1.findViewById(R.id.iv_phone);
			mHalder.tv_phone_num = (TextView) arg1.findViewById(R.id.tv_phone_num);
			mHalder.tv_time = (TextView) arg1.findViewById(R.id.tv_time);
			arg1.setTag(mHalder);
		} else
			mHalder = (Halder) arg1.getTag();
		ConsultantInfo con = consultantList.get(arg0);

		mHalder.tv_name.setText(con.getCus_name());
		mHalder.tv_age.setText(con.getCus_age()+"");
		String sex = con.getCus_sex();
		if(sex.equals("女")){
			mHalder.iv_sex.setImageResource(R.drawable.icon_woman);
			mHalder.tv_age.setTextColor(context.getResources().getColor(R.color.age_woman));
		} else {
			mHalder.iv_sex.setImageResource(R.drawable.icon_man);
			mHalder.tv_age.setTextColor(context.getResources().getColor(R.color.age_man));
		}

		int count = con.getCunt();
		if(count < 1){
			//mHalder.iv_phone.setImageResource(R.drawable.phone_no);
			mHalder.tv_phone_num.setText("0"+"次");
			mHalder.tv_phone_num.setTextColor(context.getResources().getColor(R.color.phone_no));
		} else {
			//mHalder.iv_phone.setImageResource(R.drawable.phone_click);
			mHalder.tv_phone_num.setText(count+"次");
			mHalder.tv_phone_num.setTextColor(context.getResources().getColor(R.color.phone_click));
		}
		mHalder.tv_time.setText(con.getBookTime());

		return arg1;
	}


	private class Halder{
		private TextView tv_name;
		private ImageView iv_sex;
		private TextView tv_age;
		private ImageView iv_phone;
		private TextView tv_phone_num;
		private TextView tv_time;

	}
}
