package org.dao;

import java.util.List;

import org.model.AlarmTab;

/**
 * 暂用于记录摄像头的告警事件
 * @author Marshall
 */
public interface AlarmTabDao {
	/**
	 * 插入
	 */
	public boolean insert(AlarmTab alarmTab);
	/**
	 * 查看所有异常
	 */
	public List<AlarmTab> getAlarmTab();
	/**
	 * 获取未处理的异常表格
	 */
	public List<AlarmTab> getUndealTab();
	/**
	 * 获取已处理的异常表格
	 */
	public List<AlarmTab> getDealTab();
	
	/**
	 * 获得单个设备（摄像头）的未处理的告警数目
	 */
	public Long countAlarm(String host);
	/**
	 * 获得该设备 当前月份的告警数目,暂用于查找摄像头的异常告警
	 */
	public Long countCurrentAlarm(Long hostid);
	
}
