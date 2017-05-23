package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.adapter.NoDistributionAdapte;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.NoDistributionBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;

public class LibraryMissionActivity extends BaseActivity implements
		OnClickListener, OnRefreshListener2<GridView> {
	private HttpManager http;
	private UserInfoBean user;
	private PullToRefreshGridView gv_libreryclue_jl;
	private List<NoDistributionBean> noDistributionList;
	private NoDistributionAdapte noDistributionAdapte;
	private int indexPage = 1;
	private CheckBox cb_mission_checkall;
	private int changeCheckbox = 1;
	private Map<String, Integer> distributionID;
	private String removeID;
	private LinearLayout lay_equal_divisionhk, lay_initiative_divisionhk;
	private RelativeLayout rel_mission_freshhk, rel_backhk;
	private int stayTask;
	private int totalTask;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_library_mission);
		distributionID = new HashMap<String, Integer>();
		cb_mission_checkall = (CheckBox) findViewById(R.id.cb_mission_checkallhk);
		lay_equal_divisionhk = (LinearLayout) findViewById(R.id.lay_equal_divisionhk);
		lay_initiative_divisionhk = (LinearLayout) findViewById(R.id.lay_initiative_divisionhk);
		gv_libreryclue_jl = (PullToRefreshGridView) findViewById(R.id.gv_libreryclue_jl);
		rel_mission_freshhk = (RelativeLayout) findViewById(R.id.rel_mission_freshhk);
		rel_backhk = (RelativeLayout) findViewById(R.id.rel_backhk);
	}

	@Override
	protected void initData() {
		user = MyApplication.getInstance().getUserList().get(0);
		http = new HttpManager(this);
		http.setHttpCallBackListener(listener);

		reFresh();

		noDistributionList = new ArrayList<NoDistributionBean>();
		noDistributionAdapte = new NoDistributionAdapte(this,
				noDistributionList, handler);
		gv_libreryclue_jl.setAdapter(noDistributionAdapte);
		gv_libreryclue_jl.setMode(Mode.PULL_FROM_END);
		gv_libreryclue_jl.setOnRefreshListener(this);// 记得绑定一下 监听类

		gv_libreryclue_jl.setOnItemClickListener(itemClickListener2);

		cb_mission_checkall
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
												 boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							distributionID.clear();
							for (int i = 0; i < noDistributionList.size(); i++) {
								noDistributionList.get(i).setType(
										NoDistributionBean.TYPE_CHECKED);
								distributionID.put(noDistributionList.get(i)
										.getId() + "", noDistributionList
										.get(i).getId());
							}
							changeCheckbox = 1;
						} else {
							if (changeCheckbox == 1) {
								for (int i = 0; i < noDistributionList.size(); i++) {
									noDistributionList.get(i).setType(
											NoDistributionBean.TYPE_NOCHECKED);
								}
								distributionID.clear();
							} else {
								distributionID.remove(removeID);
								return;
							}
						}
						noDistributionAdapte.notifyDataSetChanged();
					}
				});
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		rel_mission_freshhk.setOnClickListener(this);
		rel_backhk.setOnClickListener(this);
		lay_equal_divisionhk.setOnClickListener(this);
		lay_initiative_divisionhk.setOnClickListener(this);
	}

	private OnItemClickListener itemClickListener2 = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			// TODO Auto-generated method stub
			TextView tv_salesclue_phone = (TextView) view
					.findViewById(R.id.tv_salesclue_phone);
			Intent intent = new Intent(LibraryMissionActivity.this,
					DetailsClueActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("cludeId", noDistributionList.get(position)
					.getId() + "");
			intent.putExtra("phone", tv_salesclue_phone.getText().toString());
			bundle.putInt("userPosition", 2);
			intent.putExtra("data", bundle);
			intent.putExtra("toStatus", 2);
			startActivityForResult(intent, Activity.RESULT_FIRST_USER);
		}
	};

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:
					changeCheckbox = 2;
					removeID = msg.obj.toString();
					distributionID.remove(removeID);
					cb_mission_checkall.setChecked(false);
					break;

				case 2:
					distributionID.put(msg.obj.toString(),
							Integer.parseInt(msg.obj.toString()));
					if (distributionID.size() == noDistributionList.size()) {
						cb_mission_checkall.setChecked(true);
					}
					break;
			}

		};
	};
	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			gv_libreryclue_jl.onRefreshComplete();
			switch (tag) {
				case 1:// 回库任务线索
					try {
						distributionID.clear();
						noDistributionList.clear();
						noDistributionList.addAll(JSON.parseArray(result.getData(),
								NoDistributionBean.class));
						noDistributionAdapte.notifyDataSetChanged();
						indexPage = 1;
						indexPage++;
					} catch (Exception e) {
						e.printStackTrace();
						showToast(result.getMsg());
						noDistributionAdapte.notifyDataSetChanged();
					}
					break;
				case 2:// 上拉加载线索
					noDistributionList.addAll(JSON.parseArray(result.getData(),
							NoDistributionBean.class));
					noDistributionAdapte.notifyDataSetChanged();
					indexPage++;
					break;
				case 3:
					stayTask = result.getStayTask();
					totalTask = result.getTotalTask();
					break;
				case 4:
					if (result.getResult() != 0) {
						Toast.makeText(LibraryMissionActivity.this, "分配成功",
								Toast.LENGTH_SHORT).show();
						reFresh();
					} else {
						Toast.makeText(LibraryMissionActivity.this, "分配失败",
								Toast.LENGTH_SHORT).show();
					}
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			gv_libreryclue_jl.onRefreshComplete();
			switch (tag) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
			}

		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.rel_backhk:
				finish();
				break;
			case R.id.rel_mission_freshhk:
				reFresh();
				break;
			case R.id.lay_equal_divisionhk:
				if (distributionID.size() <= 0) {
					avgTask(stayTask, totalTask);
				} else {
					avgTask(stayTask, distributionID.size());
				}
				break;
			case R.id.lay_initiative_divisionhk:
				if (distributionID.size() <= 0) {
					Toast.makeText(LibraryMissionActivity.this, "请至少选择一条线索！",
							Toast.LENGTH_SHORT).show();
				} else {
					String str = "";
					Intent intent1 = new Intent(LibraryMissionActivity.this,
							TaskDistActivity.class);
					for (Object o : distributionID.keySet()) {
						str += distributionID.get(o) + ",";
					}
					intent1.putExtra("toStatus", 2);
					intent1.putExtra("data", str);
					startActivityForResult(intent1, Activity.RESULT_FIRST_USER);
				}
				break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Activity.RESULT_FIRST_USER) {
			reFresh();
		}

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		// TODO Auto-generated method stub
		http.showProgressDialog();
		http.doPost(
				ConstantsUrl.getBackTaskList(user.getEmp_id(), "" + indexPage),
				2, false);
	}

	private void avgTask(int stayTask, final int totalTask) {
		final Dialog dialog = new Dialog(LibraryMissionActivity.this,
				R.style.Translucent_NoTitle);
		View view = LayoutInflater.from(LibraryMissionActivity.this).inflate(
				R.layout.dialog_manager_avgtask, null);
		TextView tv_thisNum = (TextView) view.findViewById(R.id.tv_thisNum);
		TextView tv_xsNum = (TextView) view.findViewById(R.id.tv_xsNum);
		TextView tv_avgNum = (TextView) view.findViewById(R.id.tv_avgNum);
		TextView tv_syNum = (TextView) view.findViewById(R.id.tv_syNum);
		TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
		TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);
		tv_thisNum.setText("当前销售线索" + totalTask);
		tv_xsNum.setText("销售" + stayTask + "人");
		if ((totalTask % stayTask) == 0) {
			tv_avgNum.setText("平均" + totalTask / stayTask + "/人");
			tv_syNum.setVisibility(View.GONE);
		} else {
			tv_avgNum.setText("平均" + totalTask / stayTask + "/人");
			tv_syNum.setText("还剩" + totalTask % stayTask + "条随机分配");
			tv_syNum.setVisibility(View.VISIBLE);
		}
		dialog.setCancelable(false);
		dialog.setContentView(view);
		tv_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showProgressDialog();
				if (totalTask == 0) {
					Toast.makeText(LibraryMissionActivity.this,
							"分配失败，没有要分配的线索！", Toast.LENGTH_SHORT).show();
				} else {
					if (distributionID.size() <= 0) {
						http.doPost(ConstantsUrl.getAvgBackAssigStore(user
								.getEmp_id()), 4, false);
					} else {
						String str = "";
						for (Object o : distributionID.keySet()) {
							str += distributionID.get(o) + ",";
						}
						http.doPost(
								ConstantsUrl.getAvgBId(user.getEmp_id(),
										str.substring(0, str.length() - 1)), 4,
								false);
					}
				}
				dialog.dismiss();
			}
		});
		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	// 正常跳转
	protected void openActivityFinish(Class clazz) {
		Intent intent = new Intent(LibraryMissionActivity.this, clazz);
		startActivity(intent);
	}

	// 正常跳转
	protected void openActivityFinish(Class clazz, String value) {
		Intent intent = new Intent(LibraryMissionActivity.this, clazz);
		intent.putExtra("data", value);
		startActivity(intent);
	}

	private void reFresh() {
		http.showProgressDialog();
		http.doPost(ConstantsUrl.getBackTaskEmpCunt(user.getEmp_id()), 3, false);
		http.doPost(ConstantsUrl.getBackTaskList(user.getEmp_id(), "1"), 1,
				false);
	}

	protected void showToast(final String text) {
		Toast.makeText(LibraryMissionActivity.this, text, Toast.LENGTH_SHORT)
				.show();
	}

}
