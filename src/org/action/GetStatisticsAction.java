package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.DeviceInfoDao;
import org.dao.EventsDao;
import org.dao.FunctionsDao;
import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.ItemsDao;
import org.dao.ServicesDao;
import org.dao.ServicesLinksDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.EventsDaoImp;
import org.dao.imp.FunctionsDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.ServicesDaoImp;
import org.dao.imp.ServicesLinksDaoImp;
import org.model.Functions;
import org.model.Hosts;
import org.model.Items;
import org.model.Services;
import org.model.ServicesLinks;
import org.tool.ChangeTime;
import org.tool.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class GetStatisticsAction extends ActionSupport {
	private List result;
	private String DeviceType = "DC";

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	/***************
	 *  获取单类设备的故障统计数,关键在于priority 用优先级来区分是什么事件，设备故障还是
	 */
	public String execute() {
		EventsDao eDao = new EventsDaoImp();
		List<Map<String, String>> list = eDao.getFailureListWithoutTime();
		Integer DCCountError = 0;
		Integer IPCCountError = 0;
		Integer NVRCountError = 0;
		Map<String, String> ErrorCount = new HashMap<String, String>();
		for (Map<String, String> map : list) {
			Integer alerts = Integer.parseInt(map.get("alerts"));
			switch (map.get("name")) {
			case "海康录像机":
				System.out.println(map.get("host"));
				IPCCountError = IPCCountError + alerts;
				break;
			case "海康网络摄像头":
				System.out.println(map.get("host"));
				NVRCountError = NVRCountError + alerts;
				break;
			case "海康门禁":
				System.out.println(map.get("host"));
				DCCountError = DCCountError + alerts;
				break;
			default:
				break;
			}
		}

		ErrorCount.put("海康录像机", IPCCountError.toString());
		ErrorCount.put("海康网络摄像头", NVRCountError.toString());
		ErrorCount.put("海康门禁", DCCountError.toString());
		List<Map<String, String>> list2 = new ArrayList<>();
		list2.add(ErrorCount);

		result = list2;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	/**
	 * 当前月份 按设备类型列出对应的故障总数
	 */
	public String getErr() {
		EventsDao eDao = new EventsDaoImp();
		GroupsDao gDao = new GroupsDaoImp();
		// 找出所有主机
		Map<String, String> map = new HashMap<>();

		HostDao hDao = new HostDaoImp();
		List<Hosts> hostList = hDao.getHostList();
		Long CountDC = (long) 0;
		Long CountIPC = (long) 0;
		Long countNVR = (long) 0;
		for (Hosts h : hostList) {
			List gList = gDao.getgroupIdsByhostId(h.getHostid());
			for (Object o : gList) {
				String group = "" + (Long) o;
				// 8，9，10为约定组别，记得修改！！！！！！！！！
				switch (group) {
				case "8":
					CountIPC = eDao.getTotalFail(h.getHostid()) + CountIPC;
					break;
				case "9":
					CountDC = eDao.getTotalFail(h.getHostid()) + CountDC;
					break;
				case "10":
					countNVR = eDao.getTotalFail((h.getHostid())) + countNVR;
					break;
				default:
					break;
				}
			}
		}
		map.put("DC", "" + CountDC);
		map.put("IPC", "" + CountIPC);
		map.put("NVR", "" + countNVR);
		List<Map<String, String>> list = new ArrayList<>();
		list.add(map);
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();

		System.out.println(JsonArry);
		/*
		 * List<Object[]> dcList = gDao.getHostListByGroupid((long) 9); for
		 * (Object[] o : dcList) { Long hostid =
		 * Long.parseLong(o[0].toString()); eDao.getTotalErr(hostid); CountDC =
		 * eDao.getTotalErr(hostid) + CountDC; } DCMap.put("DC", "" + CountDC);
		 * 
		 * List<Object[]> ipcList = gDao.getHostListByGroupid((long) 8); for
		 * (Object[] o : ipcList) { Long hostid =
		 * Long.parseLong(o[0].toString()); eDao.getTotalErr(hostid); CountIPC =
		 * eDao.getTotalErr(hostid) + CountIPC; } IPCMap.put("DC", "" +
		 * CountIPC);
		 */

		return SUCCESS;
	}

	/**
	 * 当前月份 各个机房的故障总数
	 */
	public String getErr1() {
		ServicesDao sDao = new ServicesDaoImp();
		ServicesLinksDao slDao = new ServicesLinksDaoImp();
		List<Services> servicesList = sDao.getServicesGroup();
		EventsDao eDao = new EventsDaoImp();
		Map<String, String> map = new HashMap<>();
		for (Services s : servicesList) {// 遍历一次代表一个机房
			Long Count = (long) 0;
			String[] a = s.getName().split("-");
			System.out.println(s.getServiceid().toString());
			String groupName = a[1];
			List<ServicesLinks> hostlist = slDao.getDownServices(s
					.getServiceid());
			for (ServicesLinks host : hostlist) {// 遍历机房内所有主机hostlist
				// System.out.println(host.getLinkid());
				List<ServicesLinks> parmList = slDao.getDownServices(host
						.getServicedownid());
				// 输出上层服务ID
				System.out.println("serviceupid:" + host.getServiceupid());
				for (ServicesLinks parm : parmList) {// 遍历主机属性parmlist
					// 输出上层服务ID
					System.out.println("serviceupid:" + parm.getServiceupid());
					// s2 ---> 属性，设备状态或者告警或者下级设备，如摄像头等
					Services s2 = sDao.getServicesById(parm.getServicedownid());

					// System.out.println(s.getName());
					Long triggerId = null;
					Services s1 = new Services();
					if (s2.getName().indexOf("告警") == -1) {
						triggerId = s2.getTriggerid();
					}
					if (s2.getName().indexOf("海康摄像头") == 0) {// 如果包含字段海康摄像头则再进一步进行遍历
						List<ServicesLinks> HKIPCList = slDao
								.getDownServices(s2.getServiceid());
						for (ServicesLinks HKIPCparm : HKIPCList) {// 获取海康摄像头下的属性，也就是下属服务
							s1 = sDao.getServicesById((HKIPCparm
									.getServicedownid()));
							triggerId = s1.getTriggerid();
						}
					}
					if (triggerId != null) {
						Hosts h = functionToHost(triggerId);
						Count = Count + eDao.getTotalFail(h.getHostid());
					}
				}
			}
			// 遍历结束，将机房的故障次数插入MAP中
			map.put(groupName, "" + Count);

		}
		List<Map<String, String>> list = new ArrayList<>();
		list.add(map);
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	/**
	 * 获取每个月份对应的设备异常
	 * 
	 * @return
	 */
	public String getErr2() {
		Utils u = new Utils();
		HostDao hDao = new HostDaoImp();
		List<Hosts> hostList = hDao.getHostList();
		for (Hosts h : hostList) {
			u.getFailureMap(h.getHostid());
		}
		Map<String, String> timeMap = new HashMap<>();
		timeMap.put("01", "" + u.getJan());
		timeMap.put("02", "" + u.getFeb());
		timeMap.put("03", "" + u.getMar());
		timeMap.put("04", "" + u.getApr());
		timeMap.put("05", "" + u.getMay());
		timeMap.put("06", "" + u.getJun());
		timeMap.put("07", "" + u.getJul());
		timeMap.put("08", "" + u.getAug());
		timeMap.put("09", "" + u.getSep());
		timeMap.put("10", "" + u.getOct());
		timeMap.put("11", "" + u.getNov());
		timeMap.put("12", "" + u.getDec());
		List<Map<String, String>> list = new ArrayList<>();
		list.add(timeMap);
		result = list;

		String JsonArry = JSONArray.fromObject(result).toString();

		System.out.println(JsonArry);
		return SUCCESS;
	}
	
	
	
	/**
	 * 根据选择的设备类型显示该设备类型每个月的故障统计数目，也就是只能一次显示一类设备的故障统计数，默认值是门禁
	 * @return
	 */
	public String getFailure() {
		List<Map<String, Map<String, String>>> list = new ArrayList<>();
		Map<String, Map<String, String>> Map = new HashMap<>();
		System.out.println(DeviceType);
		switch (DeviceType) {
		case "DC":
			Map = getFailureMap("DC");
			break;
		case "IPC":
			Map = getFailureMap("IPC");
			break;
		case "NVR":
			Map = getFailureMap("NVR");
			break;
		default:
			break;
		}
		list.add(Map);
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();

		System.out.println(JsonArry);
		return SUCCESS;
	}

	private Map<String, Map<String, String>> getFailureMap(String deviceType) {
		List<Map<String, String>> list = new ArrayList<>();
		EventsDao eDao = new EventsDaoImp();
		switch (deviceType) {// 单个设备的故障记录，不是统计数，设备类型还会增加，记得修改！！！！！！！
		case "DC":
			list = eDao.getDCSingleList();
			break;
		case "IPC":
			list = eDao.getIPCSingleList();
			break;
		case "NVR":
			list = eDao.getNVRSingleList();
			break;
		default:
			break;
		}
		Map<String, Map<String, String>> failMap = new HashMap<>();
		Integer Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;
		for (Map<String, String> map : list) {
			String clock = ChangeTime.TimeStamp2Date(map.get("clock"), "MM");
			switch (clock) {
			case "01":
				Jan++;
				break;
			case "02":
				Feb++;
				break;
			case "03":
				Mar++;
				break;
			case "04":
				Apr++;
				break;
			case "05":
				May++;
				break;
			case "06":
				Jun++;
				break;
			case "07":
				Jul++;
				break;
			case "08":
				Aug++;
				break;
			case "09":
				Sep++;
				break;
			case "10":
				Oct++;
				break;
			case "11":
				Nov++;
				break;
			case "12":
				Dec++;
				break;
			default:
				break;
			}
		}
		Map<String, String> timeMap = new HashMap<>();
		timeMap.put("01", "" + Jan);
		timeMap.put("02", "" + Feb);
		timeMap.put("03", "" + Mar);
		timeMap.put("04", "" + Apr);
		timeMap.put("05", "" + May);
		timeMap.put("06", "" + Jun);
		timeMap.put("07", "" + Jul);
		timeMap.put("08", "" + Aug);
		timeMap.put("09", "" + Sep);
		timeMap.put("10", "" + Oct);
		timeMap.put("11", "" + Nov);
		timeMap.put("12", "" + Dec);
		failMap.put(deviceType, timeMap);
		return failMap;
	}

	/**
	 * 通过triggerid找到设备
	 * 
	 * @param triggerid
	 * @return
	 */
	private Hosts functionToHost(Long triggerid) {// trigger---->host
		FunctionsDao fDao = new FunctionsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		HostDao hDao = new HostDaoImp();

		Functions f = fDao.getFunctionByTrigger(triggerid);
		Items i = iDao.getItemByItemid(f.getItemid());
		Hosts h = hDao.getHostByHostid(i.getHostid());

		return h;
	}
}
