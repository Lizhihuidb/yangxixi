package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.datepicker.MonthDateView;
import com.lyp.datepicker.MonthDateView.DateClick;
import com.tjl.yangxixi.R;

/**
 *
 * @author Administrator
 * 销售员的预约查询
 */
public class OrderQueryActivity extends Activity implements OnClickListener{

	private ImageView mLeft,mRight,mOrderbutton,mBack;
	private TextView mDate,mWeek;
	//	private TextView tv_today;
	private MonthDateView monthDateView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(12);
		list.add(15);
		list.add(16);
		setContentView(R.layout.activity_order_query);
		mLeft = (ImageView) findViewById(R.id.iv_left);
		mRight = (ImageView) findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
		mDate = (TextView) findViewById(R.id.date_text);
		mWeek  =(TextView) findViewById(R.id.week_text);
//		mOrderbutton= (ImageView) findViewById(R.id.iv_orderbutton);
		mBack=(ImageView) findViewById(R.id.iv_orderback);
		mLeft.setOnClickListener(this);
		mRight.setOnClickListener(this);
		mDate.setOnClickListener(this);
		mWeek.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mOrderbutton.setOnClickListener(this);
		monthDateView.setTextView(mDate,mWeek);
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new DateClick() {

			@Override
			public void onClickOnDate() {
				Toast.makeText(getApplication(), monthDateView.getmSelYear()+"年"+monthDateView.getmSelMonth()+"月"+ monthDateView.getmSelDay()+"月", Toast.LENGTH_SHORT).show();
			}
		});
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.iv_orderback:
				finish();
				break;
			case R.id.iv_left:
				monthDateView.onLeftClick();
				break;
			case R.id.iv_right:
				monthDateView.onRightClick();
				break;

		}
	}


}
