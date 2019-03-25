package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

import org.dao.ZAuthorityDao;
import org.dao.ZRoleAuthorityDao;
import org.dao.ZRoleDao;
import org.dao.ZUserRoleDao;
import org.dao.imp.ZAuthorityDaoImp;
import org.dao.imp.ZRoleAuthorityDaoImp;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZUserRoleDaoImp;
import org.model.ZAuthority;
import org.model.ZRole;
import org.model.ZRoleAuthority;
import org.model.ZUser;
import org.tool.R;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZRoleAction extends ActionSupport {
	private Integer roleid;
	private String role;
	private int[] authorityId;
	private Map<String, Object> data;
	private Object result;

//	// 角色及权限关联
//	public String getRAList() throws Exception {
//		ZRoleDao rDao = new ZRoleDaoImp();
//		List<ZRole> rList = rDao.getRoleList();
//
//		ZAuthorityDao aDao = new ZAuthorityDaoImp();
//		List<ZAuthority> aList = aDao.getAList();
//
//		ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
//		List list = new ArrayList<>();
//		for (ZRole r : rList) { // 每一个role
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("id", r.getId());
//			map.put("role", r.getRole());
//			List<Integer> aidList = raDao.getAListByR(r.getId());// id对应的alist
//																	// id
//			for (ZAuthority a : aList) {// 每一个权限a
//				int id = a.getId();
//				int k = 0;
//				for (int i : aidList) {
//					if (id == i)
//						k = 1;
//				}
//				map.put(a.getAlias(), "" + k);
//			}
//			list.add(map);
//		}
//		result = list;
//		return SUCCESS;
//	}

	/**
	 * 获取角色列表
	 */
	public String getRoleList() {
		ZRoleDao rDao = new ZRoleDaoImp();
		List<ZRole> list = rDao.getRoleList();
		data = new HashMap<>();
		data.put("list", list);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}
	/**
	 * 获取角色名称列表
	 */
	public String getRoleNameList(){
		ZRoleDao rDao = new ZRoleDaoImp();
		List<String> list = rDao.getRoleNameList();
		data = new HashMap<>();
		List<Map<String, Object>> list2 = new ArrayList<>();
		for (String role : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("role", role);
			list2.add(map);
		}
		data.put("list", list2);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 获取角色对应的权限列表
	 */
	public String getAuthorityListByRole() {
		ZAuthorityDao zAuthorityDao = new ZAuthorityDaoImp();
		List<String> list = zAuthorityDao.getAliasList(roleid);
		List<ZAuthority> list2 = zAuthorityDao.getAList();
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (ZAuthority a : list2) {
			Map<String, Object> map = new HashMap<>();
			map.put("authorityId", a.getId());
			map.put("alias", a.getAlias());
			if (list.contains(a.getAlias())) {
				map.put("value", 1);
			} else {
				map.put("value", 0);
			}
			mapList.add(map);
		}
		data = new HashMap<>();
		data.put("list", mapList);
		result = R.getJson(1, "", data);
		return SUCCESS;
	}

	/**
	 * 修改角色权限
	 */
	public String updateRole() {
		ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
		if (roleid == 1) {
			result = R.getJson(0, "修改失败，不能修改超级管理员权限", "");
		}
		if (raDao.updateRoleAuthority(roleid, authorityId)) {
			result = R.getJson(1, "修改成功", "");
		} else {
			result = R.getJson(0, "修改失败", "");
		}
		return SUCCESS;
	}

	/**
	 * 添加角色
	 */
	public String addRole() throws Exception {
		System.out.println("——添加角色 addRole——");
		ZRoleDao rDao = new ZRoleDaoImp();
		if (!rDao.checkRole(role)) {
			result = R.getJson(0, "角色名重复", "");
			return SUCCESS;
		}
		if (rDao.addRole(role, authorityId)) {
			result = R.getJson(1, "添加成功", "");
		} else {
			result = R.getJson(0, "添加失败，请重试", "");
		}

		return SUCCESS;
	}

	/**
	 * 删除角色
	 */
	public String deleteRole() throws Exception {
		ZRoleDao rDao = new ZRoleDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");

		ZUserRoleDao urDao = new ZUserRoleDaoImp();
		int thisRid = urDao.getRByU(user.getId());
		if (roleid == 1) {
			result = R.getJson(0, "不能删除管理员角色!", "");
		}
		if (roleid == thisRid) {
			result = R.getJson(0, "不能删除自己的角色!", "");
		}
		if (rDao.deleteRole(roleid)) {
			result = R.getJson(1, "删除成功", "");
		} else {
			result = R.getJson(0, "删除失败，请重试", "");
		}
		return SUCCESS;
	}

	// ------------------ get & set ------------------

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int[] getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int[] authorityId) {
		this.authorityId = authorityId;
	}

}
