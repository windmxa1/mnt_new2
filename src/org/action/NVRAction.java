package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.HostInventoryDao;
import org.dao.ItemsDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.HostInventoryDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.model.HistoryText;
import org.model.HostInventory;
import org.model.Items;
import org.tool.R;
import org.view.VHostGroupId;
import org.view.VItemValueId;

import com.opensymphony.xwork2.ActionSupport;

public class NVRAction extends ActionSupport { // 录像机
	private Map<String, Object> data;
	private Object result; // json 返回值
	private Integer start;
	private Integer limit;

	public String getNVRInfo() throws Exception {
		//Long time = System.currentTimeMillis();
		DeviceInfoDao dInfoDao = new DeviceDaoImp();
		List<VItemValueId> list = dInfoDao.getHostList("录像机", start, limit);
		List<Map<String, String>> li = new ArrayList<>();
		for (VItemValueId v : list) {
			Map<String, String> infoMap = new HashMap<String, String>();
			infoMap.put("hostIp", v.getHost());
			infoMap.put("hostId", "" + v.getHostid());
			infoMap.put("location", v.getGroupname());
			infoMap.put(v.getName(), v.getValue());
			li.add(infoMap);
		}
		data = new HashMap<>();
		GroupsDao gDao = new GroupsDaoImp();
		data.put("total", gDao.getHostCountByGroupid(10L));
		data.put("list", li);
		result = R.getJson(1, "", data);
		//System.out.println(System.currentTimeMillis() - time);
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
