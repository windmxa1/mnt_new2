package org.dao;

import java.util.List;

public interface GroupsDao {
	/**
	 * 通过设备组ID获取设备列表
	 */
	public List<Object[]> getHostListByGroupid(Long groupid); //
	/**
	 *获取组列表 
	 */
	public List getGroupLists();
	/**
	 * 获取该设备对应的组ID列表，可能一个设备不只属于一个组，因此获取出列表再对列表进行遍历判断
	 */
	public List getgroupIdsByhostId(Long hostid);
	/**
	 * 通过组id获取host列表，给网络主机使用的接口
	 * @param groupid
	 * @return
	 */
	public List getHostListByGroupid02(Long groupid);
	/**
	 * 通过组id获取host列表，给监控设备使用的接口
	 * @param groupid
	 * @return
	 */
	public List getHostListByGroupid03(Long groupid);
	/**
	 * 通过组id获取host列表，存活主机，与zabbix列表相同
	 * @param groupid
	 * @return
	 */
	public List getLiveHostByGroupid(Long groupid);
}
