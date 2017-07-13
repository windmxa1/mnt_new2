package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.ZMissionDao;
import org.dao.imp.ZMissionDaoImp;
import org.model.ZMission;
import org.model.ZUser;
import org.util.MapMessage;
import org.util.Utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZMissionAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private Object result;
	private HttpServletResponse response = null;
	private HttpServletRequest request = null;
	private static final long serialVersionUID = 1L;
	private ZMissionDao mDao;
	private Integer[] id;
	private Integer a;

	/**
	 * 保存选择的好友id
	 */
	public String saveFriendId1() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (request.getParameter("friend_id1") != null) {
			session.put("friend_id1", request.getParameter("friend_id1"));
			Map<String, String> map = new HashMap<>();
			map = new HashMap<>();
			map.put("message", "success");
			map.put("description", "保存frieng_id1成功");
			result = map;
		} else {
			Map<String, String> map = new HashMap<>();
			map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "保存frieng_id1失败");
			result = map;
		}
		return SUCCESS;
	}

	/**
	 * 获取最新任务(弹窗显示最新消息)
	 */
	public String getNewTask() {
		mDao = new ZMissionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "检测到您还没有进行登录，请重新登录");
			result = map;
			return SUCCESS;
		}
		List<ZMission> list = mDao.getUnReadMission(user.getId(), 1);
		result = Utils.setToResult2(list);
		return SUCCESS;
	}

	/**
	 * 查看是否有新任务
	 */
	public String checkNewTask() {
		long start_time = System.currentTimeMillis() / 1000;
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "检测到您还没有进行登录，请重新登录");
			result = map;
			return SUCCESS;
		}
		mDao = new ZMissionDaoImp();
		try {
			while (true) {
				if ((System.currentTimeMillis() / 1000) - start_time > 5 * 60) {
					Map<String, String> map = new HashMap<>();
					map.put("message", "error");
					map.put("description", "连接超时，默认超时时间为5分钟");
					result = map;
					break;
				}
				if (mDao.checkLastestMission(user.getId()) > 0) {
					Map<String, String> map = new HashMap<>();
					map.put("message", "success");
					map.put("description", "您有最新任务，请注意查看");
					result = map;
					break;
				}
				Thread.sleep(1000 * 5);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "线程出错，需重新发送请求");
			result = map;
		}
		return SUCCESS;
	}

	/**
	 * 接收者，获取未完成的任务列表
	 */
	public String getUnCompleteTask() {
		mDao = new ZMissionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "检测到您还没有进行登录，请重新登录");
			result = map;
			return SUCCESS;
		}
		// 更新未读的任务
		mDao.readMission(user.getId());
		// 获取未完成的任务
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));
		List<ZMission> list = mDao.getUnCompleteMission(user.getId(), 1,start,limit);
		result = Utils.setToResult(list, mDao.getUnCompleteMissionCount(user.getId(),1));
		return SUCCESS;
	}

	/**
	 * 接收者，获取所有的任务列表
	 */
	public String receiverGetAllTask() {
		mDao = new ZMissionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "检测到您还没有进行登录，请重新登录");
			result = map;
			return SUCCESS;
		}
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));
		mDao.readMission(user.getId());
		List<ZMission> list = mDao.getAllMission(user.getId(), 1, start, limit);
		result = Utils.setToResult(list,
				mDao.getAllMissionCount(user.getId(), 1));
		return SUCCESS;
	}

	/**
	 * 发布者，获取未确认的任务列表
	 */
	public String getUnConfirmTask() {
		mDao = new ZMissionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "检测到您还没有进行登录，请重新登录");
			result = map;
			return SUCCESS;
		}
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));
		List<ZMission> list = mDao.getUnConfirmMission(user.getId(), 0, start,
				limit);
		result = Utils.setToResult(list,
				mDao.getUnConfirmMissionCount(user.getId(), 0));
		return SUCCESS;
	}

	/**
	 * 发布者，获取所有的任务列表
	 */
	public String senderGetAllTask() {
		mDao = new ZMissionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "检测到您还没有进行登录，请重新登录");
			result = map;
			return SUCCESS;
		}
		Integer start = Utils.parseInt(request.getParameter("start"));
		Integer limit = Utils.parseInt(request.getParameter("limit"));
		List<ZMission> list = mDao.getAllMission(user.getId(), 0, start, limit);
		result = Utils.setToResult(list,
				mDao.getAllMissionCount(user.getId(), 0));
		return SUCCESS;
	}

	/**
	 * 发布一条新的任务
	 */
	public String addMission() {
		mDao = new ZMissionDaoImp();
		String content = request.getParameter("content");
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		Long time = System.currentTimeMillis() / 1000;
		if (session.get("friend_id1") != null && user != null) {
			Integer receiverId = Utils.parseInt(session.get("friend_id1")
					.toString());
			if (mDao.addMission(new ZMission(content, user.getId(), receiverId,
					time))) {
				result = new MapMessage(0);
			} else {
				result = new MapMessage(3);
			}
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "未登录或未选择发送任务的对象");
			result = map;
		}
		return SUCCESS;
	}

	/**
	 * 完成任务
	 */
	public String completeTask() {
		mDao = new ZMissionDaoImp();
		if (id != null && id.length > 0) {
			if (mDao.completeMission(id)) {
				Map<String, String> map = new HashMap<>();
				map.put("message", "success");
				map.put("description", "成功！");
				result = map;
			} else {
				Map<String, String> map = new HashMap<>();
				map.put("message", "error");
				map.put("description", "失败");
				result = map;
			}
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "id为空");
			result = map;
		}
		return SUCCESS;
	}

	/**
	 * 确认任务完成
	 */
	public String confirmTask() {
		mDao = new ZMissionDaoImp();
		if (id != null && id.length > 0) {
			if (mDao.confirmMission(id)) {
				Map<String, String> map = new HashMap<>();
				map.put("message", "success");
				map.put("description", "成功！");
				result = map;
			} else {
				Map<String, String> map = new HashMap<>();
				map.put("message", "error");
				map.put("description", "失败");
				result = map;
			}
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "id为空");
			result = map;
		}
		return SUCCESS;
	}
	/**
	 * 取消任务
	 */
	public String canCelTask() {
		mDao = new ZMissionDaoImp();
		String cancelReason = request.getParameter("cancelReason");
		if (id != null && id.length > 0&&cancelReason!=null) {
			if (mDao.cancelMission(id[0], cancelReason)) {
				Map<String, String> map = new HashMap<>();
				map.put("message", "success");
				map.put("description", "成功！");
				result = map;
			} else {
				Map<String, String> map = new HashMap<>();
				map.put("message", "error");
				map.put("description", "失败");
				result = map;
			}
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "id为空");
			result = map;
		}
		return SUCCESS;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

}
