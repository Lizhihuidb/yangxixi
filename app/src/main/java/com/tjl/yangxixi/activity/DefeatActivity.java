package com.tjl.yangxixi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tjl.yangxixi.BaseActivity;
import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;

public class DefeatActivity extends BaseActivity implements OnClickListener{
	private WebView webview_defeat;
	private String clueId;
	private UserInfoBean user;
	private RelativeLayout rel_defeat_back;
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_defeat);
		clueId=getIntent().getStringExtra("data");
		user=MyApplication.getInstance().getUserList().get(0);
		webview_defeat=(WebView) findViewById(R.id.webview_defeat);
		rel_defeat_back=(RelativeLayout) findViewById(R.id.rel_defeat_back);
		webview_defeat.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		webview_defeat.loadUrl("http://139.196.22.252:8081/AppHtml/TaskLose_1.html?userId="+user.getEmp_id()+"&clueId="+clueId);
		webview_defeat.addJavascriptInterface(new WebAppInterface(), "demo");
		webview_defeat.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
									 JsResult result) {
				// TODO Auto-generated method stub
				return super.onJsAlert(view, url, message, result);
			}


		});
		webview_defeat.addJavascriptInterface(new MyJavaScriptInterface(), "js2java");
	}

	class WebAppInterface
	{
		WebAppInterface(){
		}

		/** Show a toast from the web page */
		// 如果target 大于等于API 17，则需要加上如下注解
		@JavascriptInterface
		public void clickOnAndroid()
		{
			// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			//Toast.makeText(ActivityWebViewActivity.this, toast+"111", Toast.LENGTH_LONG).show();
			// 这里面做你想做的，弹出“对话框”
			finish();
		}
	}
	@Override
	protected void initOnclik() {
		// TODO Auto-generated method stub
		rel_defeat_back.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.rel_defeat_back:
/*			Intent intent=new Intent(DefeatActivity.this, MainActivity.class);
			intent.putExtra("status", false);
			startActivity(intent);
			DefeatActivity.this.finish();*/
				finish();
				break;

			default:
				break;
		}
	}
	final class MyJavaScriptInterface {
		MyJavaScriptInterface(){}
		/** Show a toast from the web page */
		// 如果target 大于等于API 17，则需要加上如下注解
		@JavascriptInterface
		public void back() {
			Toast.makeText(DefeatActivity.this, "提交成功，请继续努力！", Toast.LENGTH_SHORT).show();
			Intent intent = null;
			if(user.getPosition().equals("JL")){
				DetailsClueActivity.getDetailsActivity().finish();
				//MissionGwActivity.instance.finish();
				intent = new Intent(DefeatActivity.this,MissionGwActivity.class);
			}else{
				DetailsClueActivity.getDetailsActivity().finish();
				//MainActivity.instance.finish();
				intent = new Intent(DefeatActivity.this,MainActivity.class);
				intent.putExtra("status", false);
			}
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
	}
}
