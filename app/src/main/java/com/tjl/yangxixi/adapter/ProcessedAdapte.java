package com.tjl.yangxixi.adapter;

import java.util.List;

import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.ProcessedBean;
import com.tjl.yangxixi.utils.LogJY;

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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProcessedAdapte extends BaseAdapter {
	private Context context;
	private List<ProcessedBean> list;
	private Handler handler;
	private int status;

	public ProcessedAdapte(Context context, List<ProcessedBean> list,Handler phandler,int status) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.status=status;
		if(phandler!=null){
			this.handler=phandler;
		}
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
					R.layout.adapter_processed, null);
			holder.checkBox_processed = (CheckBox) convertView.findViewById(R.id.checkBox_processed);
			holder.tv_processed_name=(TextView) convertView.findViewById(R.id.tv_processed_name);
			holder.tv_processed_time=(TextView) convertView.findViewById(R.id.tv_processed_time);
			holder.tv_processed_icon=(TextView) convertView.findViewById(R.id.tv_processed_icon);
			holder.tv_processed_id=(TextView) convertView.findViewById(R.id.tv_processed_id);
			holder.lay_qd=(LinearLayout) convertView.findViewById(R.id.lay_qd);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final ProcessedBean bean = list.get(position);
		holder.tv_processed_name.setText(list.get(position).getEmp_name());
		holder.tv_processed_time.setText(list.get(position).getCreate_time());
		holder.tv_processed_id.setText(list.get(position).getId()+"");
		if(status==1){
			if(MyApplication.getInstance().getUserList().get(0).getPosition().equals("JL")){
				holder.checkBox_processed.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						Message message = Message.obtain();
						if (isChecked) {
							bean.setType(ProcessedBean.TYPE_CHECKED);
							message.what=2;
							message.obj=bean.getId();
							handler.sendMessage(message);
							
						} else {
							bean.setType(ProcessedBean.TYPE_NOCHECKED);
							//distributionID.remove(bean.getId());
							message.obj=bean.getId();
							message.what=1;
							handler.sendMessage(message);
						}
		
					}
				});
				if (bean.getType() == ProcessedBean.TYPE_CHECKED) {
					holder.checkBox_processed.setChecked(true);
				} else {
					holder.checkBox_processed.setChecked(false);
				}
			}else{
				holder.checkBox_processed.setVisibility(View.GONE);
			}
		}else if(status==2){
			holder.checkBox_processed.setVisibility(View.GONE);
			holder.tv_processed_icon.setVisibility(View.VISIBLE);
			holder.tv_processed_icon.setBackgroundResource(R.drawable.icon_cuo);
		}else{
			holder.checkBox_processed.setVisibility(View.GONE);
			holder.tv_processed_icon.setVisibility(View.VISIBLE);
			if(bean.getIsTrack()>0){
				holder.lay_qd.setVisibility(View.VISIBLE);
			}else{
				holder.lay_qd.setVisibility(View.INVISIBLE);
			}
			holder.tv_processed_icon.setBackgroundResource(R.drawable.icon_dui);
		}
		return convertView;
	}

	class ViewHolder {
		CheckBox checkBox_processed;
		TextView tv_processed_name,tv_processed_time,tv_processed_icon,tv_processed_id;
		LinearLayout lay_qd;
	}


}
