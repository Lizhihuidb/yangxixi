package com.tjl.yangxixi.utils;

import java.io.File;

import com.tjl.yangxixi.bean.AppInfoBean;
import com.tjl.yangxixi.params.ConfigPatams;


public class DownLoadPathUtils {

	/**
	 * 获取下载存放的位置
	 * @param bean ApkInfoBean
	 * @return
	 */
	public static String getFilePath(AppInfoBean bean){
		return ConfigPatams.apkFilePath+""+bean.getPackgeName()+""+bean.getVersionCode()+ConfigPatams.apkExtension;
	}
	public static String getBaseDownloadFilePath(){
		return ConfigPatams.apkFilePath;
	}
	/**
	 * 获取下载时文件的大小,在下载前使用，判断是否断点续传
	 * @param bean
	 * @return
	 */
	public static long getFileSize(AppInfoBean bean){
		File f=new File(FileUtils.getSDCardRoot()+getFilePath(bean));
		if(f.exists()){
			return f.length();
		}
		return 0;
	}
	public static File getApkFile(AppInfoBean bean){
		File f=new File(FileUtils.getSDCardRoot()+getFilePath(bean));
		return f;
	}

	/**
	 * app更新时的目录
	 * @return
	 */
	public static String getMarketApkFilePath(){
		return FileUtils.getSDCardRoot()+ConfigPatams.marketApkFilePath;
	}
}