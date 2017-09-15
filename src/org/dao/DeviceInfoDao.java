package org.dao;

import java.util.List;
import java.util.Map;

import org.model.ZDcEvents;
import org.view.VDcEvents;
import org.view.VDcEventsId;
import org.view.VItemValueId;

public interface DeviceInfoDao {
	/**
	 * 获取设备相关信息
	 */
	public List<VItemValueId> getHostList(String type,Integer start,Integer limit);
	/**
	 * 获取摄像头IP地址
	 */
	public String getIPCIpByName(String name);
	/**
	 * 获取门禁开关信息列表、同时更新门禁的通知状态
	 */
	public List<VDcEventsId> getDCEvents(String DcHost, Integer start, Integer limit); 
	/**
	 * 读取最新的刷卡消息并更新已读状态，同时还要更新HostConfig表里面门禁的消息通知状态
	 */
	public List<VDcEventsId> readDCEvents();
	/**
	 * 获取当前未处理的门禁事件
	 */
	public List<Object[]> getUnReadDCEvents(); 
	/**
	 * 获取门禁开关信息总数
	 */
	public Long getDCEventsCount(String DcHost); //

	/**
	 * 获取各机房的故障数目和机房名
	 */
	public List<Object[]> getJFError();
	/**
	 * 获取摄像头对应的录像机IP
	 */
	public String getNVRIP(String ipcIP);
	/**
	 * 根据IP获取设备机房信息
	 */
	public Object[] getHKJFByIp(String host);
	/**
	 * 获取海康设备异常设备与机房
	 */
	public List<Object[]> getHKAlarmInfo();
	
}
