package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZRoleDao;
import org.dao.imp.ZRoleDaoImp;

import com.opensymphony.xwork2.ActionSupport;

public class ZRoleAction extends ActionSupport {
	private Integer id;
	private String role;

	private List result;

	public String getRList() throws Exception{
		System.out.println("——获取角色列表——");
		ZRoleDao rDao = new ZRoleDaoImp();
		result = rDao.getRoleList();
		return SUCCESS;
	}
	//----
	public String addRole() throws Exception {
		System.out.println("——添加角色 addRole——");
		ZRoleDao rDao = new ZRoleDaoImp();
		if(!rDao.checkRole(role)){
			System.out.println("角色名重复");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "角色名重复");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
			return SUCCESS;
		}
		if (rDao.addRole(role)) {
			System.out.println("添加成功");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
		} else {
			System.out.println("添加异常");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
		}
		return SUCCESS;
	}
	public String deleteRole() throws Exception {
		System.out.println("——删除角色 delRole——");
		ZRoleDao rDao = new ZRoleDaoImp();
		if (rDao.deleteRole(id)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
		} else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
		}
		return SUCCESS;
	}

	// ------------------ get & set ------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
