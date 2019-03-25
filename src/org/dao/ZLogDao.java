package org.dao;

import java.util.List;

import org.model.ZLog;
import org.view.VLogId;

public interface ZLogDao {
	/**
	 * 1.添加日志信息
	 * 
	 * @param l
	 * @return
	 */
	public boolean addLog(ZLog l);

	/**
	 * 2.获取日志列表
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<VLogId> getLogList(Integer start, Integer limit);

	/**
	 * 3.获取总数
	 * 
	 * @return
	 */
	public long getCount();

	/**
	 * 4.获取指定时间段的数据
	 */
	public List<VLogId> getLogList(Integer start, Integer limit,
			String start_time, String end_time);

	/**
	 * 5.获取指定时间段的数据的数目
	 */
	public Long getLogCount(String start_time, String end_time);

	/**
	 * 6.删除指定时间段的数据
	 */
	public boolean deleteLog(Long start_clock, Long end_clock);
	/**
	 * 删除所有数据
	 */
	public boolean deleteAll();
	
	/**
	 * 7.获取所有日志
	 */
	public List<VLogId> getAllLog();
}
