package com.tjl.yangxixi.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.manager.HttpManager.HttpCallBackListener;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.receiver.PhoneReceiver;
import com.tjl.yangxixi.utils.PxDpUtils;
import com.tjl.yangxixi.utils.TimeUtil;
import com.tjl.yangxixi.utils.popw;
import com.tjl.yangxixi.wheelview.NumericWheelAdapter;
import com.tjl.yangxixi.wheelview.OnWheelChangedListener;
import com.tjl.yangxixi.wheelview.WheelView;

/**
 *
 * @author Administrator
 *    销售经理的订单详情列表
 */
@SuppressLint("SetJavaScriptEnabled")
public class DetailsClueActivity extends BaseActivity implements
		OnClickListener {

	// 自定义的弹出框类
	popw menuWindow;

	//	private WebView webView;
	private String cludeId;
	private int userPosition;
	private UserInfoBean user;
	private TextView tv_fenpei;
	private TextView tv_cheng, tv_bai, tv_fan;
	private RelativeLayout rel_details_back, rel_derails_fresh;
	private HttpManager http;
	private static DetailsClueActivity activity;
	private String phone;
	private PhoneReceiver phoneReceiver;
	private Dialog dialog, dialogError;
	private static int START_YEAR = 1990, END_YEAR = 2100;
	public static final int PHONE_SEND = 100;
	private List<String> telTimes = new ArrayList<String>();
	private String radioStr = "";
	private LinearLayout layout_details;
	private int toStatus;

	private TextView mMoreinformation,mPackup,mViewserviceaddress,mQiandao;
	private TextView mViewdetailsordernumber,mViewdetailsorderdate;
	private LinearLayout mDetailsemail,mDetailsordernumber,mDetailsorderdate;
	private ImageView mServicereport,mExaminingreport;

	GridView mGvservicereport;

	/* 网页视图属性 */private WebSettings mWebSettings;

	public static DetailsClueActivity getDetailsActivity() {
		return activity;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_manager_details);
		activity = this;
		user = MyApplication.getInstance().getUserList().get(0);
//		cludeId = getIntent().getBundleExtra("data").getString("cludeId");
//		phone = getIntent().getStringExtra("phone");
//		userPosition = getIntent().getBundleExtra("data")
//				.getInt("userPosition");
//		try {
//			toStatus = getIntent().getIntExtra("toStatus", 0);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		webView = (WebView) findViewById(R.id.webView_details);

//		/** webView屏幕适配 */
//		WindowManager wm = (WindowManager) DetailsClueActivity.this
//				.getSystemService(Context.WINDOW_SERVICE);
//		int width = wm.getDefaultDisplay().getWidth();
//
//		if (width < 650 && width > 500) {
//			webView.setInitialScale(70);
//		}
//
//		mWebSettings = webView.getSettings();
//		/** 支持JS */
//		mWebSettings.setJavaScriptEnabled(true);
//		/** 支持内容重新布局 */
//		mWebSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
//		/** 将图片调整到适应WebView大小 */
//		mWebSettings.setUseWideViewPort(true);
//		/** 支持自动加载图片 */
//		mWebSettings.setLoadsImagesAutomatically(true);
//		/** 优先使用缓存 */
//		mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//		/** 支持缩放 */
//		mWebSettings.setSupportZoom(true);
//		/** 支持手势缩放/显示按钮 */
//		mWebSettings.setBuiltInZoomControls(false);
//		/** 缩放至屏幕大小 */
//		mWebSettings.setLoadWithOverviewMode(true);
//
//		tv_fenpei = (TextView) findViewById(R.id.tv_fenpei);
		tv_cheng = (TextView) findViewById(R.id.tv_cheng);
//		tv_bai = (TextView) findViewById(R.id.tv_bai);
		tv_fan = (TextView) findViewById(R.id.tv_fan);
		layout_details = (LinearLayout) findViewById(R.id.layout_details);
		rel_details_back = (RelativeLayout) findViewById(R.id.rel_details_back);
		rel_derails_fresh = (RelativeLayout) findViewById(R.id.rel_derails_fresh);
//		webView.getSettings().setJavaScriptEnabled(true);

		mMoreinformation=(TextView) findViewById(R.id.tv_more_information);
		mPackup = (TextView) findViewById(R.id.tv_pack_up);
		mDetailsordernumber=(LinearLayout) findViewById(R.id.ll_details_order_number);
		mDetailsorderdate=(LinearLayout) findViewById(R.id.ll_details_order_date);
		mDetailsemail=(LinearLayout) findViewById(R.id.ll_details_email);
		mViewserviceaddress = (TextView) findViewById(R.id.view_service_address);
		mViewdetailsordernumber = (TextView) findViewById(R.id.view_details_order_number);
		mViewdetailsorderdate = (TextView) findViewById(R.id.view_details_order_date);
		mServicereport = (ImageView) findViewById(R.id.iv_service_report);
		mExaminingreport=(ImageView) findViewById(R.id.iv_examiningreport);
		mQiandao=(TextView) findViewById(R.id.tv_qiandao);
//		viewBg = findViewById(R.id.myView);// 初始化遮罩层

		//GridView
		mGvservicereport = (GridView) findViewById(R.id.gv_service_report);
		mGvservicereport.setSelector(new ColorDrawable(Color.TRANSPARENT));
//		mGvservicereport.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				if (arg2 == Bimp.tempSelectBitmap.size()) {
//					Log.i("test", ""+Bimp.tempSelectBitmap.size());
//					ll_popup.startAnimation(AnimationUtils.loadAnimation(DetailsClueActivity.this,R.anim.activity_translate_in));
//					pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
//				} else {
//					Intent intent = new Intent(DetailsClueActivity.this,GalleryActivity.class);
//					intent.putExtra("position", "1");
//					intent.putExtra("ID", arg2);
//					startActivity(intent);
//				}
//			}
//		});

	}

	@Override
	protected void initData() {
		http = new HttpManager(this);
		http.setHttpCallBackListener(listener);
		// http.showProgressDialog();
		if (user.getPosition().equals("JL") && userPosition == 2) {
			tv_cheng.setVisibility(View.GONE);
//			tv_bai.setVisibility(View.GONE);
			tv_fan.setVisibility(View.GONE);
			rel_derails_fresh.setVisibility(View.GONE);
		} else if (user.getPosition().equals("JL") && userPosition == 3) {
			layout_details.setVisibility(View.GONE);
			rel_derails_fresh.setVisibility(View.GONE);
		}

//		else {
//			tv_fenpei.setVisibility(View.GONE);
//		}
//		webView.loadUrl("http://139.196.22.252:8081/AppHtml/TaskDetail.html?CludeId="
//				+ cludeId);
//		LogJY.d("http",
//				"http://139.196.22.252:8081/AppHtml/TaskDetail.html?CludeId="
//						+ cludeId);
//		webView.setWebViewClient(new WebViewClient() {
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return false;
//			}

//			@Override
//			public void onPageFinished(WebView view, String url) {
//				// TODO Auto-generated method stub
//				super.onPageFinished(view, url);
//				// http.disShowProgress();
//			}
//		});
//
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
//		tv_fenpei.setOnClickListener(this);
		tv_cheng.setOnClickListener(this);
//		tv_bai.setOnClickListener(this);
		tv_fan.setOnClickListener(this);
		rel_details_back.setOnClickListener(this);
		rel_derails_fresh.setOnClickListener(this);

		mMoreinformation.setOnClickListener(this);//更多信息
		mPackup.setOnClickListener(this);//收起
		mServicereport.setOnClickListener(this);
		mExaminingreport.setOnClickListener(this);
		mQiandao.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.rel_details_back:
				finish();
				break;
//		case R.id.tv_fenpei:
//			Intent intent1 = new Intent(this, TaskDistActivity.class);
//			intent1.putExtra("data", cludeId + ",");
//			intent1.putExtra("toStatus", toStatus);
//			startActivity(intent1);
//			DetailsClueActivity.this.finish();
//			break;
			case R.id.tv_cheng:
				http.showProgressDialog();
				http.doPost(ConstantsUrl.getClue(cludeId), 1, false);
				break;

//		case R.id.tv_bai:
//			Intent intent = new Intent(this, DefeatActivity.class);
//			intent.putExtra("data", cludeId);
//			startActivity(intent);
//			// DetailsClueActivity.this.finish();
//			break;
			case R.id.tv_fan:
				showDateTimePicker();
				break;
			case R.id.rel_derails_fresh:
				showTelDialog();
				break;

			//更多信息
			case R.id.tv_more_information:
				mDetailsemail.setVisibility(View.VISIBLE);
				mPackup.setVisibility(View.VISIBLE);
				mDetailsordernumber.setVisibility(View.VISIBLE);
				mDetailsorderdate.setVisibility(View.VISIBLE);
				mViewserviceaddress.setVisibility(View.VISIBLE);
				mViewdetailsordernumber.setVisibility(View.VISIBLE);
				mViewdetailsorderdate.setVisibility(View.VISIBLE);
				mMoreinformation.setVisibility(View.GONE);
				break;
			//收起
			case R.id.tv_pack_up:
				mDetailsemail.setVisibility(View.GONE);
				mDetailsordernumber.setVisibility(View.GONE);
				mDetailsorderdate.setVisibility(View.GONE);
				mMoreinformation.setVisibility(View.VISIBLE);
				mPackup.setVisibility(View.GONE);
				break;
			//添加服务报告
			case R.id.iv_service_report:
//			showpop();

				// 实例化SelectPicPopupWindow
				menuWindow = new popw(DetailsClueActivity.this, itemsOnClick);
				// 显示窗口
				menuWindow.showAtLocation(
						DetailsClueActivity.this.findViewById(R.id.iv_service_report),
						Gravity.BOTTOM | Gravity.LEFT, 0, 0); // 设置layout在PopupWindow中显示的位置
				break;
			//添加服务报告
			case R.id.iv_examiningreport:
//				showpop();

				// 实例化SelectPicPopupWindow
				menuWindow = new popw(DetailsClueActivity.this, itemsOnClick);
				// 显示窗口
				menuWindow.showAtLocation(
						DetailsClueActivity.this.findViewById(R.id.iv_examiningreport),
						Gravity.BOTTOM | Gravity.LEFT, 0, 0); // 设置layout在PopupWindow中显示的位置
				break;
			//签到
			case R.id.tv_qiandao:
				Intent qiandao = new Intent(this,SignActivity.class);
				startActivity(qiandao);
				break;

		}
	}

	HttpCallBackListener listener = new HttpCallBackListener() {

		@Override
		public void onSuccess(HttpMessage result, int tag, Object obj) {
			// TODO Auto-generated method stub
			switch (tag) {
				case 1:
					http.closeProgressDialog();
					showToast(result.getMsg());
					Intent intent = null;
					if (user.getPosition().equals("JL")) {
						// MissionGwActivity.instance.finish();
						intent = new Intent(DetailsClueActivity.this,
								MissionGwActivity.class);
					} else {
						// MainActivity.instance.finish();
						intent = new Intent(DetailsClueActivity.this,
								MainActivity.class);
						intent.putExtra("status", false);
					}
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					DetailsClueActivity.this.finish();
					break;

				case 2:
					// showToast(result.getMsg());
//				if (dialogError != null) {
//					dialogError.dismiss();
//				}
//				webView.loadUrl("http://139.196.22.252:8081/AppHtml/TaskDetail.html?CludeId="
//						+ cludeId);
					break;
				case 3:
					showToast(result.getMsg());
					Intent intent1 = null;
					if (user.getPosition().equals("JL")) {
						intent1 = new Intent(DetailsClueActivity.this,
								MissionGwActivity.class);
					} else {
						intent1 = new Intent(DetailsClueActivity.this,
								MainActivity.class);
						intent1.putExtra("status", false);
					}
					intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent1);
					DetailsClueActivity.this.finish();
					break;
			}
		}

		@Override
		public void onError(String msg, int tag, Object obj) {
			// TODO Auto-generated method stub
			switch (tag) {
				case 1:
					http.closeProgressDialog();
					break;
				case 2:
					if (dialogError != null) {
						dialogError.dismiss();
					}
					showTelError();
					break;
				case 3:

					break;
			}
		}
	};

	private void showTelDialog() {
		final Dialog dialog = new Dialog(this, R.style.Translucent_NoTitle);
		View view = LayoutInflater.from(this).inflate(R.layout.dialog_tel_call,
				null);
		TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
		tv_phone.setText(phone);
		TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
		TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);
		dialog.setCancelable(false);
		dialog.setContentView(view);
		tv_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				phoneReceiver = new PhoneReceiver();
				IntentFilter intentFilter = new IntentFilter();
				intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
				registerReceiver(phoneReceiver, intentFilter);

				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ phone));
				startActivity(intent);
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
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int) (display.getWidth() / 1.7); // 设置宽度
		dialog.getWindow().setAttributes(lp);

	}

	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (isFinishing())
				return;
			// http.closeProgressDialog();
			switch (msg.what) {
				case PHONE_SEND:
					GetCallPhoneMethod();
					if (telTimes.size() > 0) {
						http.doPost(
								ConstantsUrl.callRecording(cludeId,
										telTimes.get(telTimes.size() - 1),
										TimeUtil.getTime()), 2, false);
					}
					break;
			}
		}
	};

	/**
	 * 获取通话记录的方法
	 */
	private void GetCallPhoneMethod() {
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI,
				new String[] { CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
						CallLog.Calls.TYPE, CallLog.Calls.DURATION,
						CallLog.Calls.DATE }, null, null, CallLog.Calls.DATE);
		while (cursor.moveToNext()) {
			if (cursor.getString(0).equals(phone)) {
				String telTime = GetCallLengthMethod(cursor.getInt(3));
				telTimes.add(telTime);
			}
		}

	}

	/**
	 * 获取通话时长的方法
	 *
	 * @param seconds
	 *            传入的秒数
	 * @return 转换过后的时长
	 */
	private String GetCallLengthMethod(int seconds) {
		String length = "";
		if (seconds < 60) {
			length = seconds + "秒";
		} else if (seconds > 60) {
			length = seconds / 60 + "分" + seconds % 60 + "秒";
		}

		return length;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (phoneReceiver != null) {
			unregisterReceiver(phoneReceiver);
		}

	}

	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	private void showDateTimePicker() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// dialog.setTitle("请选择日期与时间");
		// 找到dialog的布局文件
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.time_layout, null);
		RadioGroup rdgroup_fk = (RadioGroup) view.findViewById(R.id.rdgroup_fk);
