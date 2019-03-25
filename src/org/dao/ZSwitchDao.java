package org.dao;

import java.util.List;

import org.view.VSwitchAlarmId;
import org.view.VSwitchId;

public interface ZSwitchDao {
	/**
	 * 获取开关量状态列表
	 */
	public List<VSwitchId> getSwitchList(Integer start, Integer limit);

	/**
	 * 获取异常状态的设备(设备状态值为ERROR&&未被确认&&布防状态)
	 */
	public List<String> getAlarmDevice();
	
	/**
	 * 获取开关量设备名称
	 */
	public String getSwitchName(Integer id);
	/**
	 * 获取开关量设备名称列表
	 */
	public List getSwitchNameList();
	
	/**
	 * 一键确认该开关量的所有告警
	 */
	public boolean AckAlarm(Integer id);

	/**
	 * 设备布防/撤防
	 */
	public boolean updateAvailable(Integer available, Integer id);

	/**
	 * 获取单个开关量告警记录列表
	 */
	public List<VSwitchAlarmId> getSwitchAlarmHistory(Integer start,
			Integer limit, Integer id);
	/**
	 * 获取单个开关量告警记录总数
	 */
	public Long getSwitchAlarmCount(Integer id);
	
	/**
	 * 获取所有开关量设备的告警记录列表
	 */
	public List<VSwitchAlarmId> getSwitchAlarmHistory(Integer start,
			Integer limit,String name);
	/**
	 * 获取所有开关量告警记录总数
	 */
	public Long getSwitchAlarmCount(String name);
	
	/**
	 * 获取指定时间段的开关量告警记录列表
	 */
	public List<VSwitchAlarmId> getSwitchAlarmHistory(Integer start,
			Integer limit, String start_time, String end_time,String name);
	/**
	 * 获取指定时间段的告警记录总数
	 */
	public Long getSwitchAlarmCount(String start_time, String end_time,String name);

	/**
	 * 删除指定时间段的数据
	 */
	public boolean deleteSwitchAlarm(Long start_clock, Long end_clock,String name);
	/**
	 * 删除所有数据
	 */
	boolean deleteAll();
}
