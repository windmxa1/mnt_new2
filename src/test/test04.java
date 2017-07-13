package test;

import org.action.MapAction;
import org.dao.AlarmTabDao;
import org.dao.imp.AlarmTabDaoImp;
import org.tool.ChangeTime;

public class test04 {
	public static void main(String[] args) throws Exception {
		MapAction mapAction = new MapAction();
		mapAction.execute();
//		mapAction.getAllHostsByGroupid();
//		mapAction.getErrorCount();
//		mapAction.getMonthErr();
		
//		String string = ChangeTime.TimeStamp2Date(""+1475117200, "yyyy-MM-dd HH:mm:ss");
//		System.out.println(string);
//		
//		
//		AlarmTabDao aDao = new AlarmTabDaoImp();
//		System.out.println(aDao.countCurrentAlarm((long) 10211));
//		mapAction.getErrorCount();
//		mapAction.getMonthErr();
//		EventsDao eDao = new EventsDaoImp();
//		Long result = eDao.getTotalErr((long) 10211);
//		System.out.println(result);
//		result++;
//		System.out.println(result);
		
		
//		System.out.println(ChangeTime.timeStamp());
//		System.out.println(ChangeTime.TimeStamp2Date(ChangeTime.timeStamp(), "YYYY-MM"));
//		String Current = ChangeTime.TimeStamp2Date(ChangeTime.timeStamp(), "YYYY-MM");
//		System.out.println(ChangeTime.date2TimeStamp(Current, "YYYY-MM"));
//		Long CurrentClock = Long.parseLong(ChangeTime.date2TimeStamp(Current, "YYYY-MM"));
//		System.out.println(CurrentClock);
	}
}
