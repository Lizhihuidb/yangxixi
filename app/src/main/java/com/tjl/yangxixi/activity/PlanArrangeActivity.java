package com.tjl.yangxixi.activity;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.fastjson.JSON;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.adapter.CalendarAdapter;
import com.tjl.yangxixi.adapter.PlanArrangeAdapter;
import com.tjl.yangxixi.bean.ConsultantInfo;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.StoreEmployeeInfoBean;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.ScreenUtil;
import com.tjl.yangxixi.utils.TimeUtil;

/**
 * 计划安排
 *
 * @author zzw
 *
 */
public class PlanArrangeActivity extends BaseActivity implements
		OnClickListener {

	private static PlanArrangeActivity activity;
	private ListView lv_plan;

	private List<String> timeList = new ArrayList<String>();

	private PlanArrangeAdapter adapter;
	private List<ConsultantInfo> consultantList;

	private TextView tv_date;
	//	private PopCalendarView popView;
	private PopupWindow popupW;

	/********* 日历 ***********/

	private GestureDetector gestureDetector = null;
	private CalendarAdapter calV = null;
	private ViewFlipper flipper = null;
	private GridView gridView = null;
	private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
	private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private String currentDate = "";
	/** 每次添加gridview到viewflipper中时给的标记 */
	private int gvFlag = 0;
	/** 当前的年月，现在日历顶端 */
	private TextView currentMonth;
	/** 上个月 */
	private ImageView prevMonth;
	/** 下个月 */
	private ImageView nextMonth;
	private HttpManager http;
	private UserInfoBean user;


	public static PlanArrangeActivity getActivity(){
		return activity;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_plan_query);
		activity = this;
		initView();
		initContent();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}
	private void initView() {
		setTitleView(true, "计划查询");
		http=new HttpManager(this);
		user=MyApplication.getInstance().getUserList().get(0);
		lv_plan = (ListView) findViewById(R.id.lv_task);
		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (popupW != null) {
					if (popupW.isShowing()) {
						popupW.dismiss();
					} else {
						popupW.showAsDropDown(tv_date);
					}
				}
			}
		});
	}

	private void initContent() {
		http.doPost(ConstantsUrl.getTaskBookDateByUser(user.getEmp_id()), 1, false);
		http.setHttpCallBackListener(listener);
		http(TimeUtil.getCurrentTime());

		consultantList = new ArrayList<ConsultantInfo>();
		adapter = new PlanArrangeAdapter(consultantList, this);
		lv_plan.setAdapter(adapter);

		lv_plan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				Intent intent = new Intent(PlanArrangeActivity.this,
						DetailsClueActivity.class);
				Bundle bundle = new Bundle();
				//LogJY.d("ffff", missionList.size()+""+position);
				bundle.putString("cludeId",consultantList.get(arg2).getId().toString());
				bundle.putInt("userPosition", 1);
				intent.putExtra("phone", consultantList.get(arg2).getCus_phone());
				intent.putExtra("data", bundle);
				startActivity(intent);
			}
		});

	}

	private void initPop() {

		//	popView = new PopCalendarView(R.layout.calendar, timeList, this);
		currentDate = TimeUtil.getCurrentTime(); // 当期日期
		year_c = Integer.parseInt(currentDate.split("-")[0]);
		month_c = Integer.parseInt(currentDate.split("-")[1]);
		day_c = Integer.parseInt(currentDate.split("-")[2]);

		initPopupWindow();

	}

	private void initPopupWindow() {
		View view = LayoutInflater.from(this).inflate(R.layout.calendar, null);

		currentMonth = (TextView) view.findViewById(R.id.currentMonth);
		prevMonth = (ImageView) view.findViewById(R.id.prevMonth);
		nextMonth = (ImageView) view.findViewById(R.id.nextMonth);
		setListener();

		gestureDetector = new GestureDetector(this, new MyGestureListener());
		flipper = (ViewFlipper) view.findViewById(R.id.flipper);
		flipper.removeAllViews();
		calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear,
				year_c, month_c, day_c, timeList);
		addGridView();
		gridView.setAdapter(calV);
		flipper.addView(gridView, 0);
		addTextToTopTextView(currentMonth);
		popupW = new PopupWindow(view);
		popupW.setWidth(LayoutParams.MATCH_PARENT);
		popupW.setHeight(LayoutParams.WRAP_CONTENT);
		popupW.setOutsideTouchable(true);
	}

	private class MyGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
							   float velocityY) {
			int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
			if(e1!=null&&e2!=null){
				if (e1.getX() - e2.getX() > 120) {
					// 像左滑动
					enterNextMonth(gvFlag);
					return true;
				} else if (e1.getX() - e2.getX() < -120) {
					// 向右滑动
					enterPrevMonth(gvFlag);
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 移动到下一个月
	 *
	 * @param gvFlag
	 */
	private void enterNextMonth(int gvFlag) {
		addGridView(); // 添加一个gridView
		jumpMonth++; // 下一个月

		calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear,
				year_c, month_c, day_c, timeList);
		gridView.setAdapter(calV);
		addTextToTopTextView(currentMonth); // 移动到下一月后，将当月显示在头标题中
		gvFlag++;
		flipper.addView(gridView, gvFlag);
		flipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_out));
		flipper.showNext();
		flipper.removeViewAt(0);
	}

	/**
	 * 移动到上一个月
	 *
	 * @param gvFlag
	 */
	private void enterPrevMonth(int gvFlag) {
		addGridView(); // 添加一个gridView
		jumpMonth--; // 上一个月

		calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear,
				year_c, month_c, day_c, timeList);
		gridView.setAdapter(calV);
		gvFlag++;
		addTextToTopTextView(currentMonth); // 移动到上一月后，将当月显示在头标题中
		flipper.addView(gridView, gvFlag);

		flipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_right_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_right_out));
		flipper.showPrevious();
		flipper.removeViewAt(0);
	}

	/**
	 * 添加头部的年份 闰哪月等信息
	 *
	 * @param view
	 */
	public void addTextToTopTextView(TextView view) {
		StringBuffer textDate = new StringBuffer();
		// draw = getResources().getDrawable(R.drawable.top_day);
		// view.setBackgroundDrawable(draw);
		textDate.append(calV.getShowYear()).append("年")
				.append(calV.getShowMonth()).append("月").append("\t");
		view.setText(textDate);
	}

	private void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		// 取得屏幕的宽度和高度

		int Width = ScreenUtil.getScreenWidth(this);
		int Height = ScreenUtil.getScreenHeight(this);

		gridView = new GridView(this);
		gridView.setNumColumns(7);
		gridView.setColumnWidth(40);
		// gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		if (Width == 720 && Height == 1280) {
			gridView.setColumnWidth(40);
		}
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// 去除gridView边框
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setFocusable(true);
		gridView.setFocusableInTouchMode(true);
		gridView.setOnTouchListener(new OnTouchListener() {
			// 将gridview中的触摸事件回传给gestureDetector

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return gestureDetector.onTouchEvent(event);
			}
		});

