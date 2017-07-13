package org.action;

import java.util.List;

import org.dao.ZAuthorityDao;
import org.dao.imp.ZAuthorityDaoImp;

import com.opensymphony.xwork2.ActionSupport;

public class ZAuthorityAction extends ActionSupport{
	private Integer id;
	private String alias;
	private String action;

	private List result;
	
	public String getAList() throws Exception{
		System.out.println("——获取权限列表——");
		ZAuthorityDao aDao = new ZAuthorityDaoImp();
		result = aDao.getAList();
		
		return SUCCESS;
	}
	
	//---------------------get/set----------------------------
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
