package org.dao;

import java.util.List;

import org.model.ZRole;
import org.model.ZUser;
import org.model.ZUserRole;

public interface ZUserRoleDao {
	/**
	 * 添加用户-角色关系
	 * @return
	 */
	public boolean addUR(int userId,int roleId);
	/**
	 * 删除用户-角色关系
	 * @return
	 */
	public boolean deleteUR(int id);
//	/**
//	 * 通过角色id查找用户
//	 * @param roleId
//	 * @return ZRole
//	 */
//	public int getUByR(int roleId);
	/**
	 * 通过用户id查找角色
	 * @param userId
	 * @return ZUser
	 */
	public int getRByU(int userId);
	/**
	 * 获取关联表
	 * @return
	 */
	public List<ZUserRole> getURList();
	/**
	 * 根据用户Id修改角色
	 * @param role
	 */
	public boolean updateUR(int userId,int roleId);
	/**
	 * 通过用户id 获取表id
	 * @param userId
	 * @return
	 */
	public int getIdByUserid(int userId);
	/**
	 * 通过角色id 获取表id
	 * @param roleId
	 * @return
	 */
	public List getIdByRoleid(int roleId);
	/**
	 * 查询用户-角色关联表是否有 某角色id roleId 的记录
	 * @param roleId
	 * @return
	 */
	public boolean checkRoleid(int roleId);
	
	public boolean checkUserid(int userId);
}
