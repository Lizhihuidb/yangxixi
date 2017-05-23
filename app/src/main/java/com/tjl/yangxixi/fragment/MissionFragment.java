package com.tjl.yangxixi.fragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.DetailsClueActivity;
import com.tjl.yangxixi.activity.FollowUpActiivity;
import com.tjl.yangxixi.activity.LibraryMissionActivity;
import com.tjl.yangxixi.activity.MissionGwActivity;
import com.tjl.yangxixi.activity.PlanArrangeActivity;
import com.tjl.yangxixi.activity.TaskDistActivity;
import com.tjl.yangxixi.adapter.DistributionAdapte;
import com.tjl.yangxixi.adapter.NoDistributionAdapte;
import com.tjl.yangxixi.bean.DistributionBean;
import com.tjl.yangxixi.bean.DistributionScreeningBean;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.NoDistributionBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.manager.SharePreferenceManager;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.ToolsUtils;

/**
 *
 * @author Administrator
 *		老版-销售经理的订单列表
 */
public class MissionFragment extends OriginalFragment implements OnClickListener,OnRefreshListener2<GridView>{
	private UserInfoBean user;
	private GridView gv_clue_screening;
	private List<DistributionScreeningBean> screeningList;
	private HttpManager http;
	private int[] icon;
	private DistributionAdapte distributionAdapte;
	private ImageView iv_mission_menu,iv_renwu_menu,icon_drop_hkrw;
	private View popView,view_mission_line,view_renwu_menu;
	private View fView;
	private TextView tv_title_title,tv_fresh_time;
	private LinearLayout lay_mission_top,lay_my_mission,lay_mission_plan;
	private Dialog dialog,dialog1;
	private PullToRefreshGridView gv_saleclue_jl;
	private List<NoDistributionBean> noDistributionList;
	private NoDistributionAdapte noDistributionAdapte;
	private int indexPage=1;
	private CheckBox cb_mission_checkall;
	private int changeCheckbox=1;
	private Map<String, Integer> distributionID;
	private String removeID;
	private int clueTag=1;
	private RelativeLayout rel_mission_fresh;
	private WindowManager.LayoutParams lp;
	private Display display;
	private LinearLayout lay_equal_division,lay_initiative_division,lay_library_division,lay_mission_jl;
	private RelativeLayout rel_renwu_menu,rel_mission_menu;
	private int stayTask;
	private int totalTask;
	private WebView webview_mission_jl;
	private boolean isPrepared;
	@Override
	public View createView(LayoutInflater inflater,
						   ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initData();
		fView = inflater.inflate(R.layout.fragment_mission_jl, null,true);
		initJL(fView);
		isPrepared = true;
		lazyLoad();
		return fView;
	}
	private void initData(){
		http=new HttpManager(getActivity());
		http.setHttpCallBackListener(listener);
		http.showProgressDialog();
		user=MyApplication.getInstance().getUserList().get(0);
		icon=new int[]{R.drawable.icon_max_renwu,R.drawable.icon_no_fenpei,R.drawable.icon_gjz,R.drawable.icon_no_genjin,R.drawable.icon_chengdan,R.drawable.icon_zhanbai};
		screeningList=new ArrayList<DistributionScreeningBean>();
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dialog=new Dialog(getActivity(),R.style.dialog);
		popView=inflater.inflate(R.layout.dialog_distribution,null);
		dialog.addContentView(popView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		WindowManager windowManager = getActivity().getWindowManager();
		display = windowManager.getDefaultDisplay();


		gv_clue_screening = (GridView) popView.findViewById(R.id.gv_clue_screening);
		distributionAdapte=new DistributionAdapte(getActivity(), screeningList);
		gv_clue_screening.setAdapter(distributionAdapte);
		gv_clue_screening.setOnItemClickListener(itemClickListener);

	}
	private OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			// TODO Auto-generated method stub
			switch (position) {
				case 0:
					http.showProgressDialog();
					http.doPost(ConstantsUrl.getTodayTotalTask(user.getEmp_id(), "1"), 4, false);
					break;
				case 1:
					http.showProgressDialog();
					http.doPost(ConstantsUrl.getReportInfoList(user.getEmp_id(), "1"), 2, false);
					break;
				case 2:
					openActivityFinish(FollowUpActiivity.class, "2");
					break;
				case 3:
					openActivityFinish(FollowUpActiivity.class, "1");
					break;
				case 4:
					if(ToolsUtils.isNetworkAvailable(getActivity())){
						webview_mission_jl.loadUrl("http://139.196.22.252:8081/AppHtml/ClueReport_1.html?userId="+user.getEmp_id());
						lay_mission_jl.setVisibility(View.GONE);
						webview_mission_jl.setVisibility(View.VISIBLE);
					}else{
						Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
					}
					break;
				case 5:
					if(ToolsUtils.isNetworkAvailable(getActivity())){
						webview_mission_jl.loadUrl("http://139.196.22.252:8081/AppHtml/ClueLosCauseReport_1.html?userId="+user.getEmp_id());
						lay_mission_jl.setVisibility(View.GONE);
						webview_mission_jl.setVisibility(View.VISIBLE);
					}else{
						Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
					}
					break;
			}
			dialog.dismiss();
		}
	};
	private void initJL(View v){
		//new ToolsUtils().initTitleView(false,true, getActivity().getString(R.string.txt_mission_tab), getActivity(),v);
		tv_title_title = (TextView) v.findViewById(R.id.tv_mission_title);
		tv_title_title.setText(getActivity().getString(R.string.txt_mission_tab));
		noDistributionList=new ArrayList<NoDistributionBean>();
		noDistributionAdapte=new NoDistributionAdapte(getActivity(), noDistributionList , handler);

		distributionID = new HashMap<String, Integer>();

		iv_mission_menu = (ImageView) v.findViewById(R.id.iv_mission_menu);
		iv_renwu_menu=(ImageView) v.findViewById(R.id.iv_renwu_menu);
		//frl_saleclue_allocating = (FrameLayout) v.findViewById(R.id.frl_saleclue_allocating);
		lay_mission_top=(LinearLayout) v.findViewById(R.id.lay_mission_top);
		rel_mission_fresh=(RelativeLayout) v.findViewById(R.id.rel_mission_fresh);
		view_mission_line=v.findViewById(R.id.view_mission_line);
		view_renwu_menu=v.findViewById(R.id.view_renwu_menu);
		gv_saleclue_jl=(PullToRefreshGridView) v.findViewById(R.id.gv_saleclue_jl);
		cb_mission_checkall=(CheckBox) v.findViewById(R.id.cb_mission_checkall);
		tv_fresh_time=(TextView) v.findViewById(R.id.tv_fresh_time);
		lay_equal_division=(LinearLayout) v.findViewById(R.id.lay_equal_division);
		lay_initiative_division=(LinearLayout) v.findViewById(R.id.lay_initiative_division);
		lay_library_division=(LinearLayout) v.findViewById(R.id.lay_library_division);
		rel_mission_menu=(RelativeLayout) v.findViewById(R.id.rel_mission_menu);
		rel_renwu_menu=(RelativeLayout) v.findViewById(R.id.rel_renwu_menu);
		webview_mission_jl = (WebView) v.findViewById(R.id.webview_mission_jl);
		lay_mission_jl=(LinearLayout) v.findViewById(R.id.lay_mission_jl);
		icon_drop_hkrw=(ImageView) v.findViewById(R.id.icon_drop_hkrw);


		gv_saleclue_jl.setAdapter(noDistributionAdapte);
		gv_saleclue_jl.setMode(Mode.PULL_FROM_END);
		gv_saleclue_jl.setOnRefreshListener(this);// 记得绑定一下 监听类
		//iv_mission_menu.setOnClickListener(this);
		rel_mission_fresh.setOnClickListener(this);
		//iv_renwu_menu.setOnClickListener(this);
		lay_equal_division.setOnClickListener(this);
		lay_initiative_division.setOnClickListener(this);
		lay_library_division.setOnClickListener(this);
		rel_mission_menu.setOnClickListener(this);
		rel_renwu_menu.setOnClickListener(this);

		tv_fresh_time.setText(ToolsUtils.getStringDate());
		if(SharePreferenceManager.checkExistPreference(getActivity(), "libraryNumber")==true){
			int libraryNumber=SharePreferenceManager.getIntPreference(getActivity(), "libraryNumber");
			if(libraryNumber>0){
				icon_drop_hkrw.setVisibility(View.VISIBLE);
			}
		}
		gv_saleclue_jl.setOnItemClickListener(itemClickListener2);

		cb_mission_checkall.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					distributionID.clear();
					for (int i = 0; i < noDistributionList.size(); i++) {
						noDistributionList.get(i).setType(NoDistributionBean.TYPE_CHECKED);
						distributionID.put(noDistributionList.get(i).getId()+"", noDistributionList.get(i).getId());
					}
					changeCheckbox=1;
				}else{
					if(changeCheckbox==1){
						for (int i = 0; i < noDistributionList.size(); i++) {
							noDistributionList.get(i).setType(NoDistributionBean.TYPE_NOCHECKED);
						}
						distributionID.clear();
					}else{
						distributionID.remove(removeID);
						return;
					}
				}
				noDistributionAdapte.notifyDataSetChanged();
			}
		});
		webview_mission_jl.getSettings().setJavaScriptEnabled(true);
		webview_mission_jl.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});
		webview_mission_jl.addJavascriptInterface(new WebAppInterface(), "demo");
	}
	class WebAppInterface
	{
		WebAppInterface(){
		}

		/** Show a toast from the web page */
		// 如果target 大于等于API 17，则需要加上如下注解
		@JavascriptInterface
		public void clickOnAndroid()
		{
			new Thread(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					Message msg = Message.obtain();
					msg.what=3;
					handler.sendMessage(msg);
				}
			}.start();
		}
	}
	private OnItemClickListener itemClickListener2 = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			// TODO Auto-generated method stub
			TextView tv_salesclue_phone = (TextView) view.findViewById(R.id.tv_salesclue_phone);
			Intent intent = new Intent(getActivity(),DetailsClueActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("cludeId", noDistributionList.get(position).getId()+"");
			intent.putExtra("phone", tv_salesclue_phone.getText().toString());
			bundle.putInt("userPosition", 2);
			intent.putExtra("data", bundle);
			intent.putExtra("toStatus", 1);
			startActivity(intent);
		}
	};
	public Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:
					changeCheckbox=2;
					removeID=msg.obj.toString();
					distributionID.remove(removeID);
					cb_mission_checkall.setChecked(false);
					break;

				case 2:
					distributionID.put(msg.obj.toString(), Integer.parseInt(msg.obj.toString()));
					if(distributionID.size()==noDistributionList.size()){
						cb_mission_checkall.setChecked(true);
					}
					break;
				case 3:
					lay_mission_jl.setVisibility(View.VISIBLE);
					webview_mission_jl.setVisibility(View.GONE);
					break;
			}

		};
	};
	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			gv_saleclue_jl.onRefreshComplete();
			tv_fresh_time.setText(ToolsUtils.getStringDate());
			switch (tag) {
				case 1://获取今日任务统计
					List<DistributionBean> bean = JSON.parseArray(result.getData(), DistributionBean.class);
					screeningList.clear();
					String[] str=getResources().getStringArray(R.array.distribution);
					DistributionScreeningBean dBean;
					String[] count = new String[]{bean.get(0).getTtCunt(),bean.get(0).getNdCunt(),bean.get(0).getfCunt(),bean.get(0).getNfCunt(),bean.get(0).getsCunt(),bean.get(0).getlCunt()};
					for (int i = 0; i < str.length; i++) {
						dBean = new DistributionScreeningBean(i,icon[i],str[i],count[i]);
						if(i==1&&user.getPosition().equals("GW")){
							dBean.setDescribe("今日任务");
						}
						screeningList.add(dBean);
					}
					distributionAdapte.notifyDataSetChanged();
					break;

				case 2://默认未分配线索
					try {
						distributionID.clear();
						noDistributionList.clear();
						noDistributionList.addAll(JSON.parseArray(result.getData(), NoDistributionBean.class));
						noDistributionAdapte.notifyDataSetChanged();
						indexPage=1;
						indexPage++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						clueTag=1;
						showToast(result.getMsg());
						noDistributionAdapte.notifyDataSetChanged();
					}

					break;
				case 3://上拉加载线索
					noDistributionList.addAll(JSON.parseArray(result.getData(), NoDistributionBean.class));
					noDistributionAdapte.notifyDataSetChanged();
					indexPage++;
					break;
				case 4://获取总线索
					try {
						distributionID.clear();
						noDistributionList.clear();
						noDistributionList.addAll(JSON.parseArray(result.getData(), NoDistributionBean.class));
						noDistributionAdapte.notifyDataSetChanged();
						indexPage=1;
						indexPage++;
						clueTag=2;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						clueTag=2;
						showToast(result.getMsg());
						noDistributionAdapte.notifyDataSetChanged();
					}
					break;
/*			case 5://回库任务线索
				try {
					distributionID.clear();
					noDistributionList.clear();
					noDistributionList.addAll(JSON.parseArray(result.getData(), NoDistributionBean.class));
					noDistributionAdapte.notifyDataSetChanged();
					indexPage=1;
					indexPage++;
					clueTag=3;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					clueTag=3;
					showToast(result.getMsg());
					noDistributionAdapte.notifyDataSetChanged();
				}
				break;*/
				case 6:
					stayTask=result.getStayTask();
					totalTask=result.getTotalTask();
					break;
				case 7:
					if(result.getResult()!=0){
						Toast.makeText(getActivity(), "分配成功", Toast.LENGTH_SHORT).show();
						reFresh();
					}else{
						Toast.makeText(getActivity(), "分配失败", Toast.LENGTH_SHORT).show();
					}
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			gv_saleclue_jl.onRefreshComplete();
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
			case R.id.rel_mission_menu:
				dialog.show();
				lp = dialog.getWindow().getAttributes();
				lp.width = (int)(display.getWidth()); //设置宽度
				lp.gravity = Gravity.TOP;
				lp.y=view_mission_line.getTop();
				dialog.getWindow().setAttributes(lp);
				break;
			case R.id.rel_mission_fresh:
				reFresh();
				break;
			case R.id.rel_renwu_menu:
				dialog1 = new Dialog(getActivity(),R.style.dialog);
				View viewMenu=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_mission_menu, null);
				lay_my_mission=(LinearLayout) viewMenu.findViewById(R.id.lay_my_mission);
				lay_mission_plan=(LinearLayout) viewMenu.findViewById(R.id.lay_mission_plan);
				lay_my_mission.setOnClickListener(this);
				lay_mission_plan.setOnClickListener(this);
				dialog1.addContentView(viewMenu, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				dialog1.show();
				lp = dialog1.getWindow().getAttributes();
				lp.gravity = Gravity.RIGHT|Gravity.TOP;
				lp.y=(int) (view_renwu_menu.getTop()*2.4);
				dialog1.getWindow().setAttributes(lp);

				break;
			case R.id.lay_my_mission:
				Intent intent = new Intent(getActivity(), MissionGwActivity.class);
				startActivity(intent);
				dialog1.dismiss();
				break;
			case R.id.lay_mission_plan:
				Intent intentPlan = new Intent(getActivity(), PlanArrangeActivity.class);
				startActivity(intentPlan);
				dialog1.dismiss();
				break;
			case R.id.lay_equal_division:
				if(distributionID.size()<=0){
					avgTask(stayTask, totalTask);
				}else{
					avgTask(stayTask, distributionID.size());
				}
				break;
			case R.id.lay_initiative_division:
				if(distributionID.size()<=0){
					Toast.makeText(getActivity(), "请至少选择一条线索！", Toast.LENGTH_SHORT).show();
				}else{
					String str="";
					Intent intent1 = new Intent(getActivity(), TaskDistActivity.class);
					for (Object o : distributionID.keySet()) {
						str+=distributionID.get(o)+",";
					}
					intent1.putExtra("toStatus", 1);
					intent1.putExtra("data", str);
					startActivity(intent1);
					//startActivityForResult(intent1, Activity.RESULT_FIRST_USER);
				}
				break;
			case R.id.lay_library_division:
			/*http.showProgressDialog();
			http.doPost(ConstantsUrl.getBackTaskList(user.getEmp_id(),"1"), 5, false);*/
				icon_drop_hkrw.setVisibility(View.GONE);
				openActivityFinish(LibraryMissionActivity.class);
				break;
		}

	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==Activity.RESULT_FIRST_USER){
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
		if(clueTag==1){
			http.doPost(ConstantsUrl.getReportInfoList(user.getEmp_id(), ""+indexPage), 3, false);
		}else if(clueTag==2){
			http.doPost(ConstantsUrl.getTodayTotalTask(user.getEmp_id(), ""+indexPage), 3, false);
		}
/*		else{
			http.doPost(ConstantsUrl.getBackTaskList(user.getEmp_id(),""+indexPage), 3, false);
		}*/
	}
	private void avgTask(int stayTask,final int totalTask) {
		final Dialog dialog = new Dialog(getActivity(), R.style.Translucent_NoTitle);
		View view = LayoutInflater.from(getActivity()).inflate(
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
				//showProgressDialog();
				if(totalTask==0){
					Toast.makeText(getActivity(), "分配失败，没有要分配的线索！", Toast.LENGTH_SHORT).show();
				}else{
					if(distributionID.size()<=0){
						http.doPost(ConstantsUrl.getAvgAssigStore(user.getEmp_id()),7,false);
					}else{
						String str="";
						for (Object o : distributionID.keySet()) {
							str+=distributionID.get(o)+",";
						}
						http.doPost(ConstantsUrl.getAvgBId(user.getEmp_id(),str.substring(0,str.length()-1)),7,false);
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

	//正常跳转
	protected void openActivityFinish(Class clazz){
		Intent intent = new Intent(getActivity(), clazz);
		startActivity(intent);
	}
	//正常跳转
	protected void openActivityFinish(Class clazz,String value){
		Intent intent = new Intent(getActivity(), clazz);
		intent.putExtra("data", value);
		startActivity(intent);
	}
	private void reFresh(){
		http.showProgressDialog();
		if(clueTag==1){
			http.doPost(ConstantsUrl.getReportInfoList(user.getEmp_id(), "1"), 2, false);
		}else if(clueTag==2){
			http.doPost(ConstantsUrl.getTodayTotalTask(user.getEmp_id(), "1"), 4, false);
		}
/*		else{
			http.doPost(ConstantsUrl.getBackTaskList(user.getEmp_id(),"1"), 5, false);
		}*/
		http.doPost(ConstantsUrl.getTaskEmpCunt(user.getEmp_id()), 6, false);
		http.doPost(ConstantsUrl.getSaleStatistics(user.getEmp_id()), 1,false);
	}
	protected void showToast(final String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		http.doPost(ConstantsUrl.getSaleStatistics(user.getEmp_id()), 1,false);
		http.doPost(ConstantsUrl.getReportInfoList(user.getEmp_id(), "1"), 2, false);
		http.doPost(ConstantsUrl.getTaskEmpCunt(user.getEmp_id()), 6, false);
	}
}
