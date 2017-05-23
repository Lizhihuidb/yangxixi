package com.tjl.yangxixi.fragment;

import com.tjl.yangxixi.MyApplication;
import com.tjl.yangxixi.OriginalFragment;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.UserInfoBean;
import com.tjl.yangxixi.utils.ToolsUtils;
import com.tjl.yangxixi.view.FragmentTabHost;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 *
 * @author Administrator
 *		销售经理端的线索报告
 */
public class ClueFragment extends OriginalFragment implements OnClickListener{
	private FragmentTabHost host;
	private WebView webview_clue;
	private LinearLayout lay_clue,lay_followup_reporting,lay_gw_reporting,lay_cause_defeat;
	private UserInfoBean user;
	// 标志位，标志已经初始化完成。
	private boolean isPrepared;
	@Override
	public View createView(LayoutInflater inflater,
						   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = inflater.inflate(R.layout.fragment_clue, null);
		init(v);
		isPrepared = true;
		lazyLoad();
		return v;
	}
	private void init(View v){
		user=MyApplication.getInstance().getUserList().get(0);
		new ToolsUtils().initTitleView(false,false, getActivity().getString(R.string.txt_clue), getActivity(),v);
		webview_clue=(WebView) v.findViewById(R.id.webview_clue);
		lay_clue=(LinearLayout) v.findViewById(R.id.lay_clue);
		lay_followup_reporting=(LinearLayout) v.findViewById(R.id.lay_followup_reporting);
		lay_gw_reporting=(LinearLayout) v.findViewById(R.id.lay_gw_reporting);
		lay_cause_defeat=(LinearLayout) v.findViewById(R.id.lay_cause_defeat);
		webview_clue.getSettings().setJavaScriptEnabled(true);
		webview_clue.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;

			}
		});

		lay_followup_reporting.setOnClickListener(this);
		lay_gw_reporting.setOnClickListener(this);
		lay_cause_defeat.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.lay_followup_reporting:
				if(ToolsUtils.isNetworkAvailable(getActivity())){
					webview_clue.loadUrl("http://139.196.22.252:8081/AppHtml/ClueReport_1.html?userId="+user.getEmp_id());
					lay_clue.setVisibility(View.GONE);
					webview_clue.setVisibility(View.VISIBLE);
				}else{
					Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.lay_gw_reporting:
				if(ToolsUtils.isNetworkAvailable(getActivity())){
					webview_clue.loadUrl("http://139.196.22.252:8081/AppHtml/ClueConversion_1.html?userId="+user.getEmp_id());
					lay_clue.setVisibility(View.GONE);
					webview_clue.setVisibility(View.VISIBLE);
				}else{
					Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.lay_cause_defeat:
				if(ToolsUtils.isNetworkAvailable(getActivity())){
					webview_clue.loadUrl("http://139.196.22.252:8081/AppHtml/ClueLosCauseReport_1.html?userId="+user.getEmp_id());
					lay_clue.setVisibility(View.GONE);
					webview_clue.setVisibility(View.VISIBLE);
				}else{
					Toast.makeText(getActivity(), getActivity().getString(R.string.common_network_exception), Toast.LENGTH_SHORT).show();
				}

				break;
		}

		webview_clue.addJavascriptInterface(new WebAppInterface(), "demo");
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
			//host.setPostion(2);
			// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			//Toast.makeText(ActivityWebViewActivity.this, toast+"111", Toast.LENGTH_LONG).show();
			// 这里面做你想做的，弹出“对话框”
			//lay_clue.setVisibility(View.VISIBLE);
			//webview_clue.setVisibility(View.GONE);
			new Thread(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					Message msg = Message.obtain();
					handler.sendMessage(msg);
				}
			}.start();
		}
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			webview_clue.setVisibility(View.GONE);
			lay_clue.setVisibility(View.VISIBLE);
		};
	};
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub

	}
}
