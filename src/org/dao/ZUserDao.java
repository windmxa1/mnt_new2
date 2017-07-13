package org.dao;

import java.util.List;

import org.model.ZUser;

public interface ZUserDao {
	/**
	 * 判断用户名是否可用
	 * @return true-可用   false-不可用
	 */
	public boolean checkUsername(String username);
	/**
	 * 注册
	 */
	public boolean addUser(ZUser user);
	/**
	 * 登录
	 */
	public ZUser login(String username, String password);
	/**
	 * 删除用户（传入用户对于的id）
	 */
	public boolean deleteUser(int id);
	/**
	 * 更改用户密码
	 */
	public boolean changePassword(int userId, String newPwd);
	/**
	 * 获取用户列表
	 */
	public List<ZUser> getUserList();
	/**
	 * 获取当前最大的id
	 * @return
	 */
	public int getUserMaxid();
	/**
	 * 通过username获取id
	 * @param username
	 * @return
	 */
	public int getIdByUsername(String username);
	/**
	 * 修改名字
	 * @param userId
	 * @param name
	 * @return
	 */
	public boolean changeName(int userId,String name);
	/**
	 * 检测用户id userId 是否合法
	 * @param id
	 * @return true 合法 false 无效
	 */
	public boolean checkId(int id);
	
	/**
	 * 根据用户名密码查找用户
	 */
	public ZUser getOneUser(String username,String password);
	/**
	 * 获取用户列表，除了自己
	 */
	public List<ZUser> getList(Integer userid,Integer start, Integer limit);
	/**
	 * 获取总用户数
	 */
	public Long getCount();
}
