package com.tjl.yangxixi.manager;

import java.io.UnsupportedEncodingException;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.tjl.yangxixi.R;
import com.tjl.yangxixi.bean.HttpMessage;
import com.tjl.yangxixi.params.ConstantsUrl;
import com.tjl.yangxixi.utils.CustomProgress;
import com.tjl.yangxixi.utils.LogJY;

/**
 * 网络请求封装
 *
 * @author tao
 *
 */
public class HttpManager {
	private final String TAG = "http";
	private final String TAG_REQUEST = "request";

	private HttpUtils http;
	private HttpCallBackListener listener;
	public Context context;
	private ProgressDialog progressDialog;
	private Dialog dialog;
	private boolean isShowError = true;
	private boolean isErrorRequest = false;

	@SuppressLint("InflateParams")
	public HttpManager(Context context) {
		this.context = context;
		http = new HttpUtils();

		try {
			dialog = new Dialog(context);
			LayoutInflater inflter = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			dialog.setContentView(inflter.inflate(R.layout.dialog_progress,
					null), new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			dialog.setCancelable(false);
		} catch (RuntimeException e) {
		}

	}

	public void showProgress() {
		if (dialog != null)
			dialog.show();
	}

	public void disShowProgress() {
		if (dialog != null)
			dialog.dismiss();
	}

	public void doPost(String url, int tag) {
		doPost(url, null, tag, true);
	}

	public void doPost(String url, int tag, Object obj) {
		doPost(url, null, tag, true, obj);
	}

	public void doPost(String url, int tag, boolean isShowProgress, Object obj) {
		doPost(url, null, tag, isShowProgress, obj);
	}

	public void doPost(String url, int tag, boolean isShowProgress) {
		doPost(url, null, tag, isShowProgress);
	}

	public void doPost(String url, RequestParams params, int tag) {
		doPost(url, params, tag, false);
	}

	public void doPost(String url, RequestParams params, int tag,
					   boolean isShowProgress, Object obj) {
		doHttp(HttpMethod.POST, url, params, tag, isShowProgress, obj);
	}

	public void doPost(String url, RequestParams params, int tag,
					   boolean isShowProgress) {
		doHttp(HttpMethod.GET, url, params, tag, isShowProgress, null);
	}

	public void doGet(String url, int tag, boolean isShowProgress) {
		doHttp(HttpMethod.GET, url, null, tag, isShowProgress, null);
	}

	/**
	 * 请求网络
	 *
	 * @param method
	 * @param url
	 * @param params
	 * @param tag
	 * @param isShowProgress
	 * @param obj
	 */
	private void doHttp(HttpMethod method, String url, RequestParams params,
						final int tag, final boolean isShowProgress, final Object obj) {
		if (isShowProgress) {
			showProgress();
		}
		LogJY.d(TAG, "url:" + url + "   " + params);
		http.configCurrentHttpCacheExpiry(500);
		/**
		 * 请求网络
		 */
		http.send(method, url, params, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				LogJY.d(TAG_REQUEST, "tag:" + tag + "     onSuccess:"
						+ responseInfo.result);
				if (isShowProgress) {
					disShowProgress();
				}
				// 需要判断是否是json
				HttpMessage msg = JSON.parseObject(responseInfo.result,
						HttpMessage.class);
				// msg.setData(responseInfo.result);
				/* if(msg.getResult()==1){ */
				if (msg.getResult() == 1) {
					HttpManager.this.onSuccess(msg, tag, obj);
					Log.e("请求成功", msg.getData());
				} else {// 请求发生错误
					disShowProgress();
					if (isShowError)
						try {
							HttpManager.this.onSuccess(msg, tag, obj);
							Log.e("请求失败", msg.getData()+"");
							// DialogFractory.showSingleButtonDialog(context,""
							// + msg.getMsg());
						} catch (RuntimeException e) {
						}
					if (isErrorRequest) {
						HttpManager.this.onSuccess(msg, tag, obj);
					}
				}
				/*
				 * }else{ Toast.makeText(context, "没有相应的数据",
				 * Toast.LENGTH_SHORT).show(); }
				 */

			}

			@Override
			public void onFailure(HttpException error, String msg) {
				LogJY.e(TAG_REQUEST, "tag:" + tag + "onFailure:" + msg);
				if (isShowProgress) {
					disShowProgress();
				}
				HttpManager.this.onError(msg, tag, obj);
				/**
				 * 网络异常
				 */
				Toast.makeText(context, error.getExceptionCode() + msg,
						Toast.LENGTH_SHORT).show();

				error.printStackTrace();
			}
		});

	}

	/**
	 * 判断网络状态
	 *
	 * @param activity
	 * @return
	 */
	public boolean isNetworkAvailable(Context activity) {
		Context context = activity.getApplicationContext();
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null) {
			return false;
		} else {
			// 获取NetworkInfo对象
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0) {
				for (int i = 0; i < networkInfo.length; i++) {
					// 判断当前网络状态是否为连接状态
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 设置访问出错是否提示dialog
	 *
	 * @param flag
	 */
	public void configShowError(boolean flag) {
		this.isShowError = flag;
	}

	/**
	 * 访问出错了，是否继续向下传递 默认是false
	 */
	public void configErrorRequest(boolean isErrorRequest) {
		this.isErrorRequest = isErrorRequest;
	}

	public void onSuccess(HttpMessage result, int tag, Object obj) {
		if (listener != null) {
			listener.onSuccess(result, tag, obj);
		}
	}

	public void onError(String msg, int tag, Object obj) {
		if (listener != null) {
			listener.onError(msg, tag, obj);
		}
	}

	public void setHttpCallBackListener(HttpCallBackListener listener) {
		this.listener = listener;
	}

	public interface HttpCallBackListener {
		void onSuccess(HttpMessage result, int tag, Object obj);

		void onError(String msg, int tag, Object obj);
	}

	public void showProgressDialog() {
		CustomProgress.show(context, "", true, null);
		/*
		 * if (progressDialog == null || !progressDialog.isShowing()) {
		 * progressDialog = new ProgressDialog(context);
		 * //progressDialog.setMessage
		 * (context.getString(R.string.common_processing));
		 * progressDialog.show(); }
		 */
	}

	public void showProgressDialog(String str) {
		CustomProgress.show(context, str, true, null);
		/*
		 * if (progressDialog == null || !progressDialog.isShowing()) {
		 * progressDialog = new ProgressDialog(context);
		 * //progressDialog.setMessage
		 * (context.getString(R.string.common_processing));
		 * progressDialog.show(); }
		 */
	}

	public void closeProgressDialog() {
		CustomProgress.closeDialog();
		/*
		 * if (progressDialog != null && progressDialog.isShowing()) {
		 * progressDialog.dismiss(); }
		 */
	}
}
