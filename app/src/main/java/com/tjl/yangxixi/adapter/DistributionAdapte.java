package com.tjl.yangxixi.adapter;

import java.util.List;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.DistributionScreeningBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DistributionAdapte extends BaseAdapter{
	private Context context;
	private List<DistributionScreeningBean> list;

	public DistributionAdapte(Context context,List<DistributionScreeningBean> list) {
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_distribution_item, null);
			holder.img_distribution_icon = (ImageView) convertView.findViewById(R.id.img_distribution_icon);
			holder.tv_distribution_count=(TextView) convertView.findViewById(R.id.tv_distribution_count);
			holder.tv_distribution_name=(TextView) convertView.findViewById(R.id.tv_distribution_name);
			holder.tv_distribution_id=(TextView) convertView.findViewById(R.id.tv_distribution_id);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.img_distribution_icon.setImageResource(list.get(position).getIcon());
		holder.tv_distribution_count.setText(list.get(position).getCount());
		holder.tv_distribution_name.setText(list.get(position).getDescribe());
		holder.tv_distribution_id.setText(list.get(position).getId()+"");
		return convertView;
	}
	class ViewHolder{
		ImageView img_distribution_icon;
		TextView tv_distribution_name,tv_distribution_count,tv_distribution_id;
	}

}
