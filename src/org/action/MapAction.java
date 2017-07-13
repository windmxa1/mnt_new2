package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.AlarmTabDao;
import org.dao.DeviceInfoDao;
import org.dao.EventsDao;
import org.dao.FunctionsDao;
import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.HostInventoryDao;
import org.dao.ItemsDao;
import org.dao.JfHostDao;
import org.dao.ServicesDao;
import org.dao.ServicesLinksDao;
import org.dao.imp.AlarmTabDaoImp;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.EventsDaoImp;
import org.dao.imp.FunctionsDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.HostInventoryDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.JfHostDaoImp;
import org.dao.imp.ServicesDaoImp;
import org.dao.imp.ServicesLinksDaoImp;
import org.model.Functions;
import org.model.HostInventory;
import org.model.Hosts;
import org.model.Items;
import org.model.Services;
import org.model.ServicesLinks;
import org.tool.ChangeTime;
import org.tool.Utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport {
	private List result;
	private Long groupId ;
	private String address = "会展中心";
	private static Integer Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0,
			Jun = 0, Jul = 0, Aug = 0, Sep = 0, Oct = 0, Nov = 0, Dec = 0;

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	/*****
	 * 获取机房的地址，状态，名称
	 ******/
	public String execute() throws Exception {
		ServicesDao sDao = new ServicesDaoImp();
		List<Services> servicesList = sDao.getServicesGroup();
		JfHostDao jfDao = new JfHostDaoImp();
		List<Map<String, String>> list = new ArrayList();
		for (Services s : servicesList) {
			String sName = s.getName();
			String[] a = sName.split("-");
			Map<String, String> address = new HashMap<String, String>();
			System.out.println(a[1] + "," + a[2]);
			address.put("groupId", s.getServiceid().toString());
			address.put("groupName", a[1]);
			address.put("groupAdreess", a[2]);
			AlarmTabDao aDao = new AlarmTabDaoImp();
			Long Count = (long) 0;
			List<String> k = jfDao.getHostip(s.getServiceid());
			for (String ip : k) {
				Count = Count + aDao.countAlarm(ip);
			}
			if (s.getStatus() == 5) {
				address.put("groupStatus", s.getStatus().toString());
			} else if (Count > 0) {// 只要有未处理的告警记录就显示状态4
				address.put("groupStatus", "" + 4);
			} else {
				address.put("groupStatus", s.getStatus().toString());
			}

			Hosts h = Utils.getHostsByServiceId(s.getServiceid());
			if (h != null) {
				HostInventoryDao hiDao = new HostInventoryDaoImp();
				HostInventory hi = hiDao.getHostInventory(h.getHostid());

				address.put("location_lat", hi.getLocationLat());
				address.put("location_lon", hi.getLocationLon());
			} else {
				address.put("location_lat", "错误信息，该机房下没有任何设备");
				address.put("location_lon", "错误信息，该机房下没有任何设备");
			}
			list.add(address);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	/**
	 * 将传入的机房id存入缓存中
	 */
	public String getSes() {
		System.out.println("缓存参数为groupId:"+groupId);
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("groupId", groupId);
		return SUCCESS;
	}

	/*****
	 * 二级界面，获取对应机房的所有设备，按类型按设备取出所有数据
	 ******/
	public String getAllHostsByGroupid() throws Exception {// 该Action在点击地图时启动,可以找出该地点下的所有主机并获取出分类信息
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("groupId", groupId);
		ServicesDao sDao = new ServicesDaoImp();
		ServicesLinksDao slDao = new ServicesLinksDaoImp();
		GroupsDao gDao = new GroupsDaoImp();
		/****
		 * @description (long)12是写死的groupid，用于测试数据！记得改回来！！！！！！！！！！！！！
		 ****/
		// 约定机房名称
		switch (address) {
		case "会展中心":
			groupId = (long) 12;
			break;
		case "少年宫":
			groupId = (long) 13;
			break;
		case "深圳":
			groupId = (long) 14;
			break;
		case "龙华":
			groupId = (long) 15;
			break;
		default:
			break;
		}
		List<ServicesLinks> hostlist = slDao.getDownServices(groupId);
		Map<String, Map<String, String>> IPCMap = new HashMap<>();
		Map<String, Map<String, String>> DCMap = new HashMap<>();
		Map<String, Map<String, String>> NVRMap = new HashMap<>();
		for (ServicesLinks host : hostlist) {// 遍历机房内所有主机hostlist
			// System.out.println(host.getLinkid());
			List<ServicesLinks> parmList = slDao.getDownServices(host
					.getServicedownid());
			// 输出上层服务ID
			System.out.println("serviceupid:" + host.getServiceupid());
			for (ServicesLinks parm : parmList) {// 遍历主机属性parmlist
				// 输出上层服务ID
				System.out.println("serviceupid:" + parm.getServiceupid());
				Services s = sDao.getServicesById(parm.getServicedownid());

				System.out.println(s.getName());
				Long triggerId = null;
				Services s1 = new Services();
				if (s.getName().contains("海康摄像头")) {// 如果包含字段海康摄像头则再进一步进行遍历
					List<ServicesLinks> HKIPCList = slDao.getDownServices(s
							.getServiceid());
					for (ServicesLinks HKIPCparm : HKIPCList) {// 获取海康摄像头下的属性，也就是下属服务
						s1 = sDao
								.getServicesById((HKIPCparm.getServicedownid()));
						triggerId = s1.getTriggerid();
					}
				} else {
					triggerId = s.getTriggerid();
				}
				if (triggerId != null) {
					// 这是从triggerid得到hostid
					Hosts h = functionToHost(triggerId);
					System.out.println("status=" + s.getStatus());
					// h代表的是一个一级service下的一个主机
					List groupL = gDao.getgroupIdsByhostId(h.getHostid());
					// 配置时注意，组ID或许不是8，9，10，可能需要修改
					// ##########！！！约定组号8，9，10！！！##############
					Services s2 = sDao.getServicesById(parm.getServiceupid());
					for (Object o : groupL) {
						String group = "" + (Long) o;
						// System.out.println(group);
						switch (group) {
						case "8":
							// 注释掉的代码对应下面被注释掉的3个MAP
							// IPCHosts.add(h);
							IPCMap = getMap(h, s1.getStatus(),
									parm.getServiceupid(), group);
							break;
						case "9":
							// DCHosts.add(h);
							DCMap = getMap(h, s2.getStatus(),
									parm.getServiceupid(), group);
							break;
						case "10":
							// NVRHosts.add(h);
							NVRMap = getMap(h, s2.getStatus(),
									parm.getServiceupid(), group);
							break;
						default:
							break;
						}
					}
				}
			}
		}

		Map<String, Map<String, Map<String, String>>> DeviceMap = new HashMap<>();
		DeviceMap.put("DC", DCMap);
		DeviceMap.put("IPC", IPCMap);
		DeviceMap.put("NVR", NVRMap);
		/*
		 * 依次存放 首先getMap方法中infoMap存放主机对应参数，之后用map存放
		 * 主机的唯一标识以及主机对应的所有参数组成的map，之后用deviceMap存放主机类型的唯一标识以及前次所得的Map
		 * ,如果还需要用到组ID，可以考虑再加多一层组ID标识，以此区分机房组
		 */
		List<Map<String, Map<String, Map<String, String>>>> deviceList = new ArrayList<Map<String, Map<String, Map<String, String>>>>();
		deviceList.add(DeviceMap);
		result = deviceList;
		String JsonArry = JSONArray.fromObject(result).toString();

		System.out.println(JsonArry);

		return SUCCESS;
	}

	/*****
	 * 获取对应机房当月所有设备类型的异常统计数目
	 ******/
	public String getErrorCount() throws Exception {
		Long countIPCException = (long) 0;
		Long countDCException = (long) 0;
		Long countNVRException = (long) 0;
		ServicesDao sDao = new ServicesDaoImp();
		ServicesLinksDao slDao = new ServicesLinksDaoImp();
		GroupsDao gDao = new GroupsDaoImp();
		/****
		 * @description (long)12是写死的groupid，用于测试数据！记得改回来！！！！！！！！！！！！！
		 ****/
		// 约定机房名称
		switch (address) {
		case "会展中心":
			groupId = (long) 12;
			break;
		case "少年宫":
			groupId = (long) 13;
			break;
		case "深圳":
			groupId = (long) 14;
			break;
		case "龙华":
			groupId = (long) 15;
			break;
		default:
			break;
		}
		EventsDao eDao = new EventsDaoImp();
		AlarmTabDao aDao = new AlarmTabDaoImp();
		List<ServicesLinks> hostlist = slDao.getDownServices(groupId);
		for (ServicesLinks host : hostlist) {// 遍历机房内所有主机hostlist
			// System.out.println(host.getLinkid());
			List<ServicesLinks> parmList = slDao.getDownServices(host
					.getServicedownid());
			// 输出上层服务ID

			System.out.println("serviceupid:" + host.getServiceupid());
			for (ServicesLinks parm : parmList) {// 遍历主机属性parmlist
				// 输出上层服务ID
				System.out.println("serviceupid:" + parm.getServiceupid());
				Services s = sDao.getServicesById(parm.getServicedownid());

				// System.out.println(s.getName());
				Long triggerId = null;
				Services s1 = new Services();
				if (s.getName().indexOf("告警") == -1) {
					triggerId = s.getTriggerid();
				}
				if (s.getName().indexOf("海康摄像头") == 0) {// 如果包含字段海康摄像头则再进一步进行遍历
					List<ServicesLinks> HKIPCList = slDao.getDownServices(s
							.getServiceid());
					for (ServicesLinks HKIPCparm : HKIPCList) {// 获取海康摄像头下的属性，也就是下属服务
						s1 = sDao
								.getServicesById((HKIPCparm.getServicedownid()));
						triggerId = s1.getTriggerid();
					}
				}
				System.out.println("TriggerId=" + triggerId);
				if (triggerId != null) {
					// 这是从triggerid得到hostid
					Hosts h = functionToHost(triggerId);
					System.out.println("status=" + s.getStatus());
					// h代表的是一个一级service下的一个主机
					List groupL = gDao.getgroupIdsByhostId(h.getHostid());
					// 配置时注意，组ID或许不是8，9，10，可能需要修改
					// ##########！！！约定组号8，9，10！！！##############

					for (Object o : groupL) {
						String group = "" + (Long) o;
						switch (group) {
						case "8":
							countIPCException = eDao
									.getTotalFail(h.getHostid())
									+ aDao.countCurrentAlarm(h.getHostid())
									+ countIPCException;
							getFailureMap(h.getHostid());
							break;
						case "9":
							countDCException = eDao.getTotalErr(h.getHostid())
									+ countDCException;
							getFailureMap(h.getHostid());
							break;
						case "10":
							countNVRException = eDao
									.getTotalFail(h.getHostid())
									+ countNVRException;
							getFailureMap(h.getHostid());
							break;
						default:
							break;
						}
					}
				}
			}
		}
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("deviceType", "IPC");
		map.put("errorNum", countIPCException.toString());
		list.add(map);
		map = new HashMap<>();
		map.put("deviceType", "DC");
		map.put("errorNum", countDCException.toString());
		list.add(map);
		map = new HashMap<>();
		map.put("deviceType", "NVR");
		map.put("errorNum", countNVRException.toString());
		list.add(map);

		result = list;

		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);

		return SUCCESS;
	}

	/*****
	 * 获取对应机房所有设备每个月的异常统计数目
	 ******/
	public String getMonthErr() {
		List<Map<String, String>> list = new ArrayList<>();
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
		list.add(timeMap);
		result = list;

		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	/**
	 * 按月份进行统计，统计每月的异常总数
	 */
	private void getFailureMap(Long hostid) {
		EventsDao eDao = new EventsDaoImp();
		List<Map<String, String>> list = eDao.getErrEventsByHostId(hostid);
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
	}

	private Map<String, Map<String, String>> getMap(Hosts host, Integer status,
			Long serviceid, String group) {
		DeviceInfoDao dDao = new DeviceDaoImp();
		Map<String, String> infoMap = new HashMap<String, String>();// 保存主机属性及属性值
		infoMap = dDao.getInfoMap(host.getHostid(), group);
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

		infoMap.put("hostid", "" + host.getHostid());
		infoMap.put("IP", host.getHost());
		infoMap.put("status", "" + status);
		infoMap.put("groupId", "" + groupId);

		map.put(host.getHostid().toString(), infoMap);
		return map;
	}

	private Hosts functionToHost(Long triggerid) {// trigger---->host
		FunctionsDao fDao = new FunctionsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		HostDao hDao = new HostDaoImp();

		Functions f = fDao.getFunctionByTrigger(triggerid);
		Items i = iDao.getItemByItemid(f.getItemid());
		Hosts h = hDao.getHostByHostid(i.getHostid());

		return h;
	}

	public Long getGroupId() {

		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
