package org.dao;

import java.util.List;

import org.model.Hosts;
import org.model.Items;

public interface ItemsDao {
	/**
	 * 通过设备id获取指定ip地址的全部item项
	 */
	public List getItemsByHostid(Long hostId);
	/**
	 * 通过监控项ID找到单个items
	 */
	public Items getItemByItemid(Long itemid);
	/**
	 * 通过 属于的主机的id,item的名字 直接获取item对象（在获取10次最新cpu数据时使用）
	 * @param name
	 * @return
	 */
	public Items getItemByName(Long hostid,String name);
}
