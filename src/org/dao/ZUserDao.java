package org.dao;

import java.util.List;

import org.model.ZUser;
import org.model.ZUserRole;
import org.view.VUserId;

public interface ZUserDao {
	/**
	 * 比较当前时间与目标时间
	 */
	public boolean outOfTime(Long time);
	/**
	 * 获取用户
	 */
	public ZUser getUser(String username);
	/**
	 * 获取用户对象
	 */
	public VUserId getUser(int userid);
	/**
	 * 获取用户名称
	 */
	public String getUserName(Integer userid);
	/**
	 * 添加用户以及角色
	 */
	public boolean addUser(ZUser user,int roleid);

	/**
	 * 获取用户
	 */
	public ZUser getUser(String username, String password);

	/**
	 * 删除用户（传入用户对于的id）
	 */
	public boolean deleteUser(int id);

	/**
	 * 更改用户密码
	 */
	public boolean changePassword(int userId, String newPwd);

	/**
	 * 修改用户信息
	 */
	public boolean updateUserInfo(ZUser user, ZUserRole userRole);

	/**
	 * 获取用户列表
	 */
	public List<VUserId> getUserList(Integer start, Integer limit);

	/**
	 * 获取当前最大的id
	 * 
	 * @return
	 */
	public int getUserMaxid();

	/**
	 * 通过username获取id
	 * 
	 * @param username
	 * @return
	 */
	public int getIdByUsername(String username);

	/**
	 * 修改名字
	 * 
	 * @param userId
	 * @param name
	 * @return
	 */
	public boolean changeName(int userId, String name);

	/**
	 * 检测用户id userId 是否合法
	 * 
	 * @param id
	 * @return true 合法 false 无效
	 */
	public boolean checkId(int id);

	/**
	 * 获取用户列表，除了自己
	 */
	public List<ZUser> getList(Integer userid, Integer start, Integer limit);

	/**
	 * 获取总用户数
	 */
	public Long getCount();
}
