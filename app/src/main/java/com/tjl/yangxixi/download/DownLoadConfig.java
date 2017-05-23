package com.tjl.yangxixi.download;

public interface DownLoadConfig {
	public static final int STATUS_DOWN_WAIT=1;//等待下载
	public static final int STATUS_DOWN_DOING=2;//正在下载
	public static final int STATUS_DOWN_PAUSE=3;//下载暂停
	public static final int STATUS_DOWN_FAILURE=4;//下载失败
	public static final int STATUS_DOWN_SUCCESS=5;//下载成功
}
