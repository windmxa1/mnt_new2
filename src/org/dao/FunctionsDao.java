package org.dao;

import java.util.List;

import org.model.Functions;

public interface FunctionsDao {
//	public List<Functions> getErrorFunctions(Long itemid);//通过item的id找到对应function
	/**
	 * 通过触发器ID获取 Functions对象
	 */
	public Functions getFunctionByTrigger(Long triggerid);
	/**
	 * 通过监控项ID获取 Functions对象
	 */
	public Functions getFunctionByItem(Long itemid);
}
