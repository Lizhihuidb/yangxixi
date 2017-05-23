package com.tjl.yangxixi.utils;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

public class NotificationUtils {
//	/**
//	 * 让service在前台运行
//	 * @param service
//	 * @param notifyId
//	 * @param intent
//	 * @param icon
//	 * @param msg1
//	 * @param msg2
//	 * @param msg3
//	 */
//	@SuppressWarnings("deprecation")
//	public static void serviceStartForeground(Service service,int notifyId,Intent intent,int icon,String msg1,String msg2,String msg3) {
//		Notification notification = new Notification(icon,msg1, System.currentTimeMillis());
//		PendingIntent pintent = PendingIntent.getActivity(service, 0, intent, 0);
//		notification.setLatestEventInfo(service, msg2,msg3, pintent);
//		service.startForeground(notifyId, notification);
//	}
//	@SuppressWarnings("deprecation")
//	public static void serviceStartForegroundToService(Service service,int notifyId,Intent intent,int icon,String msg1,String msg2,String msg3) {
//		Notification notification = new Notification(icon,msg1, System.currentTimeMillis());
//		PendingIntent pintent = PendingIntent.getService(service, 0, intent, 0);
//		notification.setLatestEventInfo(service, msg2,msg3, pintent);
//		service.startForeground(notifyId, notification);
//	}
//	/**
//	 * 取消service的前台运行状态
//	 * @param service
//	 * @param removeNotification
//	 */
//	public static void stopServiceForeground(Service service,boolean removeNotification){
//		service.stopForeground(removeNotification);
//	}

}
