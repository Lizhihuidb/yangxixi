package com.tjl.yangxixi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tjl.yangxixi.R;

public class ToolsUtils {
	private TextView tvTitleText;
	private RelativeLayout rel_back,rel_fresh;
	/**
	 * 初始化标题
	 * @param isShowBack
	 * @param isFresh
	 * @param titleText
	 * @param context
	 * @param v
	 */
	public void initTitleView(final boolean isShowBack,final boolean isFresh,
							  final String titleText,final Activity context,View v) {
		tvTitleText = (TextView) v.findViewById(R.id.tv_title_title);
		tvTitleText.setText(titleText);
		if (isShowBack) {
			rel_back=(RelativeLayout) v.findViewById(R.id.rel_back);
			rel_back.setVisibility(View.VISIBLE);
			rel_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					context.finish();
				}
			});
		}
		if(isFresh){
			rel_fresh=(RelativeLayout) v.findViewById(R.id.rel_fresh);
			rel_fresh.setVisibility(View.VISIBLE);
		}

	}
	/**
	 * 加载网络图片
	 * @param uri
	 * @param imageView
	 */
	public static void displayImage(String uri,ImageView imageView) {
		ImageLoader.getInstance().displayImage(uri, imageView);
	}
	public static void displayFromDrawable(int imageId, ImageView imageView) {
		// String imageUri = "drawable://" + R.drawable.image; // from drawables
		// (only images, non-9patch)
		ImageLoader.getInstance().displayImage("drawable://" + imageId,imageView);
	}
	/**
	 * 获取现在时间
	 *
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取当前日期
	 *
	 * @return返回字符串格式 yyyy-MM-dd
	 */
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 检测当的网络（WLAN、3G/2G）状态
	 * @param context Context
	 * @return true 表示网络可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected())
			{
				// 当前网络是连接的
				if (info.getState() == NetworkInfo.State.CONNECTED)
				{
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 启动系统安装器
	 * @param ctx
	 * @param filePath
	 */
	public static void startDefInstallApk( Context ctx, String filePath){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://"+filePath),"application/vnd.android.package-archive");
		ctx.startActivity(intent);
	}
}
