package org.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.dao.ZRoleDao;
import org.dao.ZUserDao;
import org.dao.ZUserRoleDao;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.dao.imp.ZUserRoleDaoImp;
import org.model.ZUser;
import org.model.ZUserRole;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZUserRoleAction extends ActionSupport{
	private int userId;
	private String username;
	private String password;
	private String name;
	private String role;
	
	private List result;
	
	private List<String> ids;
	
	/**
	 * 添加用户 并 绑定角色
	 * @return
	 * @throws Exception
	 */
	public String addUR() throws Exception{
		System.out.println("——添加用户-角色——");
		System.out.println("username:"+username+" password:"+password+" name:"+name+" role:"+role);
		ZUserDao uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		if(!uDao.checkUsername(username)){
			System.out.println("  用户名重复");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "用户名重复");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
			return SUCCESS;
		}else{
			ZUser u = new ZUser();
			u.setUsername(username);
			u.setPassword(password);
			u.setName(name);
			
			ZUserRoleDao urDao = new ZUserRoleDaoImp();
			if(uDao.addUser(u)){	//添加用户成功
				int uid = uDao.getIdByUsername(username);
				int rid = rDao.getIdByRole(role);
				if(urDao.addUR(uid, rid)){
					Map<String, String> map = new HashMap<String, String>();
					map.put("result", "1");
					List<Map<String, String>> list = new ArrayList<>(); 
					list.add(map);
					result=list;
					return SUCCESS;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "添加用户成功，绑定角色失败");
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
		}
		return SUCCESS;
	}
	/**
	 * 获取用户-角色列表 
	 * @return	json:userid name username password role
	 * @throws Exception
	 */
	public String getURList() throws Exception{
		System.out.println("\n——获取用户-角色列表——");
		
		ZUserRoleDao urDao = new ZUserRoleDaoImp();
		ZUserDao uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		List<ZUser> uList= uDao.getUserList();
		
		List<Map<String, String>> list = new ArrayList<>();
		for(ZUser u : uList){	//每一个用户对象
			Map<String, String> map = new HashMap<>();
			map.put("id", ""+u.getId());
			map.put("username", u.getUsername());
//			map.put("password", u.getPassword());
			map.put("name", u.getName());
			int roleId = urDao.getRByU(u.getId());
			if(roleId==-1){
				map.put("role", "");
			}else{
				String role = rDao.getRoleById(roleId);
				map.put("role", role);
			}
			list.add(map);
		}
		result=list;
		return SUCCESS;
	}
	/**
	 * 修改用户-角色对应
	 * @return
	 * @throws Exception
	 */
	public String updateUR()throws Exception{
		System.out.println("\n——修改用户-角色关联——");
		
		ServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		
		System.out.println("id:"+id);
		System.out.println("role:"+role);
		System.out.println("password:"+password);
		
		userId = Integer.parseInt(id);
		
		if(id.equals("")){
			System.out.println("用户id不能为空");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "用户id不能为空");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
			return SUCCESS;
		}
		
		//检测userId 是否合法
		ZUserDao zuDao = new ZUserDaoImp();
		if(!zuDao.checkId(userId)){
			System.out.println("用户id无效");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "用户id无效");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
			return SUCCESS;
		}
		if(!password.equals("")){
			ZUserDao uDao = new ZUserDaoImp();
			if(uDao.changePassword(userId, password)){
				System.out.println("修改密码成功");
			}else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "修改密码出错");
				List<Map<String, String>> list = new ArrayList<>(); 
				list.add(map);
				result=list;
				return SUCCESS;
			}
		}
		if(!role.equals("")){
			ZUserRoleDao urDao = new ZUserRoleDaoImp();
			ZRoleDao rDao = new ZRoleDaoImp();
			int r = rDao.getIdByRole(role);
			System.out.println("roleId:"+r);
			
			if(!urDao.checkUserid(userId)){	//如果关联表没有记录，则转为插入
				System.out.println("关联表没有记录，则转为插入");
				urDao.addUR(userId, r);
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "1");
				List<Map<String, String>> list = new ArrayList<>(); 
				list.add(map);
				result=list;
				return SUCCESS;
			}
			//否则直接修改
			if(urDao.updateUR(userId, r)){	
				System.out.println("更新用户-角色成功");
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "1");
				List<Map<String, String>> list = new ArrayList<>(); 
				list.add(map);
				result=list;
			}else {
				System.out.println("更新用户-角色失败");
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "0");
				List<Map<String, String>> list = new ArrayList<>(); 
				list.add(map);
				result=list;
			}
		}
		return SUCCESS;
	}
	/**
	 * 删除用户 并 删除绑定
	 * @return
	 * @throws Exception
	 */
	public String deleteUR() throws Exception{//注意：1.不能删除自己 2.不能删除管理员
		System.out.println("\n——删除用户-角色——");
		
		ServletRequest request = ServletActionContext.getRequest();
//		request.getParameter("ids");
		
		for(String a:ids){
			int idv = Integer.parseInt(a);	//前端传过来的id
			System.out.println(idv);
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			ZUser user = (ZUser) session.get("user");
			int thisId = user.getId();
			
			ZUserDao zuDao = new ZUserDaoImp();
			ZUserDao uDao = new ZUserDaoImp();
			
			if(!zuDao.checkId(idv)){//检测userId 是否合法
				System.out.println("用户id无效");
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "用户id无效");
				List<Map<String, String>> list = new ArrayList<>(); 
				list.add(map);
				result=list;
			}else{
				if(idv==thisId){	//检测是否是自己的id
					System.out.println("无法删除自己");
					Map<String, String> map = new HashMap<String, String>();
					map.put("result", "无法删除自己");
					List<Map<String, String>> list = new ArrayList<>(); 
					list.add(map);
					result=list;
				}else{
					if(idv==1){	//检测是否是管理员的id
						System.out.println("无法删除管理员");
						Map<String, String> map = new HashMap<String, String>();
						map.put("result", "无法删除管理员");
						List<Map<String, String>> list = new ArrayList<>(); 
						list.add(map);
						result=list;
					}else{
						if(uDao.deleteUser(idv)){	//删除用户
							System.out.println("删除用户成功");
							ZUserRoleDao urDao = new ZUserRoleDaoImp();
							int id = urDao.getIdByUserid(idv);
							
							if(urDao.deleteUR(id)){	//删除 用户-角色 关联表
								System.out.println("删除用户-角色关联成功");
								Map<String, String> map = new HashMap<String, String>();
								map.put("result", "1");
								List<Map<String, String>> list = new ArrayList<>(); 
								list.add(map);
								result=list;
							}else {
								Map<String, String> map = new HashMap<String, String>();
								map.put("result", "删除用户-角色关联错误");
								List<Map<String, String>> list = new ArrayList<>(); 
								list.add(map);
								result=list;
							}
						}else{
							System.out.println("删除用户失败");
							Map<String, String> map = new HashMap<String, String>();
							map.put("result", "删除用户失败");
							List<Map<String, String>> list = new ArrayList<>(); 
							list.add(map);
							result=list;
						}
					}
				}
			}
		}//for
		return SUCCESS;
	}
	
	//-------------------get set------------------------
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
