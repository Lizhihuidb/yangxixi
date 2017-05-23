package com.tjl.yangxixi.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.R;

/**
 * 忘记密码
 *
 * @author ChenHong
 *
 */
public class ForgetPwdActivity extends BaseActivity {
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_waiqing);
		setTitleView(true, "找回密码");
		WebView webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				ForgetPwdActivity.this.setProgress(progress * 1000);
			}
		});

		webView.loadUrl("http://139.196.22.252:8081/AppHtml/ForgetPassWord.html");

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub

	}
}
