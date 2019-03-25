package org.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZLogDao;
import org.dao.ZSwitchDao;
import org.dao.imp.ZLogDaoImp;
import org.dao.imp.ZSwitchDaoImp;
import org.tool.Constans;
import org.tool.PDFUtil;
import org.tool.R;
import org.view.VLogId;
import org.view.VSwitchAlarmId;
import org.view.VSwitchId;

import com.opensymphony.xwork2.ActionSupport;

public class SwitchAction extends ActionSupport {
	private Object result;
	private ZSwitchDao sDao;
	private Integer start;
	private Integer limit;
	// private Map<String, Object> data;
	private Integer available;
	private Integer id;
	private String start_time;
	private String end_time;
	private String name;

	/**
	 * 获取开关量设备名称列表
	 */
	public String getSwitchNameList() {
		sDao = new ZSwitchDaoImp();
		List<String> list = sDao.getSwitchNameList();
		List list2 = new ArrayList<>();
		for (String s : list) {
			Map<String, String> item = new HashMap<>();
			item.put("name", s);
			list2.add(item);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("list", list2);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 获取开关量列表
	 */
	public String getSwitchList() {
		sDao = new ZSwitchDaoImp();
		List<VSwitchId> list = sDao.getSwitchList(start, limit);
		for (VSwitchId v : list) {
			v.setGroupname(v.getGroupname().replace("JF-", ""));
			if (v.getAck() == 1) {// 当最新一条告警记录被确认时，显示设备为正常状态
				v.setValue("OK");
			}
		}
		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		data.put("total", list.size());
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 获取告警的设备名称列表
	 */
	public String getSwitchAlarm() {
		sDao = new ZSwitchDaoImp();
		List<String> list = sDao.getAlarmDevice();
		if (list == null || list.size() == 0) {
			result = R.getJson(1, "设备正常", "");
			return SUCCESS;
		}
		String msg = "报警设备为:";
		for (String name : list) {
			msg = msg + " " + name;
		}
		result = R.getJson(0, msg, "");
		return SUCCESS;
	}

	/**
	 * 设备布防/撤防
	 */
	public String updateAvailable() {
		sDao = new ZSwitchDaoImp();
		if (available == 0) {
			available = 1;
			String str = "sh /home/dzj/cps/nvr_script/nvrController.sh ";
			str += " " + (id - 1) + " " + available;
			try {
				Runtime.getRuntime().exec(str);
				if (sDao.updateAvailable(available, id)) {
					result = R.getJson(1, "布防成功", "");
					return SUCCESS;
				}
			} catch (IOException e) {
				result = R.getJson(0, "布防失败", "");
				return SUCCESS;
			}
		} else {
			available = 0;
			String str = "sh /home/dzj/cps/nvr_script/nvrController.sh ";
			str += " " + (id - 1) + " " + available;
			try {
				Runtime.getRuntime().exec(str);
				if (sDao.updateAvailable(available, id)) {
					result = R.getJson(1, "撤防成功", "");
					return SUCCESS;
				}
			} catch (IOException e) {
				result = R.getJson(0, "撤防失败", "");
				return SUCCESS;
			}
			return SUCCESS;
		}
		result = R.getJson(0, "操作失败，请重试", "");
		return SUCCESS;
	}

	/**
	 * 确认设备告警
	 */
	public String ackAlarm() {
		sDao = new ZSwitchDaoImp();
		try {
			String str = "sh /home/dzj/cps/nvr_script/nvrClearAlarm.sh ";
			Runtime.getRuntime().exec(str);
			if (sDao.AckAlarm(id)) {
				result = R.getJson(1, "操作成功", "");
				return SUCCESS;
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = R.getJson(0, "操作失败,请重试", "");
			return SUCCESS;
		}
		result = R.getJson(0, "操作失败,请重试", "");
		return SUCCESS;
	}

	/**
	 * 获取单个开关量告警记录
	 */
	public String getSwitchAlarmHistory() {
		sDao = new ZSwitchDaoImp();
		List<VSwitchAlarmId> list = sDao
				.getSwitchAlarmHistory(start, limit, id);
		Map data = new HashMap<>();
		data.put("list", list);
		data.put("count", sDao.getSwitchAlarmCount(id));
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 1.按时间段查看告警记录列表
	 */
	public String getAllSwitchAlarmHistory() {
		sDao = new ZSwitchDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			if (end_time == null || start_time == null || end_time.equals("")
					|| start_time.equals("")) {
				end_time = (String) session.getAttribute("end_time_sw");
				start_time = (String) session.getAttribute("start_time_sw");
			} else {
				/******* 保存缓存，确保下次查询也返回对应时间段的数据 ******/
				session.setAttribute("end_time_sw", end_time);
				session.setAttribute("start_time_sw", start_time);
			}
			if (name == null || name.equals("")) {
				name = (String) session.getAttribute("name_sw");
			} else {
				session.setAttribute("name_sw", name);
			}
			if (start_time == null || end_time == null) {
				long count = sDao.getSwitchAlarmCount(name);
				List list = sDao.getSwitchAlarmHistory(start, limit, name);
				map.put("total", count);
				map.put("list", list);
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
					end_time = sdf.format(calendar.getTime());
					// }
					List<VSwitchAlarmId> list = sDao.getSwitchAlarmHistory(
							start, limit, start_time, end_time, name);
					Long count = sDao.getSwitchAlarmCount(start_time, end_time,
							name);
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

	// /**
	// * 2.按时段删除开关量报警日志
	// */
	// public String deleteSwitchAlarmHistory() {
	// sDao = new ZSwitchDaoImp();
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
	// if (sDao.deleteSwitchAlarm(start_clock / 1000, end_clock / 1000)) {
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
	public String transferredSwitchAlarmPDF() {// 传参3个,start_time,end_time,name
		if ("".equals(name)) {
			name = null;
		}
		sDao = new ZSwitchDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List list = sDao.getSwitchAlarmHistory(0, -1, name);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 0);
				map.put("url", url);
				if (!sDao.deleteAll()) {
					result = R.getJson(0, "转存失败，请重试", map);
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
					end_time = sdf.format(calendar.getTime());
					// }
					List<VSwitchAlarmId> list = sDao.getSwitchAlarmHistory(0,
							-1, start_time, end_time, name);
					String url = PDFUtil.buidPDF(Constans.watermark, list, 0);
					map.put("url", url);
					if (!sDao.deleteSwitchAlarm(start_clock / 1000,
							end_clock / 1000, name)) {
						result = R.getJson(0, "转存失败,请重试", map);
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
	public String getSwitchAlarmPDF() {// 传参3个,start_time,end_time,name
		if ("".equals(name)) {
			name = null;
		}
		sDao = new ZSwitchDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List list = sDao.getSwitchAlarmHistory(0, -1, name);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 0);
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
					end_time = sdf.format(calendar.getTime());
					// }
					List<VSwitchAlarmId> list = sDao.getSwitchAlarmHistory(0,
							-1, start_time, end_time, name);
					String url = PDFUtil.buidPDF(Constans.watermark, list, 0);
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

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
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

}
