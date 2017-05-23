package com.tjl.yangxixi.adapter;

import java.util.List;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.NoDistributionBean;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class NoDistributionAdapte extends BaseAdapter {
	private Context context;
	private List<NoDistributionBean> list;
	private Handler handler;

	public NoDistributionAdapte(Context context, List<NoDistributionBean> list,Handler handler) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.handler=handler;
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
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.adapter_manager_salesclue, null);
			holder.checkBox_salesclue = (CheckBox) convertView
					.findViewById(R.id.checkBox_salesclue);
			holder.tv_sales_name = (TextView) convertView
					.findViewById(R.id.tv_sales_name);
			holder.tv_sales_age = (TextView) convertView
					.findViewById(R.id.tv_sales_age);
			holder.tv_phone_number = (TextView) convertView
					.findViewById(R.id.tv_phone_number);
			holder.iv_sales_sex = (ImageView) convertView
					.findViewById(R.id.iv_sales_sex);
			holder.iv_sales_phone = (ImageView) convertView
					.findViewById(R.id.iv_sales_phone);
			holder.tv_salesclue_phone=(TextView) convertView.findViewById(R.id.tv_salesclue_phone);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final NoDistributionBean bean = list.get(position);
		// holder.checkBox_salesclue.setChecked(bean.isCheboxStatus());
		holder.tv_sales_name.setText(bean.getCus_name());
		holder.tv_sales_age.setText(bean.getCus_age() + "岁");
		holder.tv_phone_number.setText(bean.getCunt() + "次");
		holder.tv_salesclue_phone.setText(bean.getCus_phone());
		if (bean.getCus_sex().equals("男")) {
			holder.iv_sales_sex.setImageResource(R.drawable.icon_man);
		} else {
			holder.iv_sales_sex.setImageResource(R.drawable.icon_woman);
		}

		holder.checkBox_salesclue.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				// TODO Auto-generated method stub
				// distributionID.put(bean.getId(), bean.getId());
				Message message = Message.obtain();
				if (isChecked) {
					bean.setType(NoDistributionBean.TYPE_CHECKED);
					message.what=2;
					message.obj=bean.getId();
					handler.sendMessage(message);

				} else {
					bean.setType(NoDistributionBean.TYPE_NOCHECKED);
					//distributionID.remove(bean.getId());
					message.obj=bean.getId();
					message.what=1;
					handler.sendMessage(message);
				}
/*				for (Integer key : distributionID.keySet()) {
					LogJY.d("ffff", distributionID.get(key)+" ");
				}*/
			}
		});
		if (bean.getType() == NoDistributionBean.TYPE_CHECKED) {
			holder.checkBox_salesclue.setChecked(true);
		} else {
			holder.checkBox_salesclue.setChecked(false);
		}
		return convertView;
	}

	class ViewHolder {
		CheckBox checkBox_salesclue;
		TextView tv_sales_name, tv_sales_age, tv_phone_number,tv_salesclue_phone;
		ImageView iv_sales_sex, iv_sales_phone;

	}


}
