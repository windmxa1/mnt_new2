package org.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZArmingCaseDao;
import org.dao.ZAuthorityDao;
import org.dao.ZOperationDao;
import org.dao.ZRoleDao;
import org.dao.ZUserDao;
import org.dao.ZUserRoleDao;
import org.dao.imp.ZArmingCaseDaoImp;
import org.dao.imp.ZAuthorityDaoImp;
import org.dao.imp.ZOperationDaoImp;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.dao.imp.ZUserRoleDaoImp;
import org.model.ZArmingCase;
import org.model.ZUser;
import org.model.ZUserRole;
import org.tool.Constans;
import org.tool.R;
import org.view.VUserId;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.media.sound.DataPusher;

public class ZUserAction extends ActionSupport {
	private Integer id;
	private String username;
	private String password;
	private String name;
	private Integer roleid;
	private Integer start;
	private Integer limit;
	private Map<String, Object> data;
	private Object result; // json 返回值
	private ZUserDao uDao;
	private int[] hour;
	private int[] switchId;
	private Integer caseId;
	private int chosen = 0;// 已弃用
	private String role;
	private Boolean isAutoLogout;// 自动注销是否启动
	private Long interval = 5 * 60L;// 自动注销时间间隔

	/**
	 * 自动注销
	 */
	public String autoLogout() {
		Constans.isAutoLogout = !isAutoLogout;
		Constans.interval = interval;
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser u = (ZUser) session.get("user");
		ZOperationDao oDao = new ZOperationDaoImp();
		oDao.saveOrUpdate(u.getId(), System.currentTimeMillis() / 1000);

		data = new HashMap<>();
		data.put("isAutoLogout", Constans.isAutoLogout);
		data.put("interval", Constans.interval);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	public String login() throws Exception {
		uDao = new ZUserDaoImp();
		ZUser u = uDao.getUser(username, password);
		if (u != null) {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			ZAuthorityDao zaDao = new ZAuthorityDaoImp();
			/********* 登录成功后更新最新操作时间 *********/
			ZOperationDao oDao = new ZOperationDaoImp();
			oDao.saveOrUpdate(u.getId(), System.currentTimeMillis() / 1000);
			/***********************************/
			List<String> list = zaDao.getActionListByUser(u.getId());
			session.put("user", u);
			session.put("aList", list);
			data = new HashMap<>();
			data.put("interval", Constans.interval);
			data.put("isAutoLogout", Constans.isAutoLogout);
			data.put("recordOperate", list.contains("recordOperate"));// 返回录像操作的权限
			result = R.getJson(1, "", data);
			return SUCCESS;
		} else {
			result = R.getJson(0, "用户名或密码错误，请重试", "");
			return SUCCESS;
		}
	}

	public String addUser() {
		uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		if (uDao.getUser(username) == null) {
			ZUser user = new ZUser();
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			Integer roleid = rDao.getIdByRole(role);
			if (uDao.addUser(user, roleid)) {
				result = R.getJson(1, "添加成功", "");
			} else {
				result = R.getJson(0, "添加失败", "");
			}
		} else {
			result = R.getJson(0, "添加失败，用户名重复", "");
		}
		return SUCCESS;
	}

	/**
	 * 修改用户信息
	 */
	public String updateUserInfo() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser u = (ZUser) session.get("user");
		if (id == 1) {
			if (u.getId() != 1) {
				result = R.getJson(0, "禁止修改管理员信息", "");
				return SUCCESS;
			}
			role = "超级管理员";
		}
		uDao = new ZUserDaoImp();
		// 用户名不可修改，密码，姓名，角色可修改
		ZUser user = new ZUser(username, password, name);
		user.setId(id);
		ZUserRoleDao uRoleDao = new ZUserRoleDaoImp();
		ZUserRole userRole = uRoleDao.getUserRole(id);
		if (userRole == null) {
			userRole = new ZUserRole();
			userRole.setUserid(id);
		}
		ZRoleDao rDao = new ZRoleDaoImp();
		userRole.setRoleid(rDao.getIdByRole(role));
		if (uDao.updateUserInfo(user, userRole)) {
			result = R.getJson(1, "修改成功", "");
		} else {
			result = R.getJson(0, "修改失败，请重试", "");
		}
		return SUCCESS;
	}

