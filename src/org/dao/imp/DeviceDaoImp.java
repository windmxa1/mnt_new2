package org.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.DeviceInfoDao;
import org.dao.HistoryTextDao;
import org.dao.HostDao;
import org.dao.HostInventoryDao;
import org.dao.ItemsDao;
import org.dao.ServicesDao;
import org.dao.ServicesLinksDao;
import org.dao.TriggersDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.DcEvents;
import org.model.HistoryText;
import org.model.HostInventory;
import org.model.Hosts;
import org.model.Items;
import org.model.Services;
import org.util.HibernateSessionFactory;

public class DeviceDaoImp implements DeviceInfoDao {
	// 是否应该传LIST进来循环处理每一种设备的全部信息
	public List<Map<String, String>> getDeviceInfo(List<Object[]> list,
			String group) { // 摄像头（待定：是否需要写3个设备的？？？）
		List<Map<String, String>> infoList = new ArrayList<Map<String, String>>();
		for (Object[] o : list) {
			String a = o[0].toString(); // 设备id,设备ip,name,groupid,**
			Long hostId = Long.parseLong(a);

			/***********************************
			 * @description 找到摄像头对应NVR，并从中取出摄像头对应告警信息
			 */
			ItemsDao iDao = new ItemsDaoImp();
			List<Items> iList = iDao.getItemsByHostid(hostId);//

			Map<String, String> infoMap = new HashMap<String, String>();
			String hostIp = o[1].toString();
			// 每次循环都把主机对应的items项存放到map中
			for (Items i : iList) { // 遍历对应ip的每一个items，获取itemid对应的value，并保存到map
				HistoryTextDao htDao = new HistoryTextDaoImp();
				HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
				// 传入的group参数代表组号，属于约定属性，记得修改！！！！！！
				if (group.equals("8") && i.getName().equals("deviceAlarm")) {// 根据获得的数据格式把NVR对应通道对应摄像头的告警信息取出
					// DeviceInfoDao dDao = new DeviceDaoImp();
					NvrIpcDao nvrIpcDao = new NvrIpcDaoImp();
					HostDao hDao = new HostDaoImp();
					String nvrHost = nvrIpcDao.getNvrByIpc(hostIp);
					Hosts nvrH = hDao.getHostByHostip(nvrHost);
					HistoryText ht1 = htDao.getDeviceAlarmByHostId(nvrH
							.getHostid());
					String Avalue = ht1.getValue();
					int channel = nvrIpcDao.getChannel(hostIp);
					String deviceAlarm = "";
					if (!Avalue.equals("")) {
						int k = 0;
						String[] s = Avalue.split(";");
						for (String s1 : s) {
							String[] s2 = s1.split("-");
							if (s2[3].equals("" + channel)) {// 通道号符合要求
								if (k == 0) {
									deviceAlarm = s2[0];
								} else {
									deviceAlarm = deviceAlarm + "," + s2[0];
								}
								k++;
							}
						}
					}
					ht.setValue(deviceAlarm);
				}
				String V = ht.getValue();
				// System.out.println("..."+V+"...");
				System.out.println(i.getName() + "----" + V + ".");
				infoMap.put(i.getName(), V); // 具体某个设备的全部信息

			}
			infoMap.put("hostIp", "" + o[1].toString());
			infoMap.put("hostId", a);

			HostInventoryDao hiDao = new HostInventoryDaoImp();

			HostInventory hi = hiDao.getHostInventory(hostId);
			infoMap.put("contact", hi.getContact());
			infoMap.put("deployment", hi.getDeployment_status());
			infoMap.put("location", hi.getLocation());
			infoMap.put("locationLat", hi.getLocationLat());
			infoMap.put("locationLon", hi.getLocationLon());
			infoMap.put("name", hi.getName());

			// 获取业务告警统一的状态，设备状态不需要进行显示
			ServicesDao sDao = new ServicesDaoImp();
			// sDao.getServiceByTriggerIds();
			TriggersDao tDao = new TriggersDaoImp();
			List triggerids = tDao.getTriggersByHostId(hostId);
			System.out.println(triggerids.toString());
			for (Object triggerid : triggerids) {
				// 通过triggerid向上找到对应的属性名称
				Services s = sDao.getServiceByTriggerId((Long) triggerid);
				if (s != null) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
							+ s.getName());
					ServicesLinksDao sLinksDao = new ServicesLinksDaoImp();
					Services s1 = sDao.getServicesById((sLinksDao
							.getUpServiceId(s.getServiceid())));
					System.out.println(s1.getName() + "," + s1.getStatus());
				}
			}

			infoList.add(infoMap);
		}
		return infoList;
	}

	public Map<String, String> getInfoMap(Long hostid,String group) {
		ItemsDao iDao = new ItemsDaoImp();
		HostDao hDao = new HostDaoImp();
		List<Items> iList = iDao.getItemsByHostid(hostid);//

		Map<String, String> infoMap = new HashMap<String, String>();
		String hostIp = hDao.getHostByHostid(hostid).getHost();
		// 每次循环都把主机对应的items项存放到map中
		for (Items i : iList) { // 遍历对应ip的每一个items，获取itemid对应的value，并保存到map
			HistoryTextDao htDao = new HistoryTextDaoImp();
			HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
			// 传入的group参数代表组号，属于约定属性，记得修改！！！！！！
			if (group.equals("8") && i.getName().equals("deviceAlarm")) {// 根据获得的数据格式把NVR对应通道对应摄像头的告警信息取出
				// DeviceInfoDao dDao = new DeviceDaoImp();
				NvrIpcDao nvrIpcDao = new NvrIpcDaoImp();
				String nvrHost = nvrIpcDao.getNvrByIpc(hostIp);
				Hosts nvrH = hDao.getHostByHostip(nvrHost);
//				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+nvrH.getHostid());
				HistoryText ht1 = htDao
						.getDeviceAlarmByHostId(nvrH.getHostid());
//				System.out.println(">>>>>>>>Ht.Value<<<<<<<<"+ht1.getValue());
				String Avalue = ht1.getValue();
				int channel = nvrIpcDao.getChannel(hostIp);
				String deviceAlarm = "";
				if (!Avalue.equals("")) {
					int k = 0;
					String[] s = Avalue.split(";");
					for (String s1 : s) {
						String[] s2 = s1.split("-");
						if (s2[3].equals("" + channel)) {// 通道号符合要求
							if (k == 0) {
								deviceAlarm = s2[0];
							} else {
								deviceAlarm = deviceAlarm + "," + s2[0];
							}
							k++;
						}
					}
				}
				ht.setValue(deviceAlarm);
			}
			String V = ht.getValue();
			System.out.println(i.getName() + "----" + V + ".");
			infoMap.put(i.getName(), V); // 具体某个设备的全部信息

		}
		infoMap.put("hostIp", ""+hostIp);
		infoMap.put("hostId", ""+hostid);

		HostInventoryDao hiDao = new HostInventoryDaoImp();

		HostInventory hi = hiDao.getHostInventory(hostid);
		infoMap.put("contact", hi.getContact());
		infoMap.put("deployment", hi.getDeployment_status());
		infoMap.put("location", hi.getLocation());
		infoMap.put("locationLat", hi.getLocationLat());
		infoMap.put("locationLon", hi.getLocationLon());
		infoMap.put("name", hi.getName());
		return infoMap;
	}

	public List<DcEvents> getDCEvents(String DcHost) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from DcEvents where host = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, DcHost);
			List<DcEvents> eventsList = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return eventsList;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
