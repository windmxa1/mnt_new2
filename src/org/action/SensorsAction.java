package org.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.If;
import org.tool.AirControlUtils;
import org.tool.R;
import org.util.SpeedUtils;

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
			if (AirControlUtils.control(host, ctl, temp)) {
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
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType1(start, limit, (short) 16);
		Long count = sDao.getSensorsByType1Count((short) 16);
		data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("list", list);
		result = R.getJson(1, "", data);
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
		Long time = System.currentTimeMillis();
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao.getSensorsByType1(start, limit,
				(short) 224);
		Long count = sDao.getSensorsByType1Count((short) 224);
		System.out.println(System.currentTimeMillis() - time);
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
		System.out.println(System.currentTimeMillis() - time);
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

}
