package com.tjl.yangxixi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lyp.yangxixi.photo.utils.CustomDialogs;
import com.tjl.yangxixi.R;

/**
 *
 * @author Administrator
 *  销售经理的重置密码界面
 */
public class ResetPwdActivity extends Activity implements OnClickListener{

	private Button mReset;
	private ImageView mback;
	CustomDialogs selfDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_pwd);
		initview();
	}

	void initview(){
		mback = (ImageView) findViewById(R.id.iv_reset_back);
		mReset = (Button) findViewById(R.id.button_reset);
		mback.setOnClickListener(this);
		mReset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				backgroundAlpha(0.5f);
				selfDialog = new CustomDialogs(ResetPwdActivity.this);
				selfDialog.setTitle(" 输入你的密码 " );
				selfDialog.setYesOnclickListener(" 确定 " , new CustomDialogs.onYesOnclickListener() {

					@Override
					public void onYesClick() {
						Toast.makeText(ResetPwdActivity.this, " 点击了 -- 确定 -- 按钮 " , Toast.LENGTH_SHORT).show();
						selfDialog.dismiss();
						WindowManager.LayoutParams lp=getWindow().getAttributes();
						lp.alpha=1f;
						getWindow().setAttributes(lp);


					}
				});
				selfDialog.setNoOnclickListener(" 取消 " , new CustomDialogs.onNoOnclickListener() {
					@Override
					public void onNoClick() {
						Toast.makeText(ResetPwdActivity.this, " 点击了 -- 取消 -- 按钮 " , Toast.LENGTH_SHORT).show();
						selfDialog.dismiss();
						WindowManager.LayoutParams lp=getWindow().getAttributes();
						lp.alpha=1f;
						getWindow().setAttributes(lp);
					}
				});
				selfDialog.show();
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			case R.id.iv_reset_back:
				finish();
				break;

		}

	}

	/**
	 * 设置添加屏幕的背景透明度
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha){
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; //0.0-1.0
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}

}
