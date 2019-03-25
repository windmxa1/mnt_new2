package org.dao;

import java.util.List;

import org.model.History;

public interface HistoryDao {
	/**
	 * 通过itemid获取history历史记录表中最新的数据value
	 * @param itemid
	 * @return
	 */
	public History getItemsValueByItemId(Long itemid);
	/**
	 * 获取最近10次数据
	 * @return
	 */
	public List getItemsValueByItemId_10time(Long itemid);
}
