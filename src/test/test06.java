package test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.action.GetStatisticsAction;
import org.dao.EventsDao;
import org.dao.imp.EventsDaoImp;

public class test06 {
	public static void main(String[] args) {
//		String date = TimeStamp2Date("1473404260", "dd");
//		System.out.println(date);
		
//		String timeStamp = timeStamp();  
//        System.out.println("timeStamp="+timeStamp);  
//          
//        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");  
//        System.out.println("date="+date);  
//          
//        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");  
//        System.out.println(timeStamp2);  
//		
		EventsDao eDao = new EventsDaoImp();
		List<Map<String, String>> list2= eDao.getIPCSingleList();
		String JsonArry = JSONArray.fromObject(list2).toString();
		System.out.println(JsonArry);
		
		
		
		GetStatisticsAction getStatisticsAction = new GetStatisticsAction();
		getStatisticsAction.getFailure();
//		getStatisticsAction.execute();
	}
	public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
	/** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }  
      
    //  输出结果：  
    //  timeStamp=1417792627  
    //  date=2014-12-05 23:17:07  
    //  1417792627  
	public static String TimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat(formats)
				.format(new java.util.Date(timestamp));
		return date;
	}
}
