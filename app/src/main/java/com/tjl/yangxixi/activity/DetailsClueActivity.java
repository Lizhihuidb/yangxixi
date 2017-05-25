package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.manager.HttpManager;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.receiver.PhoneReceiver;
import com.tjl.yangxixi.utils.popw;

/**
 *
 * @author Administrator
 *    销售经理的订单详情列表
 */
@SuppressLint("SetJavaScriptEnabled")
public class DetailsClueActivity extends Activity implements
		OnClickListener {

	// 自定义的弹出框类
	popw menuWindow;

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
	public static DetailsClueActivity getDetailsActivity() {
		return activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_details);
		tv_cheng = (TextView) findViewById(R.id.tv_cheng);
		tv_fan = (TextView) findViewById(R.id.tv_fan);
		layout_details = (LinearLayout) findViewById(R.id.layout_details);
		rel_details_back = (RelativeLayout) findViewById(R.id.rel_details_back);
		rel_derails_fresh = (RelativeLayout) findViewById(R.id.rel_derails_fresh);

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

		//GridView
		mGvservicereport = (GridView) findViewById(R.id.gv_service_report);
		mGvservicereport.setSelector(new ColorDrawable(Color.TRANSPARENT));
		tv_cheng.setOnClickListener(this);
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
			case R.id.tv_cheng:
				http.showProgressDialog();
				http.doPost(ConstantsUrl.getClue(cludeId), 1, false);
				break;

			case R.id.tv_fan:
//				showDateTimePicker();
				break;
			case R.id.rel_derails_fresh:
//				showTelDialog();
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
