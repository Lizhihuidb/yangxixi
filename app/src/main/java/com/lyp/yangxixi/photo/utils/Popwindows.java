package com.lyp.yangxixi.photo.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.datepicker.MonthDateView;
import com.lyp.datepicker.MonthDateView.DateClick;
import com.tjl.yangxixi.R;


public class Popwindows extends PopupWindow{
	private View mMenuView;
	private ImageView mLeft,mRight;
	private TextView mDate,mWeek;
	private MonthDateView monthDateView;

	public Popwindows(final Activity context, OnClickListener itemsOnClick) {
		super(context);
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(12);
		list.add(15);
		list.add(16);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.activity_order_query, null);
		mLeft = (ImageView) mMenuView.findViewById(R.id.iv_left);
		mRight = (ImageView) mMenuView.findViewById(R.id.iv_right);
		mDate = (TextView) mMenuView.findViewById(R.id.date_text);
		mWeek  =(TextView) mMenuView.findViewById(R.id.week_text);
		monthDateView = (MonthDateView) mMenuView.findViewById(R.id.monthDateView);
		mDate.setOnClickListener(itemsOnClick);
		mWeek.setOnClickListener(itemsOnClick);
		monthDateView.setTextView(mDate,mWeek);
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new DateClick() {

			@Override
			public void onClickOnDate() {
				Toast.makeText(context.getApplication(), monthDateView.getmSelYear()+"年"+monthDateView.getmSelMonth()+"月"+ monthDateView.getmSelDay()+"月", 0).show();
			}
		});

		mLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				monthDateView.onLeftClick();
			}
		});

		mRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				monthDateView.onRightClick();
			}
		});


		//设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		//设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		//设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		//设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.Popupwindow);
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		//设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
//      mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框  
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.popwindows).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						//证明此时点击的是框的外部
						dismiss();
					}
				}
				return true;
			}

		});
	}

}