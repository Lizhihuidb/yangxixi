package com.tjl.yangxixi.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;

public class SignInInfoActivity extends BaseActivity{
	private WebView webview_signin;
	private int id;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_single);
		setTitleView(true, false,"签到查看");
		id=getIntent().getIntExtra("id", 0);
		webview_signin=(WebView) findViewById(R.id.webview_single);
		webview_signin.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		webview_signin.loadUrl("http://139.196.22.252:8081/AppHtml/SignMap.html?appId="+id);
		webview_signin.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});
	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}

}