	/**
	 * 删除用户
	 */
	public String deleteUser() throws Exception {
		uDao = new ZUserDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (id == 1) {
			result = R.getJson(0, "删除失败，不能删除超级管理员", "");
			return SUCCESS;
		}
		if (id == user.getId()) {
			result = R.getJson(0, "删除失败，不能删除自己", "");
			return SUCCESS;
		}
		if (uDao.deleteUser(id)) {
			result = R.getJson(1, "删除成功", "");
		} else {
			result = R.getJson(0, "删除失败", "");
		}
		return SUCCESS;
	}

	/**
	 * 获取用户列表
	 */
	public String getUserList() throws Exception {
		uDao = new ZUserDaoImp();
		List<VUserId> list = uDao.getUserList(start, limit);
		data = new HashMap<>();
		data.put("total", uDao.getCount());
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 获取用户信息
	 */
	public String getUserInfo() throws Exception {
		uDao = new ZUserDaoImp();
		VUserId user = uDao.getUser(id);
		if (user == null) {
			result = R.getJson(0, "加载用户信息失败，请再次单击用户列表", "");
		} else {
			data = new HashMap<>();
			data.put("user", user);
			result = R.getJson(1, "", data);
		}
		return SUCCESS;
	}

	/**
	 * 登出
	 */
	public String logout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		session.clear();
		session1.invalidate();
		result = R.getJson(1, "", "");
		System.out.println("退出登录");
		return SUCCESS;
	}


	/**
	 * 更新自动布防方案
	 */
	public String updateArmingCase() {
		int i = 0;
		String hours = "";
		for (int h : hour) {
			if (i == 0) {
				hours = hours + " " + h;
			} else {
				hours = hours + "," + h;
			}
			i++;
		}
		String switches = "";
		i = 0;
		for (int s : switchId) {
			if (i == 0) {
				switches = switches + " " + s;
			} else {
				switches = switches + "," + s;
			}
			i++;
		}
		try {
			String str = "sh /home/dzj/cps/arming_script/controlArming"
					+ caseId + ".sh";
			str = str + hours + switches;
			// + " "+ caseId;
			// System.out.println(str);
			Runtime.getRuntime().exec(str);
		} catch (IOException e) {
			e.printStackTrace();
			result = R.getJson(0, "操作失败，请重试", "");
			return SUCCESS;
		}
		// 脚本执行成功后，进行DB操作
		ZArmingCase armingCase = new ZArmingCase(hours, switches, chosen);
		armingCase.setId(caseId);
		ZArmingCaseDao caseDao = new ZArmingCaseDaoImp();
		if (caseDao.saveOrUpdate(armingCase) != 0) {
			result = R.getJson(0, "操作失败，请重试", "");
			return SUCCESS;
		}
		result = R.getJson(1, "操作成功", "");
		return SUCCESS;
	}

	/**
	 * 查看布防方案，不传参则显示当前激活的方案
	 */
	public String getArmingCase() {
		ZArmingCaseDao caseDao = new ZArmingCaseDaoImp();
		ZArmingCase armingCase = caseDao.getZalarmCase(caseId);
		data = new HashMap<>();
		data.put("armingCase", armingCase);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 判断当前时间与最后操作时间是否间隔超过5分钟
	 */
	public String outOfTime() throws Exception{
		Long clock = System.currentTimeMillis() / 1000;
		ZOperationDao oDao = new ZOperationDaoImp();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser u = (ZUser) session.get("user");
		if (Constans.isAutoLogout
				&& !oDao.checkOpeartion(u.getId(), clock, Constans.interval)) {// 超过5分钟则提示该信息
			HttpSession session1 = request.getSession();
			session.clear();
			session1.invalidate();
			result = R.getJson(-999, "登录信息已过期，请重新登录", "");
			return SUCCESS;
		}
		result = R.getJson(1, "", "");
		return SUCCESS;
	}

	// —————————————————get&set—————————————————————
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getroleid() {
		return roleid;
	}

	public void setroleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public int[] getHour() {
		return hour;
	}

	public void setHour(int[] hour) {
		this.hour = hour;
	}

	public int[] getSwitchId() {
		return switchId;
	}

	public void setSwitchId(int[] switchId) {
		this.switchId = switchId;
	}

	public int getChosen() {
		return chosen;
	}

	public void setChosen(int chosen) {
		this.chosen = chosen;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsAutoLogout() {
		return isAutoLogout;
	}

	public void setIsAutoLogout(Boolean isAutoLogout) {
		this.isAutoLogout = isAutoLogout;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}
}
