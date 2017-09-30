package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.ServicesDao;
import org.dao.ZSwitchDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.ServicesDaoImp;
import org.dao.imp.ZSwitchDaoImp;
import org.tool.R;
import org.util.SpeedUtils;
import org.view.VItemValueId;
import org.view.VServicesId;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;
import speed.view.VSensorsId;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport {
	private Object result;
	private Long groupId;
	private String groupName;
	private Map<String, Object> data;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * 获取机房列表及状态
	 */
	public String getMapInfo() {
		SensorsDao sDao = new SensorsDaoImp();
		ServicesDao sDao2 = new ServicesDaoImp();
		List<VServicesId> list = sDao2.getServicesGroup();
		Set<String> list2 = sDao.getAlarmJF();
		for (VServicesId s : list) {
			if (list2.contains(s.getName().replace("JF-", ""))) {
				s.setStatus(5);
			}
		}
		data = new HashMap<>();
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 获取机房报警(暂时不做门禁和摄像头报警)
	 */
	public String getAlarm() {
		Long time = System.currentTimeMillis();
		SensorsDao sDao = new SensorsDaoImp();
		DeviceInfoDao dInfoDao = new DeviceDaoImp();
		ZSwitchDao swDao = new ZSwitchDaoImp();
		List<String> list = swDao.getAlarmDevice();

		List<Object[]> speedList = sDao.getAlarmInfo();
		List<Object[]> hkList = dInfoDao.getHKAlarmInfo();
		String s = "";
		if (list != null && list.size() > 0) {
			for (String sd : list) {
				s = s + " " + sd + "告警";
			}
		}
		for (Object[] o : hkList) {
			s = s + " " + (o[0].toString().replace("JF-", "")) + o[1] + "异常";
		}
		for (Object[] o : speedList) {
			s = s + " " + o[0] + "-" + o[1] + "异常";
		}

		if (s.length() == 0) {
			result = R.getJson(1, "", "");
		} else {
			result = R.getJson(0, s, "");
		}
		System.out.println(System.currentTimeMillis() - time);
		return SUCCESS;
	}

	/**
	 * 获取机房报警(没有空调报警)
	 */
	public String getAlarm1() {
		Long time = System.currentTimeMillis();
		SensorsDao sDao = new SensorsDaoImp();
		DeviceInfoDao dInfoDao = new DeviceDaoImp();
		ZSwitchDao swDao = new ZSwitchDaoImp();
		List<String> list = swDao.getAlarmDevice();

		List<Object[]> speedList = sDao.getAlarmInfo();
		List<Object[]> hkList = dInfoDao.getHKAlarmInfo();
		List<Map<String, String>> list2 = new ArrayList<>();

		if (list != null && list.size() > 0) {
			for (String sd : list) {
				Map<String, String> map = new HashMap<>();
				map.put("jf", "开关量");
				map.put("desc", sd + "告警");
				list2.add(map);
			}
		}
		for (Object[] o : hkList) {
			Map<String, String> map = new HashMap<>();
			map.put("jf", o[0].toString().replace("JF-", ""));
			map.put("desc", o[0].toString().replace("JF-", "") + o[1] + "异常");
			list2.add(map);
		}
		for (Object[] o : speedList) {
			Map<String, String> map = new HashMap<>();
			map.put("jf", "" + o[0]);
			map.put("desc", o[0] + "" + o[1] + "异常");
			list2.add(map);
		}
		if (list2.size() == 0) {
			result = R.getJson(0, "", list2);
			return SUCCESS;
		}
		result = R.getJson(1, "", list2);
		System.out.println(System.currentTimeMillis() - time);
		return SUCCESS;
	}

	/**
	 * 获取机房列表
	 */
	public String getGroupList() {
		GroupsDao gDao = new GroupsDaoImp();
		List<String> list = gDao.getGroupList();
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
		for (String name : list) {
			Map<String, String> map = new HashMap<>();
			map.put("name", name.replace("JF-", ""));
			list2.add(map);
		}
		data = new HashMap<>();
		data.put("list", list2);
		System.out.println(JSONArray.fromObject(list).toString());
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/*****
	 * 二级界面，获取对应机房的所有设备，按类型按设备取出所有数据(传入设备间名)
	 ******/
	public String getAllHostsByGroupName() {// 该Action在点击地图时启动,可以找出该地点下的所有主机并获取出分类信息
		data = new HashMap<>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("groupName", groupName.replace("JF-", ""));

		SensorsDao sDao = new SensorsDaoImp();
		List<VSensorsId> WATER = sDao.getSensorsByType2(0, -1, (short) 16);
		List<VSensorsId> TEMP = sDao.getSensorsByType2(0, -1, (short) 48);
		List<VSensorsId> WET = sDao.getSensorsByType2(0, -1, (short) 49);
		List<VSensorsId> AIRCONDITION = sDao.getSensorsByType2(0, -1,
				(short) 224);
		for (VSensorsId v : AIRCONDITION) {
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
		data.put("WATER", WATER);
		data.put("TEMP", TEMP);
		data.put("WET", WET);
		data.put("AIRCONDITION", AIRCONDITION);

		List<Map<String, String>> DC = getDeviceInfoList("门禁");
		List<Map<String, String>> IPC = getDeviceInfoList("摄像头");
		data.put("DC", DC);
		data.put("IPC", IPC);

		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	// public String getTempByGroupName() {
	// data = new HashMap<>();
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// session.put("groupName", groupName.replace("JF-", ""));
	//
	// SensorsDao sDao = new SensorsDaoImp();
	// List<VSensorsId> TEMP = sDao.getSensorsByType2(0, -1, (short) 48);
	// data.put("TEMP", TEMP);
	// data.put("total", TEMP.size());
	// result = R.getJson(1, "", data);
	// return SUCCESS;
	// }
	//
	// public String getWetByGroupName() {
	// data = new HashMap<>();
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// session.put("groupName", groupName.replace("JF-", ""));
	//
	// SensorsDao sDao = new SensorsDaoImp();
	// List<VSensorsId> WET = sDao.getSensorsByType2(0, -1, (short) 49);
	// data.put("WET", WET);
	// data.put("total", WET.size());
	// result = R.getJson(1, "", data);
	// return SUCCESS;
	// }
	//
	// public String getWaterLoggingByGroupName() {
	// data = new HashMap<>();
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// session.put("groupName", groupName.replace("JF-", ""));
	//
	// SensorsDao sDao = new SensorsDaoImp();
	// List<VSensorsId> WATER = sDao.getSensorsByType2(0, -1, (short) 16);
	// data.put("WATER", WATER);
	// data.put("total", WATER.size());
	// result = R.getJson(1, "", data);
	// return SUCCESS;
	// }
	//
	// public String getAirConditionByGroupName() {
	// data = new HashMap<>();
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// session.put("groupName", groupName.replace("JF-", ""));
	//
	// SensorsDao sDao = new SensorsDaoImp();
	// List<VSensorsId> AIRCONDITION = sDao.getSensorsByType2(0, -1,
	// (short) 224);
	// for (VSensorsId v : AIRCONDITION) {
	// if (v.getSensorvalue() > 0) {
	// String b = Long.toHexString(v.getSensorvalue().longValue());
	// String c = "00000000".substring(b.length()) + b;
	// Integer num = Integer.parseInt(c.substring(6, 8), 16);
	// Double temp = Double.parseDouble("" + num) / 2;
	// Integer wet = Integer.parseInt(c.substring(4, 6), 16);
	// Integer stateNum = Integer.parseInt(c.substring(2, 4), 16);
	// v.setTemp(temp);
	// v.setWet(wet);
	// v.setState(stateNum > 0 ? 1 : 0);
	// v.setRunModel(SpeedUtils.getState(stateNum));
	// }
	// }
	// data.put("AIRCONDITION", AIRCONDITION);
	// data.put("total", AIRCONDITION.size());
	// result = R.getJson(1, "", data);
	// return SUCCESS;
	// }
	//
	// public String getDCByGroupName() {
	// data = new HashMap<>();
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// session.put("groupName", groupName.replace("JF-", ""));
	//
	// List<Map<String, String>> DC = getDeviceInfoList("门禁");
	// data.put("DC", DC);
	// data.put("total", DC.size());
	// result = R.getJson(1, "", data);
	// return SUCCESS;
	// }
	//
	// public String getIPCByGroupName() {
	// data = new HashMap<>();
	// Map<String, Object> session = ActionContext.getContext().getSession();
	// session.put("groupName", groupName.replace("JF-", ""));
	//
	// List<Map<String, String>> IPC = getDeviceInfoList("摄像头");
	// data.put("IPC", IPC);
	// data.put("total", IPC.size());
	// result = R.getJson(1, "", data);
	// return SUCCESS;
	// }

	/**
	 * 获取设备列表
	 */
	private List<Map<String, String>> getDeviceInfoList(String type) {
		DeviceInfoDao dInfoDao = new DeviceDaoImp();
		List<VItemValueId> list = dInfoDao.getHostList(type, 0, -1);
		List<Map<String, String>> li = new ArrayList<>();
		for (VItemValueId v : list) {
			Map<String, String> infoMap = new HashMap<String, String>();
			infoMap.put("hostIp", v.getHost());
			infoMap.put("hostId", "" + v.getHostid());
			infoMap.put("location", v.getGroupname());
			switch (type) {
			case "门禁":
				infoMap.put("notice", "" + v.getNotice());
				infoMap.put("deviceName", "" + v.getDeviceName());
				String s1[] = v.getName().split(",");
				String s2[] = v.getValue().split(",");
				infoMap.put(s1[0], s2[0]);
				infoMap.put(s1[1], s2[1]);
				li.add(infoMap);
				break;
			case "摄像头":
				infoMap.put(v.getName(), v.getValue());
				infoMap.put("deviceName", v.getDeviceName());
				infoMap.put("nvrIp", v.getNvrIp());
				li.add(infoMap);
			}
		}
		return li;
	}

	// /**
	// * 获取设备列表
	// */
	// private List<Map<String, String>> getDeviceInfoList(Long type) {
	// GroupsDao gDao = new GroupsDaoImp();
	// List<VHostGroupId> list = gDao.getLiveHostByGroupid(type, 0, -1);
	//
	// List<Map<String, String>> li = new ArrayList<>();
	// ItemsDao iDao = new ItemsDaoImp();
	// HistoryTextDao htDao = new HistoryTextDaoImp();
	// HostDao hDao = new HostDaoImp();
	// DeviceInfoDao dInfoDao = new DeviceDaoImp();
	// for (VHostGroupId v : list) { // 遍历每一个主机
	// Long id = v.getHostid(); // 主机id
	// String ip = v.getHost(); // 主机ip
	//
	// List<Items> iList = iDao.getItemsByHostid(id);
	//
	// Map<String, String> infoMap = new HashMap<String, String>();
	// infoMap.put("hostId", "" + id);
	// infoMap.put("hostIp", ip);
	// String location = hDao.getHostGroup(id);
	// if (type == 9) {
	// String nvrIp = dInfoDao.getNVRIP(ip);
	// if (nvrIp == null || nvrIp.equals("")) {
	// nvrIp = "192.168.116.251";
	// }
	// infoMap.put("nvrIp", nvrIp);
	// }
	// if (location != null) {
	// infoMap.put("location", location.replace("JF-", ""));
	// } else {
	// infoMap.put("location", "");
	// }
	// for (Items i : iList) {
	// HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
	// infoMap.put(i.getName(), ht == null ? "" : ht.getValue());
	// }
	// ZHostConfigDao hConfigDao = new ZHostConfigDaoImp();
	// ZHostConfig hostConfig = hConfigDao.getHostConfig(ip);
	// infoMap.put("notice", hostConfig.getNotice() + "");
	// li.add(infoMap);
	// }
	// return li;
	// }

	/**
	 * 将传入的机房id存入缓存中
	 */
	public String selectGroup() {
		System.out.println("缓存参数为groupName:" + groupName);
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("groupName", groupName);
		result = R.getJson(1, "", "");
		return SUCCESS;
	}

	/**
	 * 重置缓存，清除机房选择
	 */
	public String clearSession() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("groupName");
		session.remove("start_time_l");
		session.remove("end_time_l");
		session.remove("start_time_sa");
		session.remove("end_time_sa");
		result = R.getJson(1, "", "");
		return SUCCESS;
	}

	public Long getGroupId() {

		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
