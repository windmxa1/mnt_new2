package org.dao;

import java.util.List;
import java.util.Map;

import org.model.Events;

public interface EventsDao {
	/**
	 * 获取 单个设备的 每一条异常记录
	 */
	public List<Map<String, String>> getErrEventsByHostId(Long hostid); 
	/**
	 * 获取所有类型的单个设备的设备故障记录数,没有时间戳
	 */
	public List<Map<String, String>> getFailureListWithoutTime();
	/**
	 * 获取单种类型的的设备故障记录 包含时间戳
	 */
	public List<Map<String, String>> getDCSingleList(); 
	/**
	 * 获取单种类型的的设备故障记录 包含时间戳
	 */
	public List<Map<String, String>> getIPCSingleList(); 
	/**
	 *  获取单种类型的的设备故障记录 包含时间戳
	 */
	public List<Map<String, String>> getNVRSingleList(); 
	/**
	 *获取该设备当前月份的所有错误统计数目 
	 */
	public Long getTotalErr(Long hostid); 
	/**
	 * 获取该设备当前月份的故障总数
	 */
	public Long getTotalFail(Long hostid);
	/**
	 * 获取 该设备的 每一条故障记录
	 */
	public List<Map<String, String>> getFailEventsByHostId(Long hostid);
	/**
	 * 获取未处理的告警记录,除去录像机的异常记录，不是设备记录，这里不取设备故障记录
	 */
	public List<Events> getUndealList();
	/**
	 * 获取已处理的告警记录,除去录像机的异常记录，不是设备记录，这里不取设备故障记录
	 */
	public List<Events> getDealList();
}
