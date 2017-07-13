package org.dao;

import java.util.List;

import org.model.ZAuthority;
import org.model.ZRole;
import org.model.ZRoleAuthority;

public interface ZRoleAuthorityDao {
	/**
	 * 添加角色-权限关系
	 * @param roleId
	 * @param AuthorityId
	 * @return
	 */
	public boolean addRA(int roleId,int AuthorityId);
//	/**
//	 * 删除角色-权限关系
//	 * @param id
//	 * @return
//	 */
//	public boolean deleteRA(int id);
	/**
	 * 通过角色id 获取对应的权限id
	 * @param roleId
	 * @return
	 */
	public List getAListByR(int roleId);
	/**
	 * 获取关联表
	 * @return
	 */
	public List<ZRoleAuthority> getRAList();
	/**
	 * 通过id删除角色-权限关联表
	 * @param id
	 * @return
	 */
	public boolean deleteRA(int id);
	/**
	 * 通过角色id roleId 获取对应表id列表
	 * @param roleId
	 * @return
	 */
	public List getIdListByR(int roleId);
}
