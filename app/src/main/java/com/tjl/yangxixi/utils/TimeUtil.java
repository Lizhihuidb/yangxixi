package com.tjl.yangxixi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String TIME = "06:00:00--08:00:00";
	public static int SPANTIME = 2;
	public static int SPANDAY = 3;

	/**
	 * @return解析当前时间为date
	 */
	public static Date parseTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @return格式化当前日期和时间为字符串
	 */
	public static String mCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
		String currenttime = df.format(new Date());
		return currenttime;
	}
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss");
		String currenttime = df.format(new Date());
		return currenttime;
	}
	/**
	 * @return格式化当前日期为字符串
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currenttime = df.format(new Date());
		return currenttime.toString().trim();
	}
	/**
	 * @return格式化当前日期为字符串
	 */
	public static String getYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String currenttime = df.format(new Date());
		return currenttime.toString().trim();
	}



	public static int getTime(String time) {
		String[] times = time.split("-");
		String t = times[0]+times[1]+times[2];
		return Integer.valueOf(t);
	}

}
