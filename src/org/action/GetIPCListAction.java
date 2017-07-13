package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.tool.GetRandom;

import com.opensymphony.xwork2.ActionSupport;

public class GetIPCListAction extends ActionSupport{
	private List result;		//json 返回值
	
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {	//待定：是否有null的判断,参照login
//		GroupsDao gDao = new GroupsDaoImp();
//		List<Object[]> li = gDao.getHostListByGroupid((long) 8);
//		
//		DeviceInfoDao dDao = new DeviceDaoImp();
//		List<Map<String, String>> list = dDao.getDeviceInfo(li,"8");
//		
//		
//		
//        result=list;
//		System.out.println(list.toString());
//			
//		return SUCCESS;
		////////////////////////////下面是测试数据////////////////////////////
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("deviceSubType", "DS-2CD2135F-I20160311AACH578893601");
		map1.put("hostId", "10211");
		map1.put("hostIp", "192.168.1.64");
		map1.put("is_device_online", "OK");
		map1.put("location", "罗湖区");
		map1.put("contact", "摄像头负责人");
		map1.put("IPADDRESS", "192.168.1.64");
		map1.put("deviceStatus", "OK");
		list.add(map1);
		for (int i = 0; i < 9; i++) {
			Map<String, String> map = new HashMap<String, String>();
			String temp = GetRandom.get2random();
			map.put("hostIp", "192.168.1."+temp);
			map.put("deviceType", "850");
			map.put("deviceSubType", "DS-K26012015"+GetRandom.get4random()+"V010100CH57751"+GetRandom.get4random());
			map.put("deviceStatus", "OK");
			map.put("hostId", "1"+GetRandom.get4random());
			map.put("is_device_online", "OK");
			map.put("IPADDRESS", "192.168.1."+temp);
			map.put("contact", "门禁负责人");
			map.put("location",GetRandom.getString());
			list.add(map);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}
}
