package org.dao;

import java.util.List;

import org.model.ZConnectState;
import org.model.ZMessage;
import org.view.VMessageId;

public interface ZMessDao {
	/**
	 * 统计两个人之间的消息总数
	 */
	public Long getCount(Integer user_id,Integer friend_id);
	/**
	 * 查询最新的未显示的数据
	 * @param isSender 
	 * 			true则查找作为发送者未显示的最新消息
	 * 			false则查找作为接收者未显示的最新消息
	 */
	public List<ZMessage> getLatest(Integer user_id,Integer friend_id,Integer start,Integer limit,boolean isSender);
	
	/**
	 * 查询消息（默认显示最新的5条）
	 */
	public List<VMessageId> getLatest1(Integer user_id,Integer friend_id,Integer start,Integer limit);
	
	/**
	 * 查询并更新最新数据
	 */
	public Integer checkLastest(Integer user_id,Integer friend_id);
	/**
	 * 新增数据/修改数据,注：修改数据时需要id，以及所有不允许为空的字段的值
	 */
	public Integer save(ZMessage msg);
	/**
	 * 通过id找出对应数据
	 */
	public ZMessage findById(int id);
	/**
	 * 建立连接，如果有记录则更新，否则增加一条连接记录
	 */
	public Integer insertConnect(ZConnectState connectState);
	/**
	 * 查询连接
	 */
	public ZConnectState getConnectState(Integer sender_id,Integer receiver_id);
	/**
	 * 获取连接总数
	 */
	public Integer getConnectCount(Integer sender_id,Integer receiver_id);
	/**
	 * 在服务器启动的时候将全部连接数都重置为0
	 */
	public boolean resetConnectCount();
	/**
	 * 删除两人之间的所有历史记录
	 */
	public boolean deleteAllChatHistory(Integer sender_id,Integer receiver_id);
}
