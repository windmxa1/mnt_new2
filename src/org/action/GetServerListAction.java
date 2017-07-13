package org.action;

import java.util.List;

import org.service.ServerService;
import org.service.imp.ServerServiceImp;

import com.opensymphony.xwork2.ActionSupport;

public class GetServerListAction extends ActionSupport{
	public List result;

	public String execute() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerInfo();
		
		result=li;
		return SUCCESS;
	}
	
	public String getServerCpuInfo() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerCpuInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getServerMemInfo() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerMemInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getServerNetInfo() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerNetInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getServerDiskInfo() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerDiskInfo();
		
		result=li;
		return SUCCESS;
	}
	public String getServerCpu10Info() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerCpu10Info();
		
		result=li;
		return SUCCESS;
	}
	public String getServerOtherInfo() throws Exception{
		ServerService sService = new ServerServiceImp();
		List li = sService.getServerOtherInfo();
		
		result=li;
		return SUCCESS;
	}
	//--------------------------------------------------
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}
}
