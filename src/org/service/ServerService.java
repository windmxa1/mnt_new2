package org.service;

import java.util.ArrayList;
import java.util.List;

public interface ServerService {
	/**
	 * 获取服务器主机所有的信息
	 * @return
	 */
	public List getServerInfo();
	
	/**
	 * 获取服务器主机CPU信息
	 * @return
	 */
	public List getServerCpuInfo();
	
	/**
	 * 获取近10次CPU信息
	 * @return
	 */
	public List getServerCpu10Info();
	
	/**
	 * 获取服务器主机内存信息
	 * @return
	 */
	public List getServerMemInfo();
	
	/**
	 * 获取服务器主机网络信息
	 * @return
	 */
	public List getServerNetInfo();
	
	/**
	 * 获取服务器主机磁盘信息
	 * @return
	 */
	public List getServerDiskInfo();
	/**
	 * 获取服务器其他信息
	 * @return
	 */
	public List getServerOtherInfo();
}
