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
import org.apache.struts2.components.If;
import org.dao.imp.ZSwitchDaoImp;
import org.tool.AirControlUtils;
import org.tool.Constans;
import org.tool.PDFUtil;
import org.tool.R;
import org.util.SpeedUtils;
import org.view.VSwitchAlarmId;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;
import speed.model.Record;
import speed.view.VRecordId;
import speed.view.VSensorsId;

import com.opensymphony.xwork2.ActionSupport;

public class SensorsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SensorsDao sDao;
	private Map<String, Object> data;
	private Object result;
	private Integer start;
	private Integer limit;
	private Integer sensorIndex;
	private Integer ctl;
	private Integer temp;
	private Integer available;
	private String host;
	private String start_time;
	private String end_time;
	private String displayname;
	private String location;

	/**
	 * 1.按时间段查看告警记录列表
	 */
	public String getSensorsRecord() {
		sDao = new SensorsDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			if (end_time == null || start_time == null || end_time.equals("")
					|| start_time.equals("")) {
				end_time = (String) session.getAttribute("end_time_sa");
				start_time = (String) session.getAttribute("start_time_sa");
			} else {
				/******* 保存缓存，确保下次查询也返回对应时间段的数据 ******/
				session.setAttribute("end_time_sa", end_time);
				session.setAttribute("start_time_sa", start_time);
			}
			if (location == null || location.equals("")) {
				location = (String) session.getAttribute("location_sa");
			} else {
				session.setAttribute("location_sa", location);
			}
			if (displayname == null || displayname.equals("")) {
				displayname = (String) session.getAttribute("displayname_sa");
			} else {
				session.setAttribute("displayname_sa", displayname);
			}
			if (start_time == null || end_time == null) {
				long count = sDao.getAlarmCount(location, displayname);
				List<VRecordId> list = sDao.getAlarmRecord(start, limit,
						location, displayname);
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
					List<VRecordId> list = sDao.getAlarmRecord(start, limit,
							start_time, end_time, location, displayname);
					Long count = sDao.getRecordCount(start_time, end_time,
							location, displayname);
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

	/**
	 * 2.按时段转存记录
	 */
	public String transferredSensorRecordPDF() {
		if ("".equals(location)) {
			location = null;
		}
		if ("".equals(displayname)) {
			displayname = null;
		}
		sDao = new SensorsDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List<VRecordId> list = sDao.getAlarmRecord(0, -1, location,
						displayname);
				String url = PDFUtil.buidPDF(Constans.watermark, list, 1);
				map.put("url", url);
				if (!sDao.deleteAll()) {
					result = R.getJson(0, "转存失败,请重试", map);
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
					List<VRecordId> list = sDao.getAlarmRecord(0, -1,
							start_time, end_time, location, displayname);
					String url = PDFUtil.buidPDF(Constans.watermark, list, 1);
					if (!sDao.deleteRecord(start_time, end_time, location,
							displayname)) {
						result = R.getJson(0, "转存失败,请重试", map);
					} else {
						result = R.getJson(1, "", map);
					}
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
	 * 3.按时段导出记录
	 */
	public String getSensorRecordPDF() {// 传参3个,start_time,end_time,name
		if ("".equals(location)) {
			location = null;
		}
		if ("".equals(displayname)) {
			displayname = null;
		}
		sDao = new SensorsDaoImp();
		Map<String, Object> map = new HashMap<>();
		try {
			if (start_time == null || end_time == null) {
				List<VRecordId> list = sDao.getAlarmRecord(0, -1, location,
						displayname);
				if (!sDao.deleteAll()) {
					result = R.getJson(0, "转存失败，请重试", map);
				} else {
					result = R.getJson(1, "", map);
				}
				String url = PDFUtil.buidPDF(Constans.watermark, list, 1);
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
					List<VRecordId> list = sDao.getAlarmRecord(0, -1,
							start_time, end_time, location, displayname);
					String url = PDFUtil.buidPDF(Constans.watermark, list, 1);
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

	// 斯必得设备布、撤防
	public String speedArming() {
		sDao = new SensorsDaoImp();
		if (available == 0) {
			available = 1;
			if (sDao.updateAvailable(sensorIndex, available)) {
				result = R.getJson(1, "布防成功", "");
			} else {
				result = R.getJson(0, "布防失败", "");
			}
		} else {
			available = 0;
			if (sDao.updateAvailable(sensorIndex, available)) {
				result = R.getJson(1, "撤防成功", "");
			} else {
				result = R.getJson(0, "撤防失败", "");
			}
		}
		return SUCCESS;
	}

	// 控制空调
	public String controlAirConditioner() {
		try {
			if (temp == null) {
				temp = 0;
			}
			System.out.println(host + "||" + ctl + "||" + temp + "||"
					+ displayname);
			if (AirControlUtils.control(host, ctl, temp, displayname)) {
				result = R.getJson(1, "操作成功", "");
			} else {
				result = R.getJson(0, "操作失败，请重试", "");
			}
		} catch (IOException e) {
			result = R.getJson(0, "操作失败，请重试", "");
		}
		return SUCCESS;
	}

	// 获取水浸的数据列表，类型为16
	public String getWaterloggingList() {
		long time = System.currentTimeMillis();
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType1(start, limit, (short) 16);
		Long count = sDao.getSensorsByType1Count((short) 16);
		data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("list", list);
		result = R.getJson(1, "", data);
		System.out.println(System.currentTimeMillis() - time);
		return SUCCESS;
	}

	// 获取温度的数据列表，类型为48
	public String getTempList() {
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType1(start, limit, (short) 48);
		Long count = sDao.getSensorsByType1Count((short) 48);
		data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	// 获取湿度的数据列表，类型为49
	public String getWetList() {
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType1(start, limit, (short) 49);
		Long count = sDao.getSensorsByType1Count((short) 49);
		data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	// 获取空调的数据列表,空调类型为224
	public String getAirConditionList() {
		// Long time = System.currentTimeMillis();
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao.getSensorsByType1(start, limit,
				(short) 224);
		Long count = sDao.getSensorsByType1Count((short) 224);
		// System.out.println(System.currentTimeMillis() - time);
		for (VSensorsId v : list) {
			if (v.getSensorvalue() > 0) {
				String b = Long.toHexString(v.getSensorvalue().longValue());
				String c = "00000000".substring(b.length()) + b;
				Integer num = Integer.parseInt(c.substring(6, 8), 16);
				Double temp = Double.parseDouble("" + num) / 2;
				Integer wet = Integer.parseInt(c.substring(4, 6), 16);
				Integer stateNum = Integer.parseInt(c.substring(2, 4), 16);
				v.setTemp(temp);
				v.setWet(wet);
				v.setState(stateNum > 0 ? 1 : 0);
				v.setRunModel(SpeedUtils.getState(stateNum));
			}
		}
		data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("list", list);
		result = R.getJson(1, "", data);
		// System.out.println(System.currentTimeMillis() - time);
		return SUCCESS;
	}

	// 获取设备历史记录
	public String getSensorHistory() {
		sDao = new SensorsDaoImp();
		List<VRecordId> list = sDao.getHistory(sensorIndex, start, limit);
		Long count = sDao.getHistoryCount(sensorIndex);
		data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("list", list);
		result = R.getJson(1, "", data);
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

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getSensorIndex() {
		return sensorIndex;
	}

	public void setSensorIndex(Integer sensorIndex) {
		this.sensorIndex = sensorIndex;
	}

	public Integer getCtl() {
		return ctl;
	}

	public void setCtl(Integer ctl) {
		this.ctl = ctl;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
