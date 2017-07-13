package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dao.ZUserDao;
import org.dao.imp.ZUserDaoImp;
import org.model.ZUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	private Integer id;
	private String username;
	private String password;
	private Integer authority;
	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	@Override
	public String execute() throws Exception {
		ZUserDao uDao = new ZUserDaoImp();
		List<ZUser> list = uDao.getUserList();
		
		JSONArray jo = new JSONArray();
		jo.add(list);
		result = jo.toString();
		/*
		 * [
		 * 	[
		 * 		{"id":1,"authority":1,"username":"admin","password":"admin"},
		 * 		{"id":68,"authority":2,"username":"tom","password":"2"},
		 * 		{"id":75,"authority":2,"username":"zgj","password":"e"}
		 * 	]
		 * ]
		 * 
		 * 被加多一个[]，故前端获取需要 var a; a[0][i] 来取
		 */
		System.out.println(jo);
		
		return SUCCESS;
	}
	public String login() throws Exception {
		ZUserDao zDao = new ZUserDaoImp();
		
		ZUser u = zDao.login(username, password);
		if(u != null){
			System.out.println("----------login success!----------");
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", u);
			
			//---将数据写入result---
			Map<String,String> map = new HashMap<String,String>();
			map.put("lname", u.getUsername());
			map.put("lpwd", u.getPassword());
			
			JSONObject jo = JSONObject.fromObject(map);	//将要返回的map对象进行json处理
			
			result = jo.toString();
			
			System.out.println(result);
			
			return SUCCESS;
		}else {
			System.out.println("--login error!--");
			return ERROR;
		}
	}
}
