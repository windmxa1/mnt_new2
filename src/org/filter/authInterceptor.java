package org.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import org.apache.struts2.ServletActionContext;
import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.ZLogDao;
import org.dao.ZOperationDao;
import org.dao.ZRoleDao;
import org.dao.ZSwitchDao;
import org.dao.ZUserDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ZLogDaoImp;
import org.dao.imp.ZOperationDaoImp;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZSwitchDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.model.ZLog;
import org.model.ZUser;
import org.tool.Constans;
import org.tool.R;
import org.util.Utils;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class authInterceptor extends AbstractInterceptor {
	// @Override
	// public String intercept(ActionInvocation invocation) throws Exception {
	// return invocation.invoke();
	// }
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取接口名
		String actionName = invocation.getInvocationContext().getName();

		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getContextPath(); // 获取项目名

		Map<String, Object> session = ActionContext.getContext().getSession();

		System.out.println(pName
				+ "--"
				+ new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss")
						.format(new Date()) + "--" + actionName);
		ZLogDao lDao = new ZLogDaoImp();

		ZUser u = (ZUser) request.getSession().getAttribute("user");

		ZOperationDao oDao = new ZOperationDaoImp();
		if (!actionName.equals("login")) {
			Long clock = System.currentTimeMillis() / 1000;
			if (Constans.isAutoLogout
					&& !oDao.checkOpeartion(u.getId(), clock, Constans.interval)) {// 如果操作时间间隔大于5分钟，则自动清除用户缓存并给出过期提示
				HttpSession session1 = request.getSession();
				session.clear();
				session1.invalidate();
				ActionContext.getContext().put("result",
						R.getJson(-999, "登录信息已过期，请重新登录", false));
				return Action.ERROR;
			} else {
				oDao.saveOrUpdate(u.getId(), clock);
			}
		}
		if (actionName.equals("getLogList")) {// 不记录日志，但需要权限控制
			// -----权限------
			if (u != null) {
				List<String> list = (List<String>) session.get("aList");
				if (list.contains(actionName)) {
					return invocation.invoke();
				}
				ActionContext.getContext().put("result",
						R.getJson(-900, "操作无权限", false));
				return Action.ERROR;
			} else {
				ActionContext.getContext().put("result",
						R.getJson(-999, "未登录", false));
				return Action.ERROR;
			}

		} else if (actionName.equals("login")) {
			// 要记录日志，但不需要权限
			String username = request.getParameter("username");
			ZLog log = new ZLog(username, actionName,
					new Date().getTime() / 1000);
			lDao.addLog(log);
			return invocation.invoke();
		} else if (actionName.equals("addUser")) {
			// 要优先记录日志，但需要做权限判断
			String username = request.getParameter("username");
			ZLog log = new ZLog(u.getName(), actionName,
					new Date().getTime() / 1000, "添加了新用户：" + username);
			lDao.addLog(log);

			List<String> list = (List<String>) session.get("aList");
			if (list.contains(actionName)) {
				return invocation.invoke();
			}
			ActionContext.getContext().put("result",
					R.getJson(-900, "操作无权限", false));
			return Action.ERROR;
		} else {
			if (u != null) { // 已登录
				List<String> list = (List<String>) session.get("aList");
				// 有权限
				if (list.contains(actionName)) {
					// 记日志
					String msg = "";
					if (actionName.contains("update")
							|| actionName.contains("delete")) {
						if (actionName.contains("User")) {
							ZUserDao uDao = new ZUserDaoImp();
							msg = "对"
									+ uDao.getUserName(Integer.parseInt(request
											.getParameter("id"))) + "用户操作";
						} else if (actionName.contains("Role")) {
							ZRoleDao rDao = new ZRoleDaoImp();
							msg = "对"
									+ rDao.getRoleById(Integer.parseInt(request
											.getParameter("roleid"))) + "角色操作";
						}
					}
					if (actionName.equals("speedArming")) {
						SensorsDao sDao = new SensorsDaoImp();
						Object[] o = sDao.getSensorInfoByNo(Integer
								.parseInt(request.getParameter("sensorIndex")));
						if (request.getParameter("available").equals("0")) {
							msg = o[0] + "" + o[1] + "布防";
						} else {
							msg = o[0] + "" + o[1] + "撤防";
						}
					}
					if (actionName.equals("HKArming")) {
						DeviceInfoDao dDao = new DeviceDaoImp();
						Object[] o = dDao.getHKJFByIp(request
								.getParameter("host"));
						if (request.getParameter("available").equals("0")) {
							msg = o[0] + "" + o[1] + "布防";
						} else {
							msg = o[0] + "" + o[1] + "撤防";
						}
					}
					if (actionName.equals("updateAvailable")) {
						ZSwitchDao sDao = new ZSwitchDaoImp();
						String o = sDao.getSwitchName(Integer.parseInt(request
								.getParameter("id")));
						if (request.getParameter("available").equals("0")) {
							msg = o + "布防";
						} else {
							msg = o + "撤防";
						}
					}
					if (actionName.equals("doorControl")) {
						GroupsDao gDao = new GroupsDaoImp();
						msg = gDao
								.getJFNameByHost(request.getParameter("host"))
								.replace("JF-", "")
								+ "远程开门";
					}
					if (actionName.equals("autoLogout")) {
						if (Constans.isAutoLogout) {
							msg = "关闭自动注销";
						} else {
							msg = "开启自动注销";
						}
					}
					ZLog log = new ZLog(u.getName(), actionName,
							new Date().getTime() / 1000, "" + msg);
					lDao.addLog(log);
					return invocation.invoke();
				}
				// 无权限
				ActionContext.getContext().put("result",
						R.getJson(-900, "操作无权限", false));
				return Action.ERROR;
			} else { // 未登录
				ActionContext.getContext().put("result",
						R.getJson(-999, "未登录", false));

				return Action.ERROR;
			}
		}
	}
}
