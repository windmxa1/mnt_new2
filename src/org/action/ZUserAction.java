package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dao.ZAuthorityDao;
import org.dao.ZRoleAuthorityDao;
import org.dao.ZRoleDao;
import org.dao.ZUserDao;
import org.dao.ZUserRoleDao;
import org.dao.imp.ZAuthorityDaoImp;
import org.dao.imp.ZRoleAuthorityDaoImp;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.dao.imp.ZUserRoleDaoImp;
import org.model.ZRole;
import org.model.ZUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZUserAction extends ActionSupport{
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String role;
	
	private Object result;		//json 返回值
	
	public String login() throws Exception {
		System.out.println("\n——登录——");
		ZUserDao zDao = new ZUserDaoImp();
		
		ZUser u = zDao.login(username, password);
		if(u != null){	//login success
			System.out.println("----------login success!----------");
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", u);
			
			ZUserRoleDao urDao = new ZUserRoleDaoImp();
			int rid = urDao.getRByU(u.getId());
			ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
			ZAuthorityDao zaDao = new ZAuthorityDaoImp();
			List<Integer> aList = raDao.getAListByR(rid);
			session.put("aList", aList);
			
			List<String> aList2 = new ArrayList<>();
			for(int a :aList){
				String aString = zaDao.getActionById(a);
				aList2.add(aString);
			}
			session.put("aList", aList2);
			//---将数据写入result---
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("lname", u.getUsername());
			map.put("lpwd", u.getPassword());
			map.put("success", true);

			JSONObject jo = JSONObject.fromObject(map);	//将要返回的map对象进行json处理
			
			result = map;
			
			System.out.println("1:"+result);
			System.out.println(jo.toString());
			
			return SUCCESS;
		}else {
			System.out.println("--login error!--");
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("success", false);
			
			Map<String,String> errorMsg = new HashMap<String,String>();
			errorMsg.put("clientCode","用户名或密码错误！");
			errorMsg.put("hhhhh","===");
			
			map.put("errors", errorMsg);
			
			JSONObject jo = JSONObject.fromObject(map);	//将要返回的map对象进行json处理
			result = map;
			System.out.println(result);
			
			return ERROR;
		}
	}
	public String addUser() throws Exception{
		System.out.println("——添加用户——");
		System.out.println("username:"+username+" password:"+password+" name:"+name+" role:"+role);
		ZUserDao uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		if(!uDao.checkUsername(username)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "用户名重复");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
			return SUCCESS;
		}else if(!rDao.checkRole(role)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "角色重复");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
			return SUCCESS;
		}
		ZUser u = new ZUser();
		u.setUsername(username);
		u.setPassword(password);
		u.setName(name);
		
		ZUserRoleDao urDao = new ZUserRoleDaoImp();
		int uid = uDao.getUserMaxid()+1;
		int rid = rDao.getRoleMaxid()+1;
		if(uDao.addUser(u) && rDao.addRole(role) && urDao.addUR(uid, rid)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
		}
		return SUCCESS;
	}
	public String deleteUser() throws Exception{
		System.out.println("——删除用户——");
		ZUserDao uDao = new ZUserDaoImp();
		if(uDao.deleteUser(id)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
		}
		return SUCCESS;
	}
	public String getUserList() throws Exception {		//getUserList 获取用户列表
		System.out.println("——获取用户列表——");
		ZUserDao uDao = new ZUserDaoImp();
		List<ZUser> list = uDao.getUserList();
		
		result=list;
		return SUCCESS;
	}
	
	//—————————————————get&set—————————————————————
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
