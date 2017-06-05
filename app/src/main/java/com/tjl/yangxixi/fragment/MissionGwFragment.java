//package com.tjl.yangxixi.fragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.alibaba.fastjson.JSON;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
//import com.tjl.yangxixi.MyApplication;
//import com.tjl.yangxixi.OriginalFragment;
//import com.tjl.yangxixi.R;
//import com.tjl.yangxixi.activity.DetailsClueActivity;
//import com.tjl.yangxixi.activity.MissionGwActivity;
//import com.tjl.yangxixi.activity.PlanArrangeActivity;
//import com.tjl.yangxixi.activity.SingleActivity;
//import com.tjl.yangxixi.adapter.DistributionAdapte;
//import com.tjl.yangxixi.adapter.MissionAdapte;
//import com.tjl.yangxixi.bean.DistributionBean;
//import com.tjl.yangxixi.bean.DistributionScreeningBean;
//import com.tjl.yangxixi.bean.HttpMessage;
//import com.tjl.yangxixi.bean.MissionGwBean;
//import com.tjl.yangxixi.bean.UserInfoBean;
//import com.tjl.yangxixi.manager.HttpManager;
//import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
//import com.tjl.yangxixi.params.ConstantsUrl;
//import com.tjl.yangxixi.utils.LogJY;
//import com.tjl.yangxixi.utils.ToolsUtils;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup.LayoutParams;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
//
///**
// *
// * @author Administrator
// *     订单列表
// */
//public class MissionGwFragment extends OriginalFragment{
//	private GridView gv_clue_screening;
//	private List<DistributionScreeningBean> screeningList;
//	private HttpManager http;
//	private int[] icon;
//	private DistributionAdapte distributionAdapte;
//	private MissionAdapte missionAdapte;
//	private WindowManager.LayoutParams lp;
//	private Display display;
//	private Dialog dialog,dialog1;
//	private View dialogView;
//	private TextView tv_fresh_timeGw;
//	private ImageView iv_mission_menuGw,iv_renwu_menuGw;
//	private UserInfoBean user;
//	private View view_mission_lineGw,view_renwu_menuGw;
//	//	private PullToRefreshListView lv_saleclue_Gw;
//	private List<MissionGwBean> missionList;
//	private RelativeLayout rel_mission_freshGw,rel_backGw;
//	private int indexPage=1;
//	private LinearLayout lay_my_mission,lay_mission_plan,lay_missionGw;
//	private int clueTag=1;
//	//	private WebView webview_missionGw;
//	private boolean isPrepared;
//	private LinearLayout ll_mission_01,ll_mission_02;
//	public static List<Bitmap> bitmapsPhoto=new ArrayList<Bitmap>();
//	@Override
//	public View createView(LayoutInflater inflater,
//						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		View v = inflater.inflate(R.layout.fragment_mission_gw, null);
////		initView(v);
////		initData();
//		isPrepared = true;
//		lazyLoad();
//		return v;
//	}
//
//
//
////	protected void initView(View v) {
////		// TODO Auto-generated method stub
////		user=MyApplication.getInstance().getUserList().get(0);
////		icon=new int[]{R.drawable.icon_max_renwu,R.drawable.icon_no_fenpei,R.drawable.icon_gjz,R.drawable.icon_no_genjin,R.drawable.icon_chengdan,R.drawable.icon_zhanbai};
////		screeningList=new ArrayList<DistributionScreeningBean>();
////		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////		dialog=new Dialog(getActivity(),R.style.dialog);
////		dialogView=inflater.inflate(R.layout.dialog_distribution,null);
////		dialog.addContentView(dialogView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
////
////		WindowManager windowManager = getActivity().getWindowManager();
////		display = windowManager.getDefaultDisplay();
////
////		tv_fresh_timeGw = (TextView) v.findViewById(R.id.tv_fresh_timeGw);
////		iv_mission_menuGw = (ImageView) v.findViewById(R.id.iv_mission_menuGw);
////		view_mission_lineGw=v.findViewById(R.id.view_mission_lineGw);
//////		lv_saleclue_Gw=(PullToRefreshListView) v.findViewById(R.id.lv_saleclue_Gw);
////		rel_mission_freshGw=(RelativeLayout) v.findViewById(R.id.rel_mission_freshGw);
////		rel_backGw=(RelativeLayout) v.findViewById(R.id.rel_backGw);
////		view_renwu_menuGw=v.findViewById(R.id.view_renwu_menuGw);
////		iv_renwu_menuGw=(ImageView) v.findViewById(R.id.iv_renwu_menuGw);
////		lay_missionGw=(LinearLayout) v.findViewById(R.id.lay_missionGw);
//////		webview_missionGw=(WebView) v.findViewById(R.id.webview_missionGw);
////
////		ll_mission_01= (LinearLayout) v.findViewById(R.id.ll_mission_01);
////		ll_mission_01.setOnClickListener(this);
////		ll_mission_02= (LinearLayout) v.findViewById(R.id.ll_mission_02);
////		ll_mission_02.setOnClickListener(this);
////
////
////
////		rel_backGw.setVisibility(View.GONE);
////		tv_fresh_timeGw.setText(ToolsUtils.getStringDate());
////		gv_clue_screening = (GridView) dialogView.findViewById(R.id.gv_clue_screening);
////		distributionAdapte=new DistributionAdapte(getActivity(), screeningList);
////		gv_clue_screening.setAdapter(distributionAdapte);
////		gv_clue_screening.setOnItemClickListener(itemClickListener);
////
////
////		http=new HttpManager(getActivity());
////
////		http.setHttpCallBackListener(listener);
////		iv_mission_menuGw.setOnClickListener(this);
////
//////		webview_missionGw.getSettings().setJavaScriptEnabled(true);
//////		webview_missionGw.setWebViewClient(new WebViewClient() {
//////			@Override
//////			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//////				view.loadUrl(url);
//////				return false;
//////
//////			}
//////		});
//////		webview_missionGw.addJavascriptInterface(new WebAppInterface(), "demo");
////	}
////	class WebAppInterface
////	{
////		WebAppInterface(){
////		}
////
////		/** Show a toast from the web page */
////		// 如果target 大于等于API 17，则需要加上如下注解
////		@JavascriptInterface
////		public void clickOnAndroid()
////		{
////			new Thread(){
////				@Override
////				public void run() {
////					// TODO Auto-generated method stub
////					super.run();
////					Message msg = Message.obtain();
////					handler.sendMessage(msg);
////				}
////			}.start();
////		}
////	}
////	Handler handler = new Handler(){
////		public void handleMessage(android.os.Message msg) {
////			//   		webview_missionGw.setVisibility(View.GONE);
////			lay_missionGw.setVisibility(View.VISIBLE);
////		};
////	};
////	protected void initData() {
////		// TODO Auto-generated method stub
////		missionList = new ArrayList<MissionGwBean>();
////		missionAdapte=new MissionAdapte(getActivity(), missionList);
//////		lv_saleclue_Gw.setAdapter(missionAdapte);
//////		lv_saleclue_Gw.setMode(Mode.PULL_FROM_END);
//////		lv_saleclue_Gw.setOnRefreshListener(this);// 记得绑定一下 监听类
//////		lv_saleclue_Gw.setOnItemClickListener(itemClickListener2);
////
////		rel_mission_freshGw.setOnClickListener(this);
////		rel_backGw.setOnClickListener(this);
////		iv_renwu_menuGw.setOnClickListener(this);
////	}
////	private OnItemClickListener itemClickListener = new OnItemClickListener() {
////
////		@Override
////		public void onItemClick(AdapterView<?> parent, View view, int position,
////								long id) {
////			// TODO Auto-generated method stub
////			switch (position) {
////				case 0:
////					http.showProgressDialog();
////					http.doPost(ConstantsUrl.getUserTaskList(user.getEmp_id(),"1"), 2,false);
////					break;
////				case 1:
////					http.showProgressDialog();
////					http.doPost(ConstantsUrl.getTodayTask(user.getEmp_id(),"1","1"), 6,false);
////					break;
////				case 2:
////					http.showProgressDialog();
////					http.doPost(ConstantsUrl.getClueListByState(user.getEmp_id(), "2", "1"), 4,false);
////					break;
////				case 3:
////					http.showProgressDialog();
////					http.doPost(ConstantsUrl.getClueListByState(user.getEmp_id(), "1", "1"), 5,false);
////					break;
//////			case 4:
//////				if(ToolsUtils.isNetworkAvailable(getActivity())){
//////					webview_missionGw.loadUrl("http://139.196.22.252:8081/AppHtml/SaleTaskFollReport_1.html?userId="+user.getEmp_id()+"&clueState=4");
//////					lay_missionGw.setVisibility(View.GONE);
//////					webview_missionGw.setVisibility(View.VISIBLE);
//////				}else{
//////					Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
//////				}
/////*				Intent intent = new Intent(getActivity(),SingleActivity.class);
////				intent.putExtra("toStatus", 1);
////				startActivity(intent);*/
//////				break;
//////			case 5:
//////				if(ToolsUtils.isNetworkAvailable(getActivity())){
//////					webview_missionGw.loadUrl("http://139.196.22.252:8081/AppHtml/UserTaskLosList_1.html?UserCode="+user.getEmp_id()+"&clueState=3");
//////					lay_missionGw.setVisibility(View.GONE);
//////					webview_missionGw.setVisibility(View.VISIBLE);
//////				}else{
//////					Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
//////				}
/////*				Intent intent2 = new Intent(getActivity(),SingleActivity.class);
////				intent2.putExtra("toStatus", 2);
////				startActivity(intent2);*/
//////				break;
////
////			}
////			dialog.dismiss();
////		}
////	};
////	private OnItemClickListener itemClickListener2=new OnItemClickListener() {
////
////		@Override
////		public void onItemClick(AdapterView<?> parent, View view, int position,
////								long id) {
////			TextView tv_mymission_phone = (TextView) view.findViewById(R.id.tv_mymission_phone);
////			TextView tv_mission_id = (TextView) view.findViewById(R.id.tv_mission_id);
////			Intent intent = new Intent(getActivity(),DetailsClueActivity.class);
////			Bundle bundle = new Bundle();
////			bundle.putString("cludeId", tv_mission_id.getText().toString());
////			bundle.putInt("userPosition", 1);
////			intent.putExtra("phone", tv_mymission_phone.getText());
////			intent.putExtra("data", bundle);
////			startActivity(intent);
////		}
////	};
////	HttpCallBackListener listener = new HttpCallBackListener() {
////
////		@Override
////		public void onSuccess(HttpMessage result, int tag, Object obj) {
////			// TODO Auto-generated method stub
////			tv_fresh_timeGw.setText(ToolsUtils.getStringDate());
////			http.closeProgressDialog();
//////			lv_saleclue_Gw.onRefreshComplete();
////			switch (tag) {
////				case 1:
////					List<DistributionBean> bean = JSON.parseArray(result.getData(), DistributionBean.class);
////					screeningList.clear();
////					String[] str=getResources().getStringArray(R.array.distribution);
////					DistributionScreeningBean dBean;
////					String[] count = new String[]{bean.get(0).getTtCunt(),bean.get(0).getTdCunt(),bean.get(0).getfCunt(),bean.get(0).getNfCunt(),bean.get(0).getsCunt(),bean.get(0).getlCunt()};
////					for (int i = 0; i < str.length; i++) {
////						dBean = new DistributionScreeningBean(i,icon[i],str[i],count[i]);
////						if(i==1){
////							dBean.setDescribe("今日任务");
////						}
////						screeningList.add(dBean);
////					}
////					distributionAdapte.notifyDataSetChanged();
////					break;
////
////				case 2:
////					try {
////						missionList.clear();
////						missionList.addAll(JSON.parseArray(result.getData(), MissionGwBean.class));
////						missionAdapte.notifyDataSetChanged();
////						indexPage=1;
////						clueTag=1;
////						indexPage++;
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						missionAdapte.notifyDataSetChanged();
////						clueTag=1;
////						showToast(result.getMsg());
////						e.printStackTrace();
////					}
////					break;
////				case 3:
////					missionList.addAll(JSON.parseArray(result.getData(), MissionGwBean.class));
////					missionAdapte.notifyDataSetChanged();
////					indexPage++;
////					break;
////				case 4:
////					try {
////						missionList.clear();
////						missionList.addAll(JSON.parseArray(result.getData(), MissionGwBean.class));
////						missionAdapte.notifyDataSetChanged();
////						indexPage=1;
////						clueTag=2;
////						indexPage++;
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						missionAdapte.notifyDataSetChanged();
////						clueTag=2;
////						showToast(result.getMsg());
////						e.printStackTrace();
////					}
////					break;
////				case 5:
////					try {
////						missionList.clear();
////						missionList.addAll(JSON.parseArray(result.getData(), MissionGwBean.class));
////						missionAdapte.notifyDataSetChanged();
////						indexPage=1;
////						clueTag=3;
////						indexPage++;
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						missionAdapte.notifyDataSetChanged();
////						clueTag=3;
////						showToast(result.getMsg());
////						e.printStackTrace();
////					}
////					break;
////				case 6:
////					try {
////						missionList.clear();
////						missionList.addAll(JSON.parseArray(result.getData(), MissionGwBean.class));
////						missionAdapte.notifyDataSetChanged();
////						indexPage=1;
////						clueTag=4;
////						indexPage++;
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						missionAdapte.notifyDataSetChanged();
////						clueTag=4;
////						showToast(result.getMsg());
////						e.printStackTrace();
////					}
////					break;
////			}
////		}
////
////		@Override
////		public void onError(String msg, int tag, Object obj) {
////			// TODO Auto-generated method stub
////			http.closeProgressDialog();
//////			lv_saleclue_Gw.onRefreshComplete();
////			switch (tag) {
////				case 1:
////
////					break;
////				case 2:
////					break;
////				case 3:
////					break;
////
////			}
////
////		}
////	};
////	@Override
////	public void onClick(View v) {
////		// TODO Auto-generated method stub
////		switch (v.getId()) {
////			case R.id.iv_mission_menuGw:
////				dialog.show();
////				lp = dialog.getWindow().getAttributes();
////				lp.width = (int)(display.getWidth()); //设置宽度
////				lp.gravity = Gravity.TOP;
////				lp.y=view_mission_lineGw.getTop();
////				dialog.getWindow().setAttributes(lp);
////				break;
////			case R.id.rel_mission_freshGw:
////				http.showProgressDialog();
////				if(clueTag==1){
////					http.doPost(ConstantsUrl.getUserTaskList(user.getEmp_id(),"1"), 2,false);
////				}else if(clueTag==2){
////					http.doPost(ConstantsUrl.getClueListByState(user.getEmp_id(), "2", "1"), 4,false);
////				}else if(clueTag==4){
////					http.doPost(ConstantsUrl.getTodayTask(user.getEmp_id(),"1","1"), 6,false);
////				}else{
////					http.doPost(ConstantsUrl.getClueListByState(user.getEmp_id(), "1", "1"), 5,false);
////				}
////				http.doPost(ConstantsUrl.getMySaleStatistics(user.getEmp_id()), 1,false);
////				break;
////			case R.id.iv_renwu_menuGw:
////				dialog1 = new Dialog(getActivity(),R.style.dialog);
////				View viewMenu=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_mission_menu, null);
////				lay_my_mission=(LinearLayout) viewMenu.findViewById(R.id.lay_my_mission);
////				lay_mission_plan=(LinearLayout) viewMenu.findViewById(R.id.lay_mission_plan);
////				lay_my_mission.setVisibility(View.GONE);
////				lay_my_mission.setOnClickListener(this);
////				lay_mission_plan.setOnClickListener(this);
////				dialog1.addContentView(viewMenu, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
////				dialog1.show();
////				lp = dialog1.getWindow().getAttributes();
////				lp.gravity = Gravity.RIGHT|Gravity.TOP;
////				lp.y=(int) (view_renwu_menuGw.getTop()*2.4);
////				dialog1.getWindow().setAttributes(lp);
////
////				break;
////			case R.id.lay_mission_plan:
////				Intent intent = new Intent(getActivity(), PlanArrangeActivity.class);
////				startActivity(intent);
////				dialog1.dismiss();
////				break;
////
////			case R.id.ll_mission_01:
////				Intent ll_mission_01=new Intent(getActivity(),DetailsClueActivity.class);
////				startActivity(ll_mission_01);
////				break;
////
////			case R.id.ll_mission_02:
////				Intent ll_mission_02=new Intent(getActivity(),DetailsClueActivity.class);
////				startActivity(ll_mission_02);
////				break;
////		}
////	}
////
////	@Override
////	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
////		// TODO Auto-generated method stub
////		http.showProgressDialog();
////		if(clueTag==1){
////			http.doPost(ConstantsUrl.getUserTaskList(user.getEmp_id(),indexPage+""), 3,false);
////		}else if(clueTag==2){
////			http.doPost(ConstantsUrl.getClueListByState(user.getEmp_id(), "2", indexPage+""), 3,false);
////		}else if(clueTag==4){
////			http.doPost(ConstantsUrl.getTodayTask(user.getEmp_id(),indexPage+"","1"), 3,false);
////		}else{
////			http.doPost(ConstantsUrl.getClueListByState(user.getEmp_id(), "1", indexPage+""), 3,false);
////		}
////	}
////	protected void showToast(final String text) {
////		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
////	}
////	@Override
////	protected void lazyLoad() {
////		// TODO Auto-generated method stub
////		http.showProgressDialog();
////		http.doPost(ConstantsUrl.getMySaleStatistics(user.getEmp_id()), 1,false);
////		http.doPost(ConstantsUrl.getUserTaskList(user.getEmp_id(),"1"), 2,false);
////	}
//}
