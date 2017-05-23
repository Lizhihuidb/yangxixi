package com.tjl.yangxixi.activity;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;

public class ProtocolActivity extends BaseActivity{
	private WebView webview_about;
	private int toStatus;
	private UserInfoBean user;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_protocol);
		webview_about=(WebView) findViewById(R.id.webview_about);
		toStatus=getIntent().getIntExtra("toStatus", 0);
		user=MyApplication.getInstance().getUserList().get(0);
		webview_about.getSettings().setJavaScriptEnabled(true);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		if(toStatus==1){
			setTitleView(true, true, "使用条款和隐私条款");
			//webview_about.loadUrl("http://139.196.22.252:8081/AppHtml/UsePrivacyTerms.html");
		}else{
			setTitleView(true, true, "意见反馈");
			//webview_about.loadUrl("http://139.196.22.252:8081//AppHtml/FeedBack.html?userName="+user.getEmp_name());
		}
		webview_about.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});
		webview_about.addJavascriptInterface(new WebAppInterfaceSubMit(), "demo");
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
			showToast("意见反馈提交成功");
			finish();
		}
	}
	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}

}
