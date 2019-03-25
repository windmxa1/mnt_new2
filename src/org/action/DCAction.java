package org.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.tag.rt.core.OutTag;
import org.dao.DeviceInfoDao;
import org.dao.EventsDao;
import org.dao.GroupsDao;
import org.dao.ZConnectCtlDao;
import org.dao.ZHostConfigDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.EventsDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.ZConnectCtlDaoImp;
import org.dao.imp.ZHostConfigDaoImp;
import org.dao.imp.ZSwitchDaoImp;
import org.model.ZUser;
import org.tool.Constans;
import org.tool.PDFUtil;
import org.tool.R;
import org.tool.Utils;
import org.view.VDcEventsId;
import org.view.VEventsId;
import org.view.VItemValueId;
import org.view.VSwitchAlarmId;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DCAction extends ActionSupport { // 门禁
	private Map<String, Object> data;
	private String host = "192.168.1.80";
	private Object result; // json 返回值
	private String doorid = "1";
	private String ctl = "1";
	private Integer start;
	private Integer limit;
	private Integer available;
	private String start_time;
	private String end_time;
	private String name;
	private String type;
	DeviceInfoDao dDao;
	EventsDao eDao;

	// 海康设备布、撤防(对单个设备操作)
	public String HKArming() {
		ZHostConfigDao hConfigDao = new ZHostConfigDaoImp();
		if (available == 0) {
			available = 1;
			if (hConfigDao.updateAvailable(host, available)) {
				result = R.getJson(1, "布防成功", "");
			} else {
				result = R.getJson(0, "布防失败", "");
			}
		} else {
			available = 0;
			if (hConfigDao.updateAvailable(host, available)) {
				result = R.getJson(1, "撤防成功", "");
			} else {
				result = R.getJson(0, "撤防失败", "");
			}
		}
		return SUCCESS;
	}

	public String getDCInfo() throws Exception {
		dDao = new DeviceDaoImp();
		List<VItemValueId> list = dDao.getHostList1("门禁", start, limit);
		List<Map<String, String>> li = new ArrayList<>();
		for (VItemValueId v : list) {
			Map<String, String> infoMap = new HashMap<String, String>();
			infoMap.put("hostIp", v.getHost());
			infoMap.put("hostId", "" + v.getHostid());
			infoMap.put("location", v.getGroupname());
			infoMap.put("notice", "" + v.getNotice());
			infoMap.put("deviceName", "" + v.getDeviceName());
			String s1[] = v.getName().split(",");
			String s2[] = v.getValue().split(",");
			infoMap.put(s1[0], s2[0]);
			try {
				infoMap.put(s1[1], s2[1]);
			} catch (Exception e) {
				infoMap.put(s1[1], "");
			}
			if (v.getHost().equals("192.168.117.121")) {
				infoMap.put(s1[2], s2[2]);
			}
			li.add(infoMap);
		}
		data = new HashMap<>();
		GroupsDao gDao = new GroupsDaoImp();
		data.put("total", gDao.getHostCountByGroupid1(8L));
		data.put("list", li);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 查看门禁进出信息列表、同时更新门禁的通知状态
	 */
	public String getDCEvents() {
		dDao = new DeviceDaoImp();
		List<VDcEventsId> list = dDao.getDCEvents(host, start, limit);
		data = new HashMap<>();
		data.put("list", list);
		data.put("total", dDao.getDCEventsCount(host));
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	public String doorControl() {
		try {
			String str = "sh /home/dzj/cps/dc_script/doorController.sh ";
			str += doorid + " " + ctl + " " + host;
			// 开门命令
			// ./doorController.sh 1 1 192.168.117.122
			// 关门门命令
			// ./doorController.sh 1 0 192.168.117.31
			Runtime.getRuntime().exec(str);
			result = R.getJson(1, "", "");
			return SUCCESS;
		} catch (IOException e) {
			result = R.getJson(0, "操作失败，请重试", "");
			return SUCCESS;
		}
	}

	// 开门提示
	public String doorNotice() throws Exception {
		dDao = new DeviceDaoImp();
		List<VDcEventsId> list = dDao.getUnReadDCEvents();
		if (list == null || list.size() == 0) {
			result = R.getJson(0, "", "");
			return SUCCESS;
		}
		data = new HashMap<>();
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 1.按时间段查看事件列表
	 */
	public String getEvents() {
		eDao = new EventsDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			if (end_time == null || start_time == null || end_time.equals("")
					|| start_time.equals("")) {
				end_time = (String) session.getAttribute("end_time_hk");
				start_time = (String) session.getAttribute("start_time_hk");
			} else {
				/******* 保存缓存，确保下次查询也返回对应时间段的数据 ******/
				session.setAttribute("end_time_hk", end_time);
				session.setAttribute("start_time_hk", start_time);
			}
			if (name == null || name.equals("")) {
				name = (String) session.getAttribute("name_hk");
			} else {
				session.setAttribute("name_hk", name);
			}
			if (type == null || type.equals("")) {
				type = (String) session.getAttribute("type_hk");
			} else {
				session.setAttribute("type_hk", type);
			}
			if (start_time == null || end_time == null) {
				long count = eDao.getCount(name, type);
				List list = eDao.getList(start, limit, name, type);
				map.put("total", count);
				map.put("list", list);
				result = R.getJson(1, "", map);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long start_clock = sdf.parse(start_time).getTime();
				long end_clock = sdf.parse(end_time).getTime();
				if (start_clock <= end_clock) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(end_clock);
					calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
					end_clock = calendar.getTimeInMillis();
					List<VEventsId> list = eDao.getListByClock(start, limit,
							start_clock / 1000, end_clock / 1000, name, type);
					Long count = eDao.getCountByClock(start_clock / 1000,
							end_clock / 1000, name, type);
					map.put("total", count);
					map.put("list", list);
					result = R.getJson(1, "", map);
				} else {
					result = R.getJson(0, "参数错误，请选择正确的日期", "");
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			result = R.getJson(0, "参数转换错误，请输入正确的日期格式yyyy-MM-dd", "");
		}
		return SUCCESS;
	}

	//
	// /**
	// * 2.按时段删除记录
	// */
	// public String deleteEvents() {
	// eDao = new EventsDaoImp();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// if (start_time == null || end_time == null) {
	// result = R.getJson(0, "缺少必要参数", "");
	// return SUCCESS;
	// }
	// try {
	// long start_clock = sdf.parse(start_time).getTime();
	// long end_clock = sdf.parse(end_time).getTime();
	// if (start_clock <= end_clock) {
	// // if (start_clock == end_clock) {
	// Calendar calendar = new GregorianCalendar();
	// calendar.setTimeInMillis(end_clock);
	// calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
	// // end_time = sdf.format(calendar.getTime());
	// end_clock = calendar.getTimeInMillis();
	// // }
	// }
	// if (eDao.deleteByClock(start_clock / 1000, end_clock / 1000)) {
	// result = R.getJson(1, "删除成功", "");
	// } else {
	// result = R.getJson(0, "删除失败", "");
	// }
	// } catch (ParseException e) {
	// result = R.getJson(0, "数据解析失败，请输入正确的日期格式", "");
	// }
	// return SUCCESS;
	// }

	/**
	 * 2.按时段转存成Pdf
	 */
	public String transferredEventsPDF() {
		if ("".equals(name)) {
			name = null;
		}
		if ("".equals(type)) {
			type = null;
		}
		eDao = new EventsDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List list = eDao.getList(0, -1, name, type);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 3);
				map.put("url", url);
				if (!eDao.deleteAll()) {
					result = R.getJson(0, "转存失败请重试", map);
				} else {
					result = R.getJson(1, "", map);
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long start_clock = sdf.parse(start_time).getTime();
				long end_clock = sdf.parse(end_time).getTime();
				if (start_clock <= end_clock) {
					// if (start_clock == end_clock) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(end_clock);
					calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
					end_clock = calendar.getTimeInMillis();
					// }
					List<VEventsId> list = eDao.getListByClock(0, -1,
							start_clock / 1000, end_clock / 1000, name, type);

					String url = PDFUtil.buidPDF(Constans.watermark, list, 3);
					map.put("url", url);
					if (!eDao.deleteByClock(start_clock / 1000,
							end_clock / 1000, name, type)) {
						result = R.getJson(0, "转存失败请重试", map);
					} else {
						result = R.getJson(1, "", map);
					}
				} else {
					result = R.getJson(0, "参数错误，请选择正确的日期", "");
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			result = R.getJson(0, "参数转换错误，请输入正确的日期格式yyyy-MM-dd", "");
		}
		return SUCCESS;
	}

	/**
	 * 3.按时段导出成Pdf
	 */
	public String getEventsPDF() {
		eDao = new EventsDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List list = eDao.getList(0, -1, name, type);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 3);
				map.put("url", url);
				result = R.getJson(1, "", map);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long start_clock = sdf.parse(start_time).getTime();
				long end_clock = sdf.parse(end_time).getTime();
				if (start_clock <= end_clock) {
					// if (start_clock == end_clock) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(end_clock);
					calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
					end_clock = calendar.getTimeInMillis();
					// }
					List<VEventsId> list = eDao.getListByClock(0, -1,
							start_clock / 1000, end_clock / 1000, name, type);

					String url = PDFUtil.buidPDF(Constans.watermark, list, 3);
					map.put("url", url);
					result = R.getJson(1, "", map);
				} else {
					result = R.getJson(0, "参数错误，请选择正确的日期", "");
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			result = R.getJson(0, "参数转换错误，请输入正确的日期格式yyyy-MM-dd", "");
		}
		return SUCCESS;
	}

	//
	// // 开门提示
	// public String doorNotice1() {
	// DeviceInfoDao dDao = new DeviceDaoImp();
	// long startTime = System.currentTimeMillis() / 1000;
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// ZUser user = (ZUser) session.get("user");
	// if (user == null) {
	// result = R.getJson(-999, "检测到您还没有进行登录，请进行登录", "");
	// return SUCCESS;
	// }
	// String threadId = System.currentTimeMillis() + Utils.ran6();
	// ZConnectCtlDao cDao = new ZConnectCtlDaoImp();
	// if (!cDao.saveOrUpdate(user.getId(), 0, threadId)) {
	// System.out.println("建立告警长连接失败");
	// result = R.getJson(0, "连接失败请重试", "");
	// return SUCCESS;
	// }
	// try {
	// int i = 0;
	// while (true) {
	// String out = "";
	// if ((System.currentTimeMillis() / 1000) - startTime > 5 * 60) {
	// result = R.getJson(0, "连接超时，默认超时时间为5分钟", "");
	// break;
	// }
	// String threadId2 = cDao.getThreadId(user.getId(), 0);
	// if (i > 0 && !threadId2.equals(threadId)) {//
	// 非第一次进入轮询，且连接数大于1则退出轮询，从逻辑上实现断开前一次的连接(防止重复连接)
	// result = R.getJson(500, "自动断开前一次的连接，当前可运行进程编号为："
	// + threadId2, "");
	// break;
	// }
	// List<VDcEventsId> list = dDao.readDCEvents();
	// if (list != null && list.size() > 0) {
	// int k = 0;
	// for (VDcEventsId events : list) {
	// if (k == 0) {
	// out = "刷卡信息-"
	// + events.getGroupname().replace("JF-", "")
	// + ":" + events.getCardid();
	// } else {
	// out = out + " | "
	// + events.getGroupname().replace("JF-", "")
	// + ":" + events.getCardid();
	// }
	// k = 1;
	// }
	// result = R.getJson(1, "", out);
	// break;
	// }
	// Thread.sleep(1000 * 1);
	// i = 1;
	// }
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// result = R.getJson(0, "线程出错，需重新发送请求", "");
	// } finally {
	// }
	// System.out.println("本次连接耗时:"
	// + (System.currentTimeMillis() / 1000 - startTime) + "秒");
	//
	// return SUCCESS;
	// }

	public String getCtl() {
		return ctl;
	}

	public void setCtl(String ctl) {
		this.ctl = ctl;
	}

	public String getDoorid() {
		return doorid;
	}

	public void setDoorid(String doorid) {
		this.doorid = doorid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
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

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
