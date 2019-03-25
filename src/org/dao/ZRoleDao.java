package org.dao;

import java.util.List;

import org.model.ZRole;

public interface ZRoleDao {
	/**
	 * 添加角色，并添加角色拥有的权限
	 * @return 
	 */
	public boolean addRole(String role,int[] authorityId);
	/**
	 * 检测角色名是否可用
	 * @param role
	 * @return
	 */
	public boolean checkRole(String role);
	/**
	 * 删除角色,同时删除角色权限关联，用户角色关联
	 * @return
	 */
	public boolean deleteRole(int id);
	/**
	 * 获取角色列表
	 * @return
	 */
	public List<ZRole> getRoleList();
	/**
	 * 获取角色列表
	 * @return
	 */
	public List<String> getRoleNameList();
	/**
	 * 获取角色最大的id
	 * @return
	 */
	public int getRoleMaxid();
	/**
	 * 通过role获取id
	 * @param role
	 * @return
	 */
	public Integer getIdByRole(String role);
	/**
	 * 通过id获取role
	 * @param roleId
	 * @return
	 */
	public String getRoleById(int roleId);
	/**
	 * 检查id是否合法
	 * @param id
	 * @return true 合法 flase 无效
	 */
	public boolean checkId(int id);
}
