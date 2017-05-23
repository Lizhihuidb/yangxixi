package com.tjl.yangxixi.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;

public class SingleActivity extends BaseActivity{
	private WebView webview_single;
	private int toStatus;
	private UserInfoBean user;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_single);
		user=MyApplication.getInstance().getUserList().get(0);
		toStatus=getIntent().getIntExtra("toStatus", 0);
		webview_single=(WebView) findViewById(R.id.webview_single);
		webview_single.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		if(toStatus==1){
			setTitleView(true, "成单分析");
			webview_single.loadUrl("http://139.196.22.252:8081/AppHtml/SaleTaskFollReport.html?userId="+user.getEmp_id()+"&clueState=4");
		}else if(toStatus==2){
			setTitleView(true, "战败分析");
			webview_single.loadUrl("http://139.196.22.252:8081/AppHtml/UserTaskLosList.html?UserCode="+user.getEmp_id()+"&clueState=3");
		}
		webview_single.setWebViewClient(new WebViewClient() {
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
