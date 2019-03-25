package org.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeTime {
//	public static String TimeStamp2Date(String timestampString, String formats) {
//		Long timestamp = Long.parseLong(timestampString) * 1000;
//		String date = new java.text.SimpleDateFormat(formats)
//				.format(new java.util.Date(timestamp));
//		return date;
//	}
	/**
	 * 取得当前年月日
	 */
	public static Integer currentDate() {
		return Integer.parseInt(new SimpleDateFormat("YYYYMMdd")
				.format(new Date()));
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
     * 取得当前月份的第一天的时间戳（精确到秒）
     */
    public static String timeStamp1(){
    	try {
    		Date date= new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		String date1 = sdf.format(date);
//    		System.out.println(date1);
    		String s[] = date1.split("-");
    		String Year = s[0];
    		String Month = s[1];
    		String date2 = Year+"-"+Month+"-"+"01 00:00:00";
    		Date date3 = sdf.parse(date2);
    		return ""+(date3.getTime()/1000);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
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
