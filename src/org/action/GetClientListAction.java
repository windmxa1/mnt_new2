package org.action;

import java.util.List;
import java.util.Map;

import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.service.ClientService;
import org.service.imp.ClientServiceImp;

import com.opensymphony.xwork2.ActionSupport;

public class GetClientListAction extends ActionSupport{
	public Object result;

	public String execute() throws Exception{	//获取主机全部信息
		ClientService cService = new ClientServiceImp();
		List li = cService.getClientInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getClientCpuInfo() throws Exception{
		ClientService cService = new ClientServiceImp();
		List li = cService.getClientCpuInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getClientMemInfo() throws Exception{
		ClientService cService = new ClientServiceImp();
		List li = cService.getClientMemInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getClientNetInfo() throws Exception{
		ClientService cService = new ClientServiceImp();
		List li = cService.getClientNetInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getClientDiskInfo() throws Exception{
		ClientService cService = new ClientServiceImp();
		List li = cService.getClientDiskInfo();
		
		result=li;
		return SUCCESS;
	}
//	getClientCPU10Info
	public String getClientCPU10Info() throws Exception{
		ClientService cService = new ClientServiceImp();
//		List li = cService.getClientCpu10Info();
		Map m = cService.getClientCpu10Info();

//		result = li;
		result = m;
		return SUCCESS;
	}
	
	public String getClientOtherInfo() throws Exception{
		ClientService cService = new ClientServiceImp();
		List li = cService.getClientOtherInfo();
		
		result = li;
		return SUCCESS;
	}
	//----------------------------------------------------
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
