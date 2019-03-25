package org.dao;

import org.model.ZConnectCtl;


public interface ZConnectCtlDao {
	/**
	 * 建立连接
	 * @param type 0为进出人员
	 */
	public Boolean saveOrUpdate(Integer userid,Integer type,String threadId);
	/**
	 * 查询连接
	 * @param type 0为进出人员
	 */
	public ZConnectCtl getConnect(Integer userid,Integer type);
	/**
	 * 获取当前连接的线程Id 
	 * @param type 0为进出人员
	 */
	public String getThreadId(Integer userid,Integer type);
	
	/**
	 * 在服务器启动的时候删除所有长轮询连接记录
	 */
	public boolean deleteAll();
}
