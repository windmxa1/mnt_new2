package org.tool;

import java.util.List;
import java.util.Map;

import org.dao.AlarmTabDao;
import org.dao.EventsDao;
import org.dao.FunctionsDao;
import org.dao.HostDao;
import org.dao.ItemsDao;
import org.dao.ServicesDao;
import org.dao.ServicesLinksDao;
import org.dao.imp.AlarmTabDaoImp;
import org.dao.imp.EventsDaoImp;
import org.dao.imp.FunctionsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.ServicesDaoImp;
import org.dao.imp.ServicesLinksDaoImp;
import org.model.AlarmTab;
import org.model.Functions;
import org.model.Hosts;
import org.model.Items;
import org.model.Services;
import org.model.ServicesLinks;

public class Utils {
	private Integer Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0,
			Jul = 0, Aug = 0, Sep = 0, Oct = 0, Dec = 0, Nov = 0;

	/**
	 * 得到当前设备每个月的故障统计数
	 */
	public void getFailureMap(Long hostid) {
		EventsDao eDao = new EventsDaoImp();
		List<Map<String, String>> list = eDao.getFailEventsByHostId(hostid);
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
	/**
	 * 根据服务id找到相应设备，找到一级服务下级的所有设备，但是只需要返回任意一台设备即可
	 */
	public static Hosts getHostsByServiceId(Long serviceid) {
		ServicesLinksDao slDao = new ServicesLinksDaoImp();
		ServicesDao sDao = new ServicesDaoImp();
		List<ServicesLinks> hostlist = slDao.getDownServices(serviceid);
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
					// 这是从triggerid得到hostid,找到设备即可返回
					Hosts h = functionToHost(triggerId);
					return h;
				}
			}
		}
		return null;
	}
	/**
	 * 将时间格式规范化,如2016/09/30 14:28:58 转成2016-09-30 14:28:58
	 */
	public static String getTime(String s2){
		String[] s3 = s2.split("/");
		String yyyy = s3[0];
		String MM = s3[1];
		String dd = s3[2].split(" ")[0];
		String time = s3[2].split(" ")[1];
		return yyyy+"-"+MM+"-"+dd+" "+time; 
	}
//	/**
//	 * 用于保存告警历史记录
//	 */
//	public void saveAlarmHistory(String deviceType,String Avalue,String channel,Long hostId,String hostIp){
////		String Avalue = "";//录像机的告警字符串
////		String channel = "";//从关联表中取出的摄像头对应的通道号
////		String deviceType = "ipc";//设备类型
//		
//		String clock = "";
//		String[] s = Avalue.split(";");
//		AlarmTabDao aTabDao = new AlarmTabDaoImp();
//		for (String s1 : s) {
//			String[] s2 = s1.split("-");
//			if (s2[3].equals("" + channel)) {
//				String s4 = Utils.getTime(s2[1]);
//				clock = s4;
////				clock = Integer.parseInt(ChangeTime.date2TimeStamp(s4,"yyyy-MM-dd HH:mm:ss"));
//				String alarmType = s2[0];
//				AlarmTab alarmTab = new AlarmTab(deviceType, alarmType, clock, hostId, hostIp);
//				aTabDao.insert(alarmTab);
//			}
//		}
//	}

	private static Hosts functionToHost(Long triggerid) {// trigger---->host
		FunctionsDao fDao = new FunctionsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		HostDao hDao = new HostDaoImp();

		Functions f = fDao.getFunctionByTrigger(triggerid);
		Items i = iDao.getItemByItemid(f.getItemid());
		Hosts h = hDao.getHostByHostid(i.getHostid());

		return h;
	}

	public Integer getJan() {
		return Jan;
	}

	public Integer getFeb() {
		return Feb;
	}

	public Integer getMar() {
		return Mar;
	}

	public Integer getApr() {
		return Apr;
	}

	public Integer getMay() {
		return May;
	}

	public Integer getJun() {
		return Jun;
	}

	public Integer getJul() {
		return Jul;
	}

	public Integer getAug() {
		return Aug;
	}

	public Integer getSep() {
		return Sep;
	}

	public Integer getOct() {
		return Oct;
	}

	public Integer getDec() {
		return Dec;
	}

	public Integer getNov() {
		return Nov;
	}
}
