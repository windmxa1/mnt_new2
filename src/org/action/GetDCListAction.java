package org.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dao.DeviceInfoDao;
import org.dao.EventsDao;
import org.dao.GroupsDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.EventsDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.model.DcEvents;
import org.tool.GetRandom;

import com.opensymphony.xwork2.ActionSupport;

public class GetDCListAction extends ActionSupport { // 门禁
	private String host = "192.168.1.80";
	private List result; // json 返回值
	private String doorid="1";
	private String ctl="1";
	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String execute() throws Exception {

		GroupsDao gDao = new GroupsDaoImp();
		// 找出所有主机
		List<Object[]> li = gDao.getHostListByGroupid((long) 9);
		
		DeviceInfoDao dDao = new DeviceDaoImp();
		// 找出所有与主机对应的设备信息
		List<Map<String, String>> list = dDao.getDeviceInfo(li,"9");
		result = list;
		
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	public String getDcEvents() {
		DeviceInfoDao dDao = new DeviceDaoImp();
		List<DcEvents> list = dDao.getDCEvents(host);
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}
	public String doorControl(){
		try {
			String str = "sh /home/shc/home/doorController.sh ";
//			String str = "E:/WebComponents.exe";
			str+=doorid+" "+ctl;
			Runtime.getRuntime().exec(str);
			return SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String getCtl() {
		return ctl;
	}

	public void setCtl(String ctl) {
		this.ctl = ctl;
	}

	public String getDoorid() {
		return doorid;
	}

	public void setDoorid(String doorid) {
		this.doorid = doorid;
	}

}
