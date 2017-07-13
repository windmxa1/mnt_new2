package org.action;

import java.util.List;

import org.util.SpeedUtils;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;
import speed.view.VSensorsId;

import com.opensymphony.xwork2.ActionSupport;

public class SensorsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SensorsDao sDao;
	private Object result;
	private Integer start;
	private Integer limit;

	// 获取水浸的数据列表，类型为16
	public String getWaterloggingList() {
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType2(start, limit, (short) 16);
		result = list;
		return SUCCESS;
	}

	// 获取温度的数据列表，类型为48
	public String getTempList() {
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType2(start, limit, (short) 48);
		result = list;
		return SUCCESS;
	}

	// 获取湿度的数据列表，类型为49
	public String getWetList() {
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao
				.getSensorsByType2(start, limit, (short) 49);
		result = list;
		return SUCCESS;
	}

	// 获取空调的数据列表,空调类型为224
	public String getAirConditionList() {
		sDao = new SensorsDaoImp();
		List<VSensorsId> list = sDao.getSensorsByType2(start, limit,
				(short) 224);
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
				v.setState(SpeedUtils.getState(stateNum));
			}
		}
		result = list;
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

}
