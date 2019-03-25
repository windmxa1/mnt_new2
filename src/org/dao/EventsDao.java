package org.dao;

import java.util.List;

import org.view.VEventsId;

public interface EventsDao {
	/**
	 * 获取总数
	 */
	Long getCount(String name,String type);

	/**
	 * 获取事件列表
	 */
	List<VEventsId> getList(Integer start, Integer limit,String name,String type);

	/**
	 * 获取事件列表（按时间）
	 */
	List<VEventsId> getListByClock(Integer start, Integer limit,
			Long start_clock, Long end_clock,String name,String type);

	/**
	 * 获取总数（按时间）
	 */
	Long getCountByClock(Long start_clock, Long end_clock,String name,String type);

	/**
	 * 删除事件（按时间）
	 */
	boolean deleteByClock(Long start_clock, Long end_clock,String name,String type);
	/**
	 * 删除所有数据
	 */
	boolean deleteAll();

}
