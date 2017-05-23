package com.tjl.yangxixi.adapter;

import java.util.List;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.MissionGwBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MissionAdapte extends BaseAdapter{
	private Context context;
	private List<MissionGwBean> list;

	public MissionAdapte(Context context,List<MissionGwBean> list) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_my_mission, null);
			holder.iv_sales_sexGw=(ImageView) convertView.findViewById(R.id.iv_sales_sexGw);
			holder.iv_sales_phoneGw=(ImageView) convertView.findViewById(R.id.iv_sales_phoneGw);
			holder.tv_mission_nameGw=(TextView) convertView.findViewById(R.id.tv_mission_nameGw);
			holder.tv_sales_ageGw=(TextView) convertView.findViewById(R.id.tv_sales_ageGw);
			holder.tv_mission_id=(TextView) convertView.findViewById(R.id.tv_mission_id);
			holder.tv_phone_numberGw=(TextView) convertView.findViewById(R.id.tv_phone_numberGw);
			holder.lay_appointment_statusGw=(LinearLayout) convertView.findViewById(R.id.lay_appointment_statusGw);
			holder.tv_mission_phone=(TextView) convertView.findViewById(R.id.tv_mymission_phone);
			holder.tv_appointment_status=(TextView) convertView.findViewById(R.id.tv_appointment_status);
			holder.iv_appointment_statusGw=(ImageView) convertView.findViewById(R.id.iv_appointment_statusGw);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		MissionGwBean bean = list.get(position);
		if (bean.getCus_sex().equals("男")) {
			holder.iv_sales_sexGw.setImageResource(R.drawable.icon_man);
		} else {
			holder.iv_sales_sexGw.setImageResource(R.drawable.icon_woman);
		}
		holder.tv_mission_phone.setText(bean.getCus_phone());
		holder.tv_mission_nameGw.setText(bean.getCus_name());
		holder.tv_sales_ageGw.setText(bean.getCus_age()+"岁");
		holder.tv_phone_numberGw.setText(bean.getCunt()+"次");
		holder.tv_mission_id.setText(bean.getId()+"");
		if(bean.getBstate()>0){
			holder.lay_appointment_statusGw.setVisibility(View.VISIBLE);
		}else{
			holder.lay_appointment_statusGw.setVisibility(View.INVISIBLE);
		}
		if(bean.getOverdue()>0){
			holder.tv_appointment_status.setText("预约已过期");
			holder.iv_appointment_statusGw.setImageResource(R.drawable.icon_youyugq);
		}else{
			holder.tv_appointment_status.setText("有预约");
			holder.iv_appointment_statusGw.setImageResource(R.drawable.icon_youyuyue);
		}
		return convertView;
	}
	class ViewHolder{
		ImageView iv_sales_sexGw,iv_sales_phoneGw,iv_appointment_statusGw;
		TextView tv_mission_nameGw,tv_sales_ageGw,tv_phone_numberGw,tv_mission_id,tv_mission_phone,tv_appointment_status;
		LinearLayout lay_appointment_statusGw;
	}

}
