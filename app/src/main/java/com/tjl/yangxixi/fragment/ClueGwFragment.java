package com.tjl.yangxixi.fragment;

import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *
 * @author Administrator
 *     线索报告
 */

public class ClueGwFragment extends OriginalFragment{
	private WebView webview_gw_reporting;
	private UserInfoBean user;
	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_clue_gw, null);
		initView(view);
		return view;
	}
	private void initView(View v){
		user=MyApplication.getInstance().getUserList().get(0);
		webview_gw_reporting=(WebView) v.findViewById(R.id.webview_gw_reporting);
		webview_gw_reporting.getSettings().setJavaScriptEnabled(true);
		webview_gw_reporting.loadUrl("http://139.196.22.252:8081/AppHtml/SaleTaskReport_1.html?userId="+user.getEmp_id());
		webview_gw_reporting.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}
}
