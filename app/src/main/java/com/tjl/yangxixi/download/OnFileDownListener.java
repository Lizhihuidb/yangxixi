package com.tjl.yangxixi.download;

public interface OnFileDownListener {
	/**
	 * ��ʼ����
	 * @param total
	 * @param current
	 * @param speed
	 * @param tag
	 */
	void onStart(long total, long current, double speed, int tag);
	
	/**
	 * ������
	 * @param total �ļ���С
	 * @param current ���ؽ���
	 * @param speed �����ٶ�
	 * @param tag ���
	 */
	void onLoading(long total, long current, double speed, int tag);
	/**
	 * ���سɹ�
	 */
	void onSuccess(Request request, int tag);
	/**
	 * ����ʧ��
	 */
	void onFailure(Exception error, String msg, int tag);
	/**
	 * ״̬�����ı�
	 */
	void onStatusChanage(int status, int tag);
}