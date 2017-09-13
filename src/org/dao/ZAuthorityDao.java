package org.dao;

import java.util.List;

import org.model.ZAuthority;

public interface ZAuthorityDao {
	public List<ZAuthority> getAList();
	/**
	 * 通过 action 字符串 获取 权限id
	 * @param action
	 * @return
	 */
	public int getIdByAction(String action);
	/**
	 * 通过Id获取Action名
	 */
	public String getActionById(Integer Id);
	/**
	 * 获取角色对应权限列表
	 */
	public List<ZAuthority> getAList(Integer roleId); 
	/**
	 * 获取角色对应的权限列表名称
	 */
	public List<String> getAliasList(Integer roleId);
	/**
	 * 获取角色对应的Action
	 */
	public List<String> getActionListByUser(Integer userId);
	
}
