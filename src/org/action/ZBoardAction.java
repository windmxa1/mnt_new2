package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZBoardDao;
import org.dao.imp.ZBoardDaoImp;
import org.model.ZBoard;
import org.model.ZUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZBoardAction extends ActionSupport{
	private Integer[] id;
	private String title;
	private String content;
	private Object result;
	private Integer start;
	private Integer limit;
	
	/**
	 * 要先登录，保证session中有数据
	 * @return
	 * @throws Exception
	 */
	public String addBoard() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		
		if(user==null){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "请先登录，才能发布公告");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result = list;
			return SUCCESS;
		}
		ZBoard b = new ZBoard();
		b.setTitle(title);
		b.setContent(content);
		b.setTime((int) (System.currentTimeMillis()/1000));
		b.setUserId(user.getId());
		
		ZBoardDao bDao = new ZBoardDaoImp();
		if(bDao.addBoard(b)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result = list;
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result = list;
		}
		return SUCCESS;
	}
	/**
	 * 删除，传入主键 id
	 * @return
	 * @throws Exception
	 */
	public String deleteBoard() throws Exception{
		ZBoardDao bDao = new ZBoardDaoImp();
		if(bDao.deleteBoard(id)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "1");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result = list;
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "0");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result = list;
		}
		return SUCCESS;
	}
	/**
	 * 获取公告列表
	 * @return
	 * @throws Exception
	 */
	public String getBoardList() throws Exception{
		ZBoardDao bDao = new ZBoardDaoImp();
		List<ZBoard> list = bDao.getBoardList(start,limit);
		Map<String, Object> m1 = new HashMap<>();
		m1.put("total", bDao.getBoardCount());
		m1.put("data", list);
		Map<String, Object> m2 = new HashMap<>();
		m2.put("value", m1);
		
		result=m2;
//		result=list;
		return SUCCESS;
	}
	
	//----------------------------------------------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer[] getId() {
		return id;
	}
	public void setId(Integer[] id) {
		this.id = id;
	}
}