//		gridView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1,
//					int position, long arg3) {
//				// TODO Auto-generated method stub
//				// 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
//				int startPosition = calV.getStartPositon();
//				int endPosition = calV.getEndPosition();
//				if (startPosition <= position + 7
//						&& position <= endPosition - 7) {
//					String scheduleDay = calV.getDateByClickItem(position)
//							.split("\\.")[0]; // 这一天的阳历
//					// String scheduleLunarDay =
//					// calV.getDateByClickItem(position).split("\\.")[1];
//					// //这一天的阴历
//					String scheduleYear = calV.getShowYear();
//					String scheduleMonth = calV.getShowMonth();
//					String date = scheduleYear + "-" + scheduleMonth + "-"
//							+ scheduleDay;
//					Toast.makeText(PlanArrangeActivity.this, "点击了该条目" + date,
//							Toast.LENGTH_SHORT).show();
//					http(date);
//				}
//			}
//		});
		gridView.setLayoutParams(params);
	}

	private void setListener() {
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
	}

	public void http(String date) {
		http.showProgressDialog();
		if(popupW!=null){
			popupW.dismiss();
		}
		http.doPost(ConstantsUrl.getTaskBookListByDate(user.getEmp_id(), date), 2, false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.nextMonth: // 下一个月
				enterNextMonth(gvFlag);
				break;
			case R.id.prevMonth: // 上一个月
				enterPrevMonth(gvFlag);
				break;
		}
	}

	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				case 1:
					try {
						timeList.clear();
						List<ConsultantInfo> ciList=JSON.parseArray(result.getData(),ConsultantInfo.class);
						for (int i = 0; i < ciList.size(); i++) {
							timeList.add(ciList.get(i).getBookTime());
						}
						initPop();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						showToast(result.getMsg());
					}
					break;
				case 2:
					try {
						consultantList.clear();
						consultantList.addAll(JSON.parseArray(result.getData(), ConsultantInfo.class));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						showToast(result.getMsg());
					}
					adapter.notifyDataSetChanged();
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			http.closeProgressDialog();
			switch (tag) {
				case 1:

					break;

			}

		}
	};

}
