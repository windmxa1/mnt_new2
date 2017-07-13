package org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ClientService {
	/**
	 * 获取客户端主机所有的信息
	 * @return
	 */
	public List getClientInfo();
	
	/**
	 * 获取客户端主机CPU信息
	 * @return
	 */
	public List getClientCpuInfo();
	
	/**
	 * 获取近10次CPU信息
	 * @return
	 */
	public Map getClientCpu10Info();
	
	/**
	 * 获取客户端主机内存信息
	 * @return
	 */
	public List getClientMemInfo();
	/**
	 * 获取客户端主机内存信息，修改版本
	 * @return
	 */
	public List getClientMemInfo02();
	/**
	 * 获取客户端主机网络信息
	 * @return
	 */
	public List getClientNetInfo();
	
	/**
	 * 获取客户端主机磁盘信息
	 * @return
	 */
	public List getClientDiskInfo();
	/**
	 * 获取客户端其他信息
	 * @return
	 */
	public List getClientOtherInfo();
}
