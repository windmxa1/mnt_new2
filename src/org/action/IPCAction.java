package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.ZIPCRecordDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.ZIPCRecordDaoImp;
import org.model.ZIpcRecord;
import org.tool.R;
import org.view.VItemValueId;

import com.opensymphony.xwork2.ActionSupport;

public class IPCAction extends ActionSupport {
	private Map<String, Object> data;
	private Object result; // json 返回值
	private Integer start;
	private Integer limit;
	private String name;
	private ZIpcRecord record;

	public String getIPCInfo() throws Exception { // 待定：是否有null的判断,参照login
		DeviceInfoDao dInfoDao = new DeviceDaoImp();
		List<VItemValueId> list = dInfoDao.getHostList("摄像头", start, limit);
		List<Map<String, String>> li = new ArrayList<>();
		for (VItemValueId v : list) {
			Map<String, String> infoMap = new HashMap<String, String>();
			infoMap.put("hostIp", v.getHost());
			infoMap.put("hostId", "" + v.getHostid());
			infoMap.put("location", v.getGroupname());
			infoMap.put(v.getName(), v.getValue());
			infoMap.put("deviceName", v.getDeviceName());
			infoMap.put("nvrIp", v.getNvrIp());
			li.add(infoMap);
		}
		data = new HashMap<>();
		GroupsDao gDao = new GroupsDaoImp();
		data.put("total", gDao.getHostCountByGroupid(9L));
		data.put("list", li);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 获取本地录像文件
	 */
	public String getIPCRecordList() throws Exception { // 待定：是否有null的判断,参照login
		ZIPCRecordDao zDao = new ZIPCRecordDaoImp();
		List<ZIpcRecord> li = zDao.getList(start,limit);
		if (li == null) {
			result = R.getJson(0, "加载失败，请重试", "");
			return SUCCESS;
		}
		data = new HashMap<>();
		data.put("total", zDao.getCount());
		data.put("list", li);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 保存本地录像文件路径
	 */
	public String addIPCLocalRecord() throws Exception {
		ZIPCRecordDao zDao = new ZIPCRecordDaoImp();
		if (zDao.saveOrUpdate(record)>0) {
			result = R.getJson(1, "", "");
			return SUCCESS;
		}
		result = R.getJson(0, "", "");
		return SUCCESS;
	}

	/**
	 * 获取摄像头IP
	 */
	public String getIPCIpByName() throws Exception {
		DeviceInfoDao dInfoDao = new DeviceDaoImp();
		data = new HashMap<>();
		data.put("ipcIP", dInfoDao.getIPCIpByName(name));
		result = R.getJson(1, "", data);
		return SUCCESS;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZIpcRecord getRecord() {
		return record;
	}

	public void setRecord(ZIpcRecord record) {
		this.record = record;
	}
}