//		final RadioButton btn_0 = (RadioButton) view.findViewById(R.id.btn_0);
//		final RadioButton btn_1 = (RadioButton) view.findViewById(R.id.btn_1);
//		final RadioButton btn_2 = (RadioButton) view.findViewById(R.id.btn_2);
		final RadioButton btn_3 = (RadioButton) view.findViewById(R.id.btn_3);
		final EditText editContent = (EditText) view
				.findViewById(R.id.editContent);
//		radioStr = btn_0.getText().toString();
		rdgroup_fk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
//				case R.id.btn_0:
//					editContent.setVisibility(View.GONE);
//					radioStr = btn_0.getText().toString();
//					break;
//				case R.id.btn_1:
//					editContent.setVisibility(View.GONE);
//					radioStr = btn_1.getText().toString();
//					break;
//				case R.id.btn_2:
//					editContent.setVisibility(View.GONE);
//					radioStr = btn_2.getText().toString();
//					break;
					case R.id.btn_3:
						radioStr = "";
						break;
				}
			}
		});
		// 年
		final WheelView wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		final WheelView wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		final WheelView wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("日");
		wv_day.setCurrentItem(day - 1);

		// 时
		final WheelView wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);
		wv_hours.setCurrentItem(hour);

		// 分
		final WheelView wv_mins = (WheelView) view.findViewById(R.id.mins);
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
		wv_mins.setCyclic(true);
		wv_mins.setCurrentItem(minute);

		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小
		int textSize = 0;

		textSize = PxDpUtils.sp2px(context, 18);
		wv_day.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) view
				.findViewById(R.id.btn_datetime_cancel);

		// 确定
		btn_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 如果是个数,则显示为"02"的样式
				String parten = "00";
				DecimalFormat decimal = new DecimalFormat(parten);
				// 设置日期的显示
				// tv_time.setText((wv_year.getCurrentItem() + START_YEAR) + "-"
				// + decimal.format((wv_month.getCurrentItem() + 1)) + "-"
				// + decimal.format((wv_day.getCurrentItem() + 1)) + " "
				// + decimal.format(wv_hours.getCurrentItem()) + ":"
				// + decimal.format(wv_mins.getCurrentItem()));
				String fkTime = (wv_year.getCurrentItem() + START_YEAR) + "-"
						+ decimal.format((wv_month.getCurrentItem() + 1)) + "-"
						+ decimal.format((wv_day.getCurrentItem() + 1)) + " "
						+ decimal.format(wv_hours.getCurrentItem()) + ":"
						+ decimal.format(wv_mins.getCurrentItem());
				try {
					if (radioStr.equals("")) {
						http.doPost(ConstantsUrl.requestBookCall(cludeId,
								URLEncoder.encode(fkTime, "UTF-8"),
								URLEncoder.encode(radioStr, "UTF-8")), 3, false);
						dialog.dismiss();
					}
//					else if (!TextUtils.isEmpty(editContent.getText())) {
//						http.doPost(ConstantsUrl.requestBookCall(cludeId,
//								URLEncoder.encode(fkTime, "UTF-8"), URLEncoder
//										.encode(editContent.getText()
//												.toString(), "UTF-8")), 3,
//								false);
//						dialog.dismiss();
//					}
//					else {
//						showToast("请输入或选择预约信息");
//					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
		});
		// 取消
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		// 设置dialog的布局,并显示
		dialog.setContentView(view);
		dialog.show();
	}

	// 提交出现异常
	private void showTelError() {
		dialogError = new Dialog(this, R.style.Translucent_NoTitle);
		View view = LayoutInflater.from(this).inflate(
				R.layout.dialog_tel_error, null);
		TextView tv_cancel_err = (TextView) view
				.findViewById(R.id.tv_cancel_err);
		TextView tv_ok_err = (TextView) view.findViewById(R.id.tv_ok_err);
		dialogError.setContentView(view);
		tv_ok_err.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (telTimes.size() > 0) {
					http.doPost(
							ConstantsUrl.callRecording(cludeId,
									telTimes.get(telTimes.size() - 1),
									TimeUtil.getTime()), 2, false);
				}
			}
		});
		tv_cancel_err.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogError.dismiss();
			}
		});
		dialogError.show();
		/*
		 * WindowManager windowManager = getWindowManager(); Display display =
		 * windowManager.getDefaultDisplay(); WindowManager.LayoutParams lp =
		 * dialog.getWindow().getAttributes(); lp.width =
		 * (int)(display.getWidth()/1.4); //设置宽度
		 * dialog.getWindow().setAttributes(lp);
		 */

	}



	// 为弹出窗口实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
				case R.id.btn_take_photo:
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, 1);
					break;
				case R.id.btn_pick_photo:
					Intent i = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

					startActivityForResult(i, 0);

					break;
			}
		}
	};






}
