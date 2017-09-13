package org.dao;

import java.util.List;

import org.model.Services;
import org.view.VServicesId;

public interface ServicesDao {
	/**
	 * 找出第一级服务即代表机房的项
	 */
	public List<VServicesId> getServicesGroup();
	/**
	 * 通过服务id找到对应的服务
	 */
	public Services getServicesById(Long serviceid);
	/**
	 * 找到triggerid对应的服务，找到某个设备下的属性，设备故障或者告警
	 */
	public Services getServiceByTriggerId(Long triggerid);
}
