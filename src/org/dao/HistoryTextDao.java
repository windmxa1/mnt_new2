package org.dao;

import java.util.List;

import org.model.HistoryText;

public interface HistoryTextDao {
	/**
	 * 通过items id 获取值，通过设备的属性名获取对应属性值
	 */
	public HistoryText getItemsValueByItemId(Long itemid);
	/**
	 * 通过设备id获取设备告警信息
	 */
	public 	HistoryText getDeviceAlarmByHostId(Long hostid);
}
