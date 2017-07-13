package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.ZMessDao;
import org.dao.ZUserDao;
import org.dao.imp.ZMessDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.model.ZConnectState;
import org.model.ZMessage;
import org.model.ZUser;
import org.util.MapMessage;
import org.util.Utils;
import org.view.VMessageId;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZChatAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private Object result;
	private HttpServletResponse response = null;
	private HttpServletRequest request = null;
	private static final long serialVersionUID = 1L;
	private ZMessDao mDao;
	private Map<String, String> map;

	// /**
	// * 登录
	// */
	// public String login() {
	// String username = request.getParameter("username");
	// String password = request.getParameter("password");
	// ZUserDao uDao = new ZUserDaoImp();
	// ZUser user = uDao.getOneUser(username, password);
	// if (user != null) {
	// Map<String, Object> session = ActionContext.getContext()
	// .getSession();
	// session.put("user", user);
	// result = new MapMessage(0);
	// } else {
	// result = new MapMessage(3);
	// }
	// return SUCCESS;
	// }
	/**
	 * 保存friendId
	 */
	public String saveFriendId() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (request.getParameter("friend_id") != null) {
			session.put("friend_id", request.getParameter("friend_id"));
			map = new HashMap<>(); 
			map.put("message", "success");
			map.put("description", "保存frieng_id成功");
			result = map;
		} else {
			map = new HashMap<>(); 
			map.put("message", "error");
			map.put("description", "保存frieng_id失败");
			result = map;
		}
		return SUCCESS;
	}

	/**
	 * 清除两人之间的记录
	 */
	public String delChatHistory() {
		
		return SUCCESS;
	}

	/**
	 * 获取最新聊天消息
	 */
	public String getLatestMsg() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
//		Integer friend_id = Utils.parseInt(session.get("friend_id").toString());
		
		mDao = new ZMessDaoImp();
		if (session.get("friend_id") == null || user == null) {
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "friend_id异常或未登录，请重试或重新登录");
			result = map;
			return SUCCESS;
		}
		Integer friend_id = Utils.parseInt(session.get("friend_id").toString());
		// Integer friend_id = 3;
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));

		if (limit == -1) {
			limit = 5;
		}
		if (start == -1) {
			start = 15;
		}
		List<VMessageId> list = mDao.getLatest1(user.getId(),
				friend_id, start, limit);
		result = Utils.setToResult(list, limit);

		return SUCCESS;
	}

	/**
	 * 检查是否有新的聊天消息
	 */
	public String checkNewMsg() {
		long start_time = System.currentTimeMillis() / 1000;
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
//		Integer friend_id = Utils.parseInt(request.getParameter("friend_id"));
		if (session.get("friend_id") == null || user == null) {
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "缺少必要参数friend_id或未登录");
			result = map;
			return SUCCESS;
		}
		Integer friend_id = Utils.parseInt(session.get("friend_id").toString());
		// Integer friend_id = 3;
		// Integer start = Utils.parseInt(request.getParameter("start"));
		// Integer limit = Utils.parseInt(request.getParameter("limit"));
		// if (limit == -1) {
		// limit = 5;
		// }

		mDao = new ZMessDaoImp();

		ZConnectState connectState = mDao.getConnectState(user.getId(),
				friend_id);
		if (connectState != null) {
			connectState.setCount(connectState.getCount() + 1);
		} else {
			connectState = new ZConnectState(user.getId(), friend_id);
		}
		Integer id = mDao.insertConnect(connectState);
		if (id == -1) {
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "建立连接失败，请重试");
			result = map;
			return SUCCESS;
		}
		try {
			int i = 0;
			while (true) {
				Integer connectCount = mDao.getConnectCount(user.getId(),
						friend_id);
				if (i > 0 && connectCount > 1) {// 非第一次进入轮询，且连接数大于2则退出轮询，从逻辑上实现断开前一次的连接(防止重复连接)
					// System.out.println("自动断开前一次的连接，当前连接数为：" + connectCount);
					break;
				}
				if (mDao.checkLastest(user.getId(), friend_id) > 0) {
					map = new HashMap<>();
					map.put("message", "success");
					map.put("description", "您有最新消息，请注意查收");
					result = map;
					break;
				}
				if ((System.currentTimeMillis() / 1000) - start_time > 5 * 60) {
					map = new HashMap<>();
					map.put("message", "error");
					map.put("description", "连接超时，默认超时时间为5分钟");
					result = map;
					break;
				}
				Thread.sleep(1000 * 1);
				i = 1;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "线程出错，需重新发送请求");
			result = map;
		} finally {
			// 结束时，连接数减1
			if (id > 0) {
				connectState.setId(id);
			}
			connectState
					.setCount(mDao.getConnectCount(user.getId(), friend_id) - 1);
			mDao.insertConnect(connectState);
		}
		return SUCCESS;
	}

	/**
	 * 发送聊天消息
	 */
	public String chat() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
//		Integer friend_id = Utils.parseInt(session.get("friend_id").toString());
		if (session.get("friend_id") == null||user==null) {
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "未能正确获取缓存中的好友Id,请重新点击选择好友");
			result = map;
			return SUCCESS;
		}
		Integer friend_id = Utils.parseInt(session.get("friend_id").toString());
		mDao = new ZMessDaoImp();
		String message = request.getParameter("message");
		Long time = System.currentTimeMillis() / 1000;
		ZMessage msg = new ZMessage(message, time, user.getId(), friend_id);
		int msgId = mDao.save(msg);
		if (msgId > 0) {
			map = new HashMap<>();
			map.put("message", "success");
			map.put("description", "发送消息成功");
			result = map;
			return SUCCESS;
		}
		map = new HashMap<>();
		map.put("message", "error");
		map.put("description", "发送消息失败了，请重新发送");
		result = map;
		return SUCCESS;
	}

	/**
	 * 获取历史记录
	 */
	public String getHistory() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
//		Integer friend_id = Integer.parseInt(request.getParameter("friend_id"));
		if (session.get("friend_id") == null||user==null) {
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "请重新登录或选择好友");
			result = map;
			return SUCCESS;
		}
		Integer friend_id = Utils.parseInt(session.get("friend_id").toString());
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));
		if (limit == -1) {
			limit = 15;
		}
		mDao = new ZMessDaoImp();
		List<VMessageId> list = mDao.getLatest1(user.getId(), friend_id, start,
				limit);
		result = Utils
				.setToResult(list, mDao.getCount(user.getId(), friend_id));
		return SUCCESS;
	}

	/**
	 * 获取好友列表
	 */
	public String userList() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		ZUserDao uDao = new ZUserDaoImp();
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));
		if (user != null) {
			List<ZUser> list = uDao.getList(user.getId(), start, limit);
			for(ZUser user2:list){
				user2.setPassword("*****");
			}
			result = Utils.setToResult(list, uDao.getCount() - 1);
		} else {
			result = new MapMessage(2);
		}
		return SUCCESS;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

}
