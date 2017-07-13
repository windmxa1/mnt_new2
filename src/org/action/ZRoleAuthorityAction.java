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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
//data[i]= sm[i].get('id');
public class ZRoleAuthorityAction extends ActionSupport{
	private int id;
//	private int roleId;
	private int[] authorityid;
	
	private List result;
	
	private List<String> ids;

	public String addRA() throws Exception{	//采用request的方式
		System.out.println("\n——添加角色-权限——");
		
		ServletRequest request = ServletActionContext.getRequest();
		
		String role = request.getParameter("role");
		System.out.println("role:"+role);
		
//		Map<String, String> m =new HashMap<String,String>();
		List<String> list = new ArrayList<>();
//		if(request.getParameter("name").equals("on")){
//			list.add("name");
//		}
		if(request.getParameter("获取用户列表")!=null){
			list.add("getURList");
		}
		if(request.getParameter("创建用户")!=null){
			list.add("register");
		}
		if(request.getParameter("删除用户")!=null){
			list.add("deleteUser");
		}
		if(request.getParameter("登陆")!=null){
			list.add("login");
		}
		if(request.getParameter("验证用户名")!=null){
			list.add("checkUsername");
		}
		if(request.getParameter("获取摄像头信息")!=null){
			list.add("getIPCInfo");
		}
		if(request.getParameter("获取录像机信息")!=null){
			list.add("getNVRInfo");
		}
		if(request.getParameter("获取门禁信息")!=null){
			list.add("getDCInfo");
		}
		if(request.getParameter("获取门禁事件")!=null){
			list.add("getDCEvents");
		}
		if(request.getParameter("添加用户角色")!=null){
			list.add("addUR");
		}
		ZRoleDao rDao = new ZRoleDaoImp();
		System.out.println("检测角色名");
		if(!rDao.checkRole(role)){
			System.out.println("  角色名重复");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "角色名重复");
			List<Map<String, String>> li = new ArrayList<>(); 
			li.add(map);
			result=li;
			return SUCCESS;
		}
		System.out.println("角色名合法");
		boolean b = rDao.addRole(role);
		if(b){	//添加角色成功
			System.out.println("添加角色成功");
			int rid = rDao.getIdByRole(role);	//获取角色id
			for(String action:list){	//每一个权限名
				System.out.println("  action:"+action);
				ZAuthorityDao aDao = new ZAuthorityDaoImp();
				int aid = aDao.getIdByAction(action);	//获取权限id
				ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
				raDao.addRA(rid, aid);
			}
			System.out.println("添加角色-权限关联成功");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list1 = new ArrayList<>();
			list1.add(map);
			result=list1;
		}else{
			System.out.println("添加角色失败");
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list2 = new ArrayList<>(); 
			list2.add(map);
			result=list2;
		}
		return SUCCESS;
	}
	public String getRAList() throws Exception{
		System.out.println("\n——获取角色-权限列表——");
		
		ZAuthorityDao aDao = new ZAuthorityDaoImp();
		List<ZAuthority> aList = aDao.getAList();
		
		ZRoleDao rDao = new ZRoleDaoImp();
		List<ZRole> rList = rDao.getRoleList();
		
		ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
		List<ZRoleAuthority> raList = raDao.getRAList();
		
		List list = new ArrayList<>();
		for(ZRole r : rList){	//每一个role
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("id", r.getId());
			map.put("role", r.getRole());
			List<Integer> aidList = raDao.getAListByR(r.getId());//id对应的alist id
//			Map<String, String> auMap = new HashMap<>();
			for(ZAuthority a:aList){//每一个权限a
				int id = a.getId();
				int k=0;
				for(int i:aidList){
					if(id==i)
						k=1;
				}
//				auMap.put(a.getAlias(),""+k);
				map.put(a.getAlias(),""+k);
			}
//			map.put("authority", auMap);
			list.add(map);
		}
//		result=raList;
		result=list;
		return SUCCESS;
	}
	/**
	 * 根据角色id roleId 删除角色，删除用户-角色关系，删除角色-权限关系
	 * @return
	 * @throws Exception
	 */
	public String deleteRA() throws Exception{//输入参数为：roleId 注意：1.不能删除自己对应的角色 2.不能删除管理员角色
		System.out.println("\n——删除角色-权限——");
//		System.out.println("  roleId:"+roleId);
		
		for(String b:ids){
			System.out.println("id:"+b);
			int roleId = Integer.parseInt(b);
			ZRoleDao rDao = new ZRoleDaoImp();
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			ZUser user = (ZUser) session.get("user");
			int thisId = user.getId();
			
			ZUserRoleDao urDao = new ZUserRoleDaoImp();
			int thisRid = urDao.getRByU(thisId);
			
			if(!rDao.checkId(roleId)){	//检测角色id是否合法
				System.out.println("角色id无效");
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "角色id无效");
				List<Map<String, String>> list = new ArrayList<>(); 
				list.add(map);
				result=list;
			}else{
				if(roleId==1){	//检测是否是管理员
					System.out.println("无法删除管理员");
					Map<String, String> map = new HashMap<String, String>();
					map.put("result", "无法删除管理员");
					List<Map<String, String>> list = new ArrayList<>(); 
					list.add(map);
					result=list;
				}else{
					if(roleId==thisRid){	//检测是否是自己的角色
						System.out.println("无法删除自己的角色");
						Map<String, String> map = new HashMap<String, String>();
						map.put("result", "无法删除自己的角色");
						List<Map<String, String>> list = new ArrayList<>(); 
						list.add(map);
						result=list;
					}else{
						if(rDao.deleteRole(roleId)){
							System.out.println("删除角色成功");
							ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
							//删除角色-权限关联
							List<Integer> idList = raDao.getIdListByR(roleId);
							for(int a : idList){
								System.out.println("raid:"+a);
								if(raDao.deleteRA(a))
									System.out.println("删除角色-权限关联成功");
								else
									System.out.println("删除角色-权限关联失败");
							}
							
							//删除用户-角色关联
							List<Integer> id = urDao.getIdByRoleid(roleId);
							for(int k : id){
								if(urDao.deleteUR(k))
									System.out.println("删除用户-角色关联成功");
								else
									System.out.println("删除用户-角色关联失败");
							}
							Map<String, String> map = new HashMap<String, String>();
							map.put("result", "1");
							List<Map<String, String>> list = new ArrayList<>(); 
							list.add(map);
							result=list;
						}else{
							Map<String, String> map = new HashMap<String, String>();
							map.put("result", "删除角色失败");
							List<Map<String, String>> list = new ArrayList<>(); 
							list.add(map);
							result=list;
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 输入：角色id ：roleid ， 新的权限中文字段
	 * @return
	 * @throws Exception
	 */
	public String updateRA() throws Exception{
		System.out.println("\n——更新角色-权限——");
		ServletRequest request = ServletActionContext.getRequest();
		
		String role = request.getParameter("role");
		System.out.println("  role:"+role);
		
		ZRoleDao rDao = new ZRoleDaoImp();
		int rid = rDao.getIdByRole(role);
		
		
//		int rid = Integer.parseInt(roleid);	//角色id
//		System.out.println(roleid);
		
		//判断角色id是否合法
		if(rDao.checkId(rid)){

			List<String> list = new ArrayList<>();
			if(request.getParameter("获取用户列表")!=null){
				list.add("getURList");
			}
			if(request.getParameter("创建用户")!=null){
				list.add("register");
			}
			if(request.getParameter("删除用户")!=null){
				list.add("deleteUser");
			}
			if(request.getParameter("登陆")!=null){
				list.add("login");
			}
			if(request.getParameter("验证用户名")!=null){
				list.add("checkUsername");
			}
			if(request.getParameter("获取摄像头信息")!=null){
				list.add("getIPCInfo");
			}
			if(request.getParameter("获取录像机信息")!=null){
				list.add("getNVRInfo");
			}
			if(request.getParameter("获取门禁信息")!=null){
				list.add("getDCInfo");
			}
			if(request.getParameter("获取门禁事件")!=null){
				list.add("getDCEvents");
			}
			if(request.getParameter("添加用户角色")!=null){
				list.add("addUR");
			}
			
			//先删除角色对应的权限，再添加角色对应的新权限
			ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
			List<Integer> idList = raDao.getIdListByR(rid);
			for(int k:idList){//  删除
				if(raDao.deleteRA(k))	//循环删除对应关联表
					System.out.println("先删除R-A:"+k+"成功");
				else
					System.out.println("先删除R-A:"+k+"失败");
//				raDao.deleteRA(k);	
			}
			for(String action:list){	//添加		每一个权限名
				System.out.println("要修改的权限名："+action);
				ZAuthorityDao aDao = new ZAuthorityDaoImp();
				int aid = aDao.getIdByAction(action);	//获取权限id
				raDao.addRA(rid, aid);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list1 = new ArrayList<>(); 
			list1.add(map);
			result=list1;
			return SUCCESS;
		}else{//id无效
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "id无效");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result=list;
			return SUCCESS;
		}
	}
	
//	public String getAListByR() throws Exception{
//		ZRoleAuthorityDao raDao = new ZRoleAuthorityDaoImp();
//		List<ZAuthority> aList = raDao.getAListByR(roleId);
//		
//		result=aList;
//		return SUCCESS;
//	}
	
	//--------------get set----------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getAuthorityid() {
		return authorityid;
	}
	public void setAuthorityid(int[] authorityid) {
		this.authorityid = authorityid;
	}
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
