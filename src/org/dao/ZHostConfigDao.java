package org.dao;

import java.util.List;

import org.model.ZHostConfig;

public interface ZHostConfigDao {
	/**
	 * 添加或修改
	 */
	public Long saveOrUpdate(ZHostConfig hostConfig);

	/**
	 * 初始化
	 */
	public boolean init();

	/**
	 * 获取单个对象
	 */
	public ZHostConfig getHostConfig(String host);

	/**
	 * 更新海康设备布撤防状态
	 */
	public boolean updateAvailable(String host, Integer available);

	/**
	 * 更新通知状态
	 */
	boolean updateNotice(String[] hostList);
}
