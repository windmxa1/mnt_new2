package org.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.service.NVRService;
import org.service.imp.NVRServiceImp;

import com.opensymphony.xwork2.ActionSupport;


public class GetNVRListAction extends ActionSupport{	//录像机
	private List result;		//json 返回值
		
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {	//待定：是否有null的判断,参照login
		GroupsDao gDao = new GroupsDaoImp();
		List<Object[]> li = gDao.getHostListByGroupid((long) 10);
		
		DeviceInfoDao dDao = new DeviceDaoImp();
		List<Map<String, String>> list = dDao.getDeviceInfo(li,"10");
		
        result=list;
		System.out.println(list.toString());
			
		return SUCCESS;
	}
	public String getNVRInfo() throws Exception{
		NVRService nvrService = new NVRServiceImp();
		List li = nvrService.getNVRInfo();
		
		result = li;
		return SUCCESS;
	}
}
