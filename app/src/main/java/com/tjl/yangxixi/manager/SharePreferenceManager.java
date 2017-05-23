package com.tjl.yangxixi.manager;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * 操作sharepreference类
 * @author tao
 *
 */
public class SharePreferenceManager {
	private static final String NAME_BASE="HaoHuiShop";
	private static final int MODE_BASE=20013001;

	public static void saveIntPreference(Context ctx,String key,int value){
		getBasePreferences(ctx).edit().putInt(key, value).commit();
	}
	public static int getIntPreference(Context ctx,String key){
		return getBasePreferences(ctx).getInt(key, 0);
	}
	public static void saveStringPreference(Context ctx,String key,String value){
		getBasePreferences(ctx).edit().putString(key, value).commit();
	}

	public static String getStringPreference(Context ctx,String key){
		return getBasePreferences(ctx).getString(key, null);
	}

	public static String getStringPreference(Context ctx,String key,String def){
		return getBasePreferences(ctx).getString(key, def);
	}

	public static long getLongPreference(Context ctx,String key){
		return getBasePreferences(ctx).getLong(key, 0);
	}

	public static void saveLongPreference(Context ctx,String key,long value){
		getBasePreferences(ctx).edit().putLong(key, value).commit();
	}
	public static void saveBooleanPreference(Context ctx,String key,boolean value){
		getBasePreferences(ctx).edit().putBoolean(key, value).commit();
	}
	public static boolean getBooleanPreference(Context ctx,String key){
		return getBasePreferences(ctx).getBoolean(key, true);
	}
	public static boolean checkExistPreference(Context ctx,String key){
		return getBasePreferences(ctx).contains(key);
	}
	public static void removeKeyPreference(Context ctx,String key){
		getBasePreferences(ctx).edit().remove(key).commit();
	}
	private static SharedPreferences getBasePreferences(Context ctx){
		return ctx.getSharedPreferences(NAME_BASE, MODE_BASE);
	}
}