package org.dao;

import java.util.List;
import java.util.Map;

import org.model.HostInventory;

public interface HostInventoryDao {
	/**
	 * 获取所有设备的主机资产记录
	 */
	public List<HostInventory> getHostInventoryList();

	/**
	 * 获取该设备的主机资产记录
	 */
	public HostInventory getHostInventory(Long hostid);

//	/**
//	 * 获取该设备的主机资产记录
//	 */
//	public Map<String, String> getMapHostInvent(Long hostid);

}
