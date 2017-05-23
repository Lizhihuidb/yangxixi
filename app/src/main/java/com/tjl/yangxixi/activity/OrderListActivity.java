package com.tjl.yangxixi.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;

import com.lyp.yangxixi.photo.utils.Popwindows;
import com.lyp.yangxixi.photo.utils.WindowBackgroundAlphaUtils;
import com.tjl.yangxixi.R;


/**
 *
 * @author Administrator
 *  预约列表
 */
public class OrderListActivity extends Activity implements OnClickListener{

	private ImageView mOrderlist;
	private Button main;
	private Popwindows myWindow;
	private LinearLayout mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);
		initview();
	}


	void initview(){
		mOrderlist = (ImageView) findViewById(R.id.iv_orderlistbutton);
		mList = (LinearLayout) findViewById(R.id.ll_list);
		mOrderlist.setOnClickListener(this);
		mList.setOnClickListener(this);

	}




	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
			//弹出日期界面
			case R.id.iv_orderlistbutton:
				getSelectPic();

				break;
			//任务详情
			case R.id.ll_list:
				intent = new Intent(this,DetailsClueActivity.class);
				startActivity(intent);
				break;
		}
	}


	private void getSelectPic() {
		myWindow = new Popwindows(this,null);
		myWindow.showAtLocation(mOrderlist, Gravity.CENTER, 0, 0);//只显示在相对父控件ll_parent中间位置，如果是底部，那就加上Gravity.BOTTOM
		WindowBackgroundAlphaUtils.backgroundAlpha(OrderListActivity.this, 0.2f);
		myWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub

				//设置popupWindow监听，当消失的时候，记得把背景变亮。

				WindowBackgroundAlphaUtils.backgroundAlpha(OrderListActivity.this, 1.0f);
			}
		});
	}

}
