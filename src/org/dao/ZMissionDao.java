package org.dao;

import java.util.List;

import org.model.ZMission;

public interface ZMissionDao {
	/**
	 * 发布任务
	 * 
	 * @param m
	 * @return
	 */
	public boolean addMission(ZMission m);

	/**
	 * 列出未读的任务，key为0则uid为发送人，为1则uid为接收人
	 * 
	 * @param uid
	 * @param key
	 * @return
	 */
	public List<ZMission> getUnReadMission(Integer uid, int key);

	/**
	 * 列出未确认的任务，key为0则uid为发送人，为1则uid为接收人
	 * 
	 * @param uid
	 * @param key
	 * @return
	 */
	public List<ZMission> getUnConfirmMission(Integer uid, int key,
			Integer start, Integer limit);

	/**
	 * 统计未确认的任务的总数,key为0则uid为发送人，为1则uid为接收人
	 */
	public Long getUnConfirmMissionCount(Integer uid, int key);
	

	/**
	 * 列出未完成的任务，key为0则uid为发送人，为1则uid为接收人
	 * 
	 * @param uid
	 * @param key
	 * @return
	 */
	public List<ZMission> getUnCompleteMission(Integer uid, int key,
			Integer start, Integer limit);

	/**
	 * 统计未完成的任务的总数 ,key为0则uid为发送人，为1则uid为接收人
	 */
	public Long getUnCompleteMissionCount(Integer uid, int key);
	
	/**
	 * 列出所有的任务清单，key为0则uid为发送人，为1则uid为接收人
	 */
	public List<ZMission> getAllMission(Integer uid, int key, Integer start,
			Integer limit);
	
	/**
	 * 统计任务的总数目，key为0则uid为发送人，为1则uid为接收人
	 */
	public Long getAllMissionCount(Integer uid, int key);

	/**
	 * 检测是否有未读的任务清单，有返回清单个数，用count
	 * 
	 * @param receive_id
	 * @return
	 */
	public long checkLastestMission(Integer receive_id);

	/**
	 * 读任务，并修改为已读
	 * 
	 * @param receive_id
	 * @return
	 */
	public boolean readMission(Integer receive_id);
	/**
	 * 完成任务
	 */
	public boolean completeMission(Integer[] id);
	/**
	 * 确认任务完成
	 */
	public boolean confirmMission(Integer[] id);
	/**
	 * 取消任务
	 */
	public boolean cancelMission(Integer id,String cancelReason);
}