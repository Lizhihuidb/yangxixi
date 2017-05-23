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
import com.tjl.yangxixi.bean.FollManClueSaleInfo;

/**
 *  销售线索
 * @author Administrator
 *
 */
public class ConsultantMAdapter extends BaseAdapter {

	private List<FollManClueSaleInfo> consultantList;
	private Context context;
	private LayoutInflater inflater;

	public ConsultantMAdapter(List<FollManClueSaleInfo> consultantList, Context context){
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
			arg1 = inflater.inflate(R.layout.adapter_consultant, null);
			mHalder.tv_name = (TextView) arg1.findViewById(R.id.tv_name);
			mHalder.iv_sex = (ImageView) arg1.findViewById(R.id.iv_sex);
			mHalder.tv_age = (TextView) arg1.findViewById(R.id.tv_age);
			mHalder.iv_phone = (ImageView) arg1.findViewById(R.id.iv_phone);
			mHalder.tv_phone_num = (TextView) arg1.findViewById(R.id.tv_phone_num);
			mHalder.tv_xs_name = (TextView) arg1.findViewById(R.id.tv_xs_name);
			mHalder.tv_id=(TextView) arg1.findViewById(R.id.tv_id);
			mHalder.tv_con_phone=(TextView) arg1.findViewById(R.id.tv_con_phone);

			arg1.setTag(mHalder);
		} else
			mHalder = (Halder) arg1.getTag();
		FollManClueSaleInfo con = consultantList.get(arg0);

		mHalder.tv_name.setText(con.getCus_name());
		mHalder.tv_age.setText(con.getCus_age()+"");
		mHalder.tv_id.setText(con.getId());
		mHalder.tv_con_phone.setText(con.getCus_phone());
		String sex = con.getCus_sex();
		if(sex.equals("女")){
			mHalder.iv_sex.setImageResource(R.drawable.icon_woman);
			mHalder.tv_age.setTextColor(context.getResources().getColor(R.color.age_woman));
		} else {
			mHalder.iv_sex.setImageResource(R.drawable.icon_man);
			mHalder.tv_age.setTextColor(context.getResources().getColor(R.color.age_man));
		}

		int count = con.getCout();
		if(count < 1){
			//mHalder.iv_phone.setImageResource(R.drawable.phone_no);
			mHalder.tv_phone_num.setText("0");
			mHalder.tv_phone_num.setTextColor(context.getResources().getColor(R.color.phone_no));
		} else {
			//mHalder.iv_phone.setImageResource(R.drawable.icon_nogenjin);
			mHalder.tv_phone_num.setText(count+"");
			mHalder.tv_phone_num.setTextColor(context.getResources().getColor(R.color.phone_click));
		}
		mHalder.tv_xs_name.setText(con.getEmp_name());

		return arg1;
	}


	private class Halder{
		private TextView tv_name;
		private ImageView iv_sex;
		private TextView tv_age;
		private ImageView iv_phone;
		private TextView tv_phone_num;
		private TextView tv_xs_name;
		private TextView tv_id,tv_con_phone;

	}
}
