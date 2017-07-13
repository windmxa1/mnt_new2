package org.dao;

import java.util.List;
import java.util.Map;

import org.model.DcEvents;

public interface DeviceInfoDao {
	/**
	 *  通过同一类型的设备列表获取设备信息：
	 */
	public List<Map<String, String>> getDeviceInfo(List<Object[]> someTypeList,String group); 
	/**
	 * 获取门禁信息列表
	 */
	public List<DcEvents> getDCEvents(String DcHost); //
	/**
	 * 获取该设备的信息列表
	 */
	public Map<String, String> getInfoMap(Long hostid,String group) ;
}
