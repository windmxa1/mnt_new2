package org.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZLogDao;
import org.dao.imp.ZLogDaoImp;
import org.tool.Constans;
import org.tool.PDFUtil;
import org.tool.R;
import org.view.VLogId;

import com.opensymphony.xwork2.ActionSupport;

public class ZLogAction extends ActionSupport {
	private Long id;
	private String username;
	private String operation;
	private Long time;
	private String start_time;
	private String end_time;
	private Integer start;
	private Integer limit;

	private Object result;

	/**
	 * 1.获取操作日志
	 */
	public String getLogList() {
		// Long startTime = System.currentTimeMillis();
		ZLogDao lDao = new ZLogDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			if (end_time == null || start_time == null || end_time.equals("")
					|| start_time.equals("")) {
				end_time = (String) session.getAttribute("end_time_l");
				start_time = (String) session.getAttribute("start_time_l");
			} else {
				/******* 保存缓存，确保下次查询也返回对应时间段的数据 ******/
				session.setAttribute("end_time_l", end_time);
				session.setAttribute("start_time_l", start_time);
			}
			if (start_time == null || end_time == null) {
				long count = lDao.getCount();
				List list = lDao.getLogList(start, limit);
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
					List<VLogId> list = lDao.getLogList(start, limit,
							start_time, end_time);
					Long count = lDao.getLogCount(start_time, end_time);
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
		// System.out.println(System.currentTimeMillis() - startTime);
		return SUCCESS;
	}

	// /**
	// * 2.删除操作日志
	// */
	// public String deleteLog() {
	// ZLogDao aDao = new ZLogDaoImp();
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
	// if (aDao.deleteLog(start_clock / 1000, end_clock / 1000)) {
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
	 * 2.转存为pdf
	 */
	public String transferredLogPDF() {
		ZLogDao aDao = new ZLogDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List list = aDao.getLogList(0, -1);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 2);
				map.put("url", url);
				if (!aDao.deleteAll()) {
					result = R.getJson(0, "转存失败，请重试", "");
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
					List<VLogId> list = aDao.getLogList(0, -1, start_time,
							end_time);
					String url = PDFUtil.buidPDF(Constans.watermark, list, 2);
					map.put("url", url);
					if (!aDao.deleteLog(start_clock / 1000, end_clock / 1000)) {
						result = R.getJson(0, "转存失败，请重试", "");
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
	 * 3.转存为pdf
	 */
	public String getLogPDF() {
		ZLogDao aDao = new ZLogDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List list = aDao.getLogList(0, -1);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 2);
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
					List<VLogId> list = aDao.getLogList(0, -1, start_time,
							end_time);
					String url = PDFUtil.buidPDF(Constans.watermark, list, 2);
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

	/**
	 * 导出所有日志记录并删除
	 */
	public String getAllLog() {
		ZLogDao aDao = new ZLogDaoImp();
		List<VLogId> list = aDao.getAllLog();
		PDFUtil.buidPDF(Constans.watermark, list, 2);
		return SUCCESS;
	}

	// ====================================================
	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
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
}
