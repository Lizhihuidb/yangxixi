package com.tjl.yangxixi.service;


import com.tjl.yangxixi.R;
import com.tjl.yangxixi.activity.LoginActivity;
import com.tjl.yangxixi.bean.AppInfoBean;
import com.tjl.yangxixi.download.DownLoadUtils;
import com.tjl.yangxixi.download.OnFileDownListener;
import com.tjl.yangxixi.download.Request;
import com.tjl.yangxixi.utils.DownLoadPathUtils;
import com.tjl.yangxixi.utils.FileUtils;
import com.tjl.yangxixi.utils.NotificationUtils;
import com.tjl.yangxixi.utils.ToolsUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

/**
 * app升级的服务
 * @author HLD
 */
public class UpdateAppService extends Service {
	private AppInfoBean bean;
	private DownLoadUtils download;
	private int notifyId=1000015;
	private boolean isStarted=false;
	@Override
	public void onCreate() {
		super.onCreate();
		download=new DownLoadUtils(this);
		download.setOnFileDownListener(downloadListener);

	}

//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		Object o=intent.getSerializableExtra("data");
//		if(o!=null&&o instanceof AppInfoBean&&!isStarted){
//			NotificationUtils.serviceStartForeground(this, notifyId, new Intent(this,LoginActivity.class), R.drawable.ic_launcher, getString(R.string.txt_notirfy_updateapp_hide), getString(R.string.txt_notirfy_updateapp_title),getString(R.string.txt_notirfy_updateapp_msg));
//			bean=(AppInfoBean) o;
//			//开始下载
//			isStarted=true;
//			Toast.makeText(this, ""+getString(R.string.txt_updateing), Toast.LENGTH_LONG).show();
//			download.downLoad(bean.getLoadAddr(), ""+DownLoadPathUtils.getMarketApkFilePath(), bean.getPackgeSize(), true, 1);
//		}
//		return super.onStartCommand(intent, flags, startId);
//	}


	private OnFileDownListener downloadListener=new OnFileDownListener() {

		@Override
		public void onStatusChanage(int status, int tag) {

		}
		@Override
		public void onStart(long total, long current, double speed, int tag) {

		}
		@Override
		public void onLoading(long total, long current, double speed, int tag) {
		}
		@Override
		public void onFailure(Exception error, String msg, int tag) {
			handler.sendMessage(handler.obtainMessage());
			stopSelf();
		}
		@Override
		public void onSuccess(Request request, int tag) {
			ToolsUtils.startDefInstallApk(UpdateAppService.this, FileUtils.getSDCardRoot()+""+DownLoadPathUtils.getMarketApkFilePath());
			stopSelf();
		}
	};
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			Toast.makeText(UpdateAppService.this, "下载失败", Toast.LENGTH_LONG).show();
		};
	};

//	public void onDestroy() {
//		super.onDestroy();
//		NotificationUtils.stopServiceForeground(this, true);
//	};


	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}