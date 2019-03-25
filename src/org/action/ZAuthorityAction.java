package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZAuthorityDao;
import org.dao.imp.ZAuthorityDaoImp;
import org.model.ZAuthority;
import org.tool.R;

import com.opensymphony.xwork2.ActionSupport;

public class ZAuthorityAction extends ActionSupport {
	private Map<String, Object> data;
	private Object result;

	public String getAuthorityList() throws Exception {
		System.out.println("——获取权限列表——");
		ZAuthorityDao aDao = new ZAuthorityDaoImp();
		data = new HashMap<String, Object>();
		List<ZAuthority> list = aDao.getAList();
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	// ---------------------get/set----------------------------
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
