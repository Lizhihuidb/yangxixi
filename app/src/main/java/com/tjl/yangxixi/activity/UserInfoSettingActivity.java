package com.tjl.yangxixi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;

public class UserInfoSettingActivity extends BaseActivity {
	private WebView webview_userinfo;
	private int toStatus;
	private UserInfoBean user;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_userinfo_setting);
		webview_userinfo = (WebView) findViewById(R.id.webview_userinfo);
		user = MyApplication.getInstance().getUserList().get(0);
		toStatus = getIntent().getIntExtra("toStatus", 0);
		webview_userinfo.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		if (toStatus == 1) {
			webview_userinfo
					.loadUrl("http://139.196.22.252:8081/AppHtml/UserInfo_1.html?userId="
							+ user.getEmp_id());

		} else if (toStatus == 2) {
			webview_userinfo
					.loadUrl("http://139.196.22.252:8081/AppHtml/ResetPassword_1.html?userCode="
							+ user.getEmp_id());

		}
		webview_userinfo.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});
		webview_userinfo.addJavascriptInterface(new WebAppInterface(), "demo");
		webview_userinfo.addJavascriptInterface(new WebAppInterfaceSubMit(), "userClose");
	}


	class WebAppInterface {
		WebAppInterface(){
		}

		/** Show a toast from the web page */
		// 如果target 大于等于API 17，则需要加上如下注解
		@JavascriptInterface
		public void clickOnAndroid()
		{
			UserInfoSettingActivity.this.finish();
		}
	}
	class WebAppInterfaceSubMit
	{
		WebAppInterfaceSubMit(){
		}

		/** Show a toast from the web page */
		// 如果target 大于等于API 17，则需要加上如下注解
		@JavascriptInterface
		public void back()
		{
			// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			//Toast.makeText(ActivityWebViewActivity.this, toast+"111", Toast.LENGTH_LONG).show();
			// 这里面做你想做的，弹出“对话框”
			Intent intent = new Intent(UserInfoSettingActivity.this, LoginActivity.class);
			startActivity(intent);
			//MainActivity.instance.finish();
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			Toast.makeText(UserInfoSettingActivity.this, "修改资料成功，请重新登录", Toast.LENGTH_LONG).show();
			finish();
		}
	}
	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}

}
