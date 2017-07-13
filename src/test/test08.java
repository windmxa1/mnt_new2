package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.action.GetDCListAction;
import org.action.GetStatisticsAction;
import org.dao.EventsDao;
import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.ServicesDao;
import org.dao.ServicesLinksDao;
import org.dao.imp.EventsDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ServicesDaoImp;
import org.dao.imp.ServicesLinksDaoImp;
import org.model.Services;
import org.model.ServicesLinks;
import org.tool.ChangeTime;

public class test08 {
	private Long groupId = (long) 12;
	private String address = "会展中心";
	public static void main(String[] args) {
//		EventsDao eventsDao = new EventsDaoImp();
////		System.out.println(eventsDao.getTotalFail((long) 10151));
//		
//		HostDao hdDao = new HostDaoImp();
//		List list = hdDao.getHostList();
//		String JsonArry = JSONArray.fromObject(list).toString();
//		System.out.println(JsonArry);
////		10211
//		System.out.println(eventsDao.getTotalFail((long) 10211));
//		System.out.println(eventsDao.getTotalFail(((long) 10151)));
//		GetStatisticsAction getStatisticsAction = new GetStatisticsAction();
//		getStatisticsAction.getErr();
//		getStatisticsAction.getErr1();
//		getStatisticsAction.getFailure();
//		getStatisticsAction.getErr2();
//		List<Map<String,String>> l =  eventsDao.getFailEventsByHostId((long) 10211);
//		String JsonArry1 = JSONArray.fromObject(l).toString();
//		System.out.println(JsonArry1);
//		
//		GetDCListAction getDCListAction = new GetDCListAction();
//		getDCListAction.getDcEvents();
		
		String s2 = "2016/09/30 14:28:58";
		String[] s3 = s2.split("/");
		String yyyy = s3[0];
		String MM = s3[1];
		String dd = s3[2].split(" ")[0];
		String time = s3[2].split(" ")[1];
		String a =yyyy+"-"+MM+"-"+dd+" "+time;
		System.out.println(ChangeTime.date2TimeStamp(a, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(Integer.parseInt(ChangeTime.date2TimeStamp(a, "yyyy-MM-dd HH:mm:ss")));
		System.out.println(yyyy+"-"+MM+"-"+dd+" "+time);
	}
}
