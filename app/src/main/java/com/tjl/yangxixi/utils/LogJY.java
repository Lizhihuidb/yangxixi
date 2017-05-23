package com.tjl.yangxixi.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tjl.yangxixi.params.ConfigPatams;


/**
 * 打印日志类
 * @author tao
 *
 */
public class LogJY {
	private static final int error=-1;

	private static int doLog(String name,String tag,String msg, Throwable tr) {
		try {
			Class<?> cls=Class.forName("android.util.Log");
			Method m = cls.getMethod(name, String.class,String.class,Throwable.class);
			m.invoke(null,tag, msg,tr);
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return error;
	}

	public static int v(String tag, String msg) {
		if(ConfigPatams.LogOpen){
//    		return Log.v(tag, msg);
			return doLog("v", tag, msg,null);
		}else{
			return error;
		}
	}
	public static int v(String tag, String msg, Throwable tr) {
		if(ConfigPatams.LogOpen){
//    		return Log.v(tag, msg,tr);
			return doLog("v", tag, msg,tr);
		}else{
			return error;
		}
	}
	public static int d(String tag, String msg) {
		if(ConfigPatams.LogOpen){
//    		return Log.d(tag, msg);
			return doLog("d", tag, msg,null);
		}else{
			return error;
		}
	}

	public static int d(String tag, String msg, Throwable tr) {
		if(ConfigPatams.LogOpen){
			return doLog("d", tag, msg,tr);
		}else{
			return error;
		}
	}
	public static int i(String tag, String msg) {
		if(ConfigPatams.LogOpen){
			return doLog("i", tag, msg,null);
		}else{
			return error;
		}
	}
	public static int i(String tag, String msg, Throwable tr) {
		if(ConfigPatams.LogOpen){
			return doLog("i", tag, msg,tr);
		}else{
			return error;
		}
	}
	public static int w(String tag, String msg) {
		if(ConfigPatams.LogOpen){
			return doLog("w", tag, msg,null);
		}else{
			return error;
		}
	}
	public static int w(String tag, String msg, Throwable tr) {
		if(ConfigPatams.LogOpen){
			return doLog("w", tag, msg,tr);
		}else{
			return error;
		}
	}
	public static int w(String tag, Throwable tr) {
		if(ConfigPatams.LogOpen){
			return doLog("w", tag, null,tr);
		}else{
			return error;
		}
	}
	public static int e(String tag, String msg) {
		if(ConfigPatams.LogOpen){
			return doLog("e", tag, msg,null);
		}else{
			return error;
		}
	}
	public static int e(String tag, String msg, Throwable tr) {
		if(ConfigPatams.LogOpen){
			return doLog("e", tag, msg,tr);
		}else{
			return error;
		}
	}
}
