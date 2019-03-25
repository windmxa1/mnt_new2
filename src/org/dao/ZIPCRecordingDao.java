package org.dao;

import java.util.List;


public interface ZIPCRecordingDao {
	/**
	 * 修改录像状态
	 */
	public Boolean updateRecordStatus(String host,Integer isRecording);
	/**
	 * 初始化
	 */
	public Boolean init ();
	/**
	 * 列出正在录像的摄像头名称
	 */
	public List<String> getRecordingList();
	/**
	 * 重启客户端时调用
	 */
	public Boolean init1 ();
}
