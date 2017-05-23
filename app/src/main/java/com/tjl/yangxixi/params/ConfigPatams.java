package com.tjl.yangxixi.params;

public class ConfigPatams {
	/**
	 * log的开关
	 * (发布软件前必须关掉)
	 */
	public static final boolean LogOpen=true;

	/**
	 * 文件的本地目录
	 */
	private static final String baseFilePath="/yds/";
	public static final String marketFilePath=baseFilePath+"market/";
	public static final String apkFilePath=baseFilePath+"market/downloads/";//文件的基本位置
	public static final String apkExtension=".apk";//文件扩展名

	public static final String marketApkFilePath=marketFilePath+"market.apk";//更新时目录
}
