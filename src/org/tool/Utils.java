package org.tool;

import org.dao.HostDao;
import org.dao.ItemsDao;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.model.Functions;
import org.model.Hosts;
import org.model.Items;

public class Utils {
	private Integer Jan = 0, Feb = 0, Mar = 0, Apr = 0, May = 0, Jun = 0,
			Jul = 0, Aug = 0, Sep = 0, Oct = 0, Dec = 0, Nov = 0;

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

	/**
	 * 将字节数组转换成十六进制字符串
	 * @param byteArray
	 * @return
	 * @author A客
	 */
	public static String byteArrayToHexStr(byte[] byteArray) {
		if (byteArray == null) {
			return null;
		}
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[byteArray.length * 2];
		for (int j = 0; j < byteArray.length; j++) {
			int v = byteArray[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	/**
	 * @param str
	 * @return
	 * @author A客
	 */
	public static byte[] hexStrToByteArray(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return new byte[0];
		}
		byte[] byteArray = new byte[str.length() / 2];
		for (int i = 0; i < byteArray.length; i++) {
			String subStr = str.substring(2 * i, 2 * i + 2);
			byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
		}
		return byteArray;
	}
	/**
	 * 生成6位随机数
	 * 
	 * @return
	 */
	public static String ran6() {
		Double a = 100000 + Math.random() * 899999;
		return "" + a.intValue();
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
