package org.dao;

import java.util.List;

import org.model.Groups;
import org.view.VHostGroupId;

public interface GroupsDao {
	/**
	 * 获取组列表
	 */
	public List<Object[]> getGroupLists();

	/**
	 * 获取该设备对应的组ID列表，可能一个设备不只属于一个组，因此获取出列表再对列表进行遍历判断
	 */
	public List<Long> getgroupIdsByhostId(Long hostid);

	/**
	 * 通过组id获取host列表，给网络主机使用的接口
	 * 
	 * @param groupid
	 * @return
	 */
	public List<Object[]> getHostListByGroupid02(Long groupid);

	/**
	 * 通过组id获取host列表，给监控设备使用的接口
	 * 
	 * @param groupid
	 * @return
	 */
	public List<Object[]> getHostListByGroupid03(Long groupid);

	/**
	 * 通过IP获取设备间名称
	 */
	public String getJFNameByHost(String host);
	
	/**
	 * 通过组id获取host列表，存活主机，与zabbix列表相同
	 */
	public List<VHostGroupId> getLiveHostByGroupid(Long groupid,Integer start,Integer limit);
	/**
	 * 获取设备总数
	 */
	public Long getHostCountByGroupid(Long groupid);
	/**
	 * 获取设备间列表
	 */
	public List<String> getGroupList();
}
