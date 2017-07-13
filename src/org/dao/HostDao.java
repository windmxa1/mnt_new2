package org.dao;

import java.util.List;

import org.model.Hosts;
import org.model.ZUser;
import org.view.VHostGroup;

public interface HostDao {
	/**
	 * 获取所有设备列表
	 */
	public List<Hosts> getHostList();	
	/**
	 * 通过设备id获取设备类型
	 */
	public void getTypenameByHostid();	//
	/**
	 * 通过设备id找到该设备
	 */
	public Hosts getHostByHostid(Long hostid);
	/**
	 * 通过设备ip找到该设备，暂时用于找到录像机
	 */
	public Hosts getHostByHostip(String nvrHost);
	/**
	 * 获取所有存活的主机列表————视图形
	 * @return
	 */
	public List getHostLive();
}
