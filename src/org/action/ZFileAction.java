package org.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZFileDao;
import org.dao.imp.ZFileDaoImp;
import org.model.ZFile;
import org.model.ZUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.INEG;

public class ZFileAction extends ActionSupport{
	private File upFile;
	private String upFileFileName;
	private String upFileContentType;
	
	private Object result;
	
	private Integer[] id;
	private String fileName;
	private Integer start;
	private Integer limit;
	
	/**
	 * 1.文件上传
	 * @return
	 * @throws Exception
	 */
	public String fileUpload() throws Exception{
		System.out.println("\n——文件上传——");
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser u = (ZUser) session.get("user");
		
		if(u!=null){
			long upTime = new Date().getTime();		//上传时间
			try {
				ServletRequest request = ServletActionContext.getRequest();
				String path = request.getRealPath("/");
				System.out.println("path: "+path);
				File dir = new File(path+"UpFileDir");
				if(!dir.exists() && !dir.isDirectory()){	//路径不存在则创建
					dir.mkdir();
				}
				InputStream is = new FileInputStream(upFile);
				
				String fileName = upTime+"_"+new Random().nextInt(10)+upFileFileName.substring(upFileFileName.indexOf("."));	//文件实际名，时间戳加上0-9随机数
				String fPath = dir+"/"+fileName;		//文件最终路径		linux 用/  win用\
				OutputStream os = new FileOutputStream(fPath);
				
				System.out.println("文件名："+upFileFileName);
				System.out.println("文件保存名："+fileName);
				System.out.println("文件路径:"+fPath);
				
				byte[] buffer = new byte[1024];
				int count=0;
				while((count=is.read(buffer))>0){
					os.write(buffer,0,count);
				}
				os.close();
				is.close();
				String url = "http://192.168.1.117:8080/mnt_new2/UpFileDir/";	//实际环境需要改为主机地址
				
				ZFile f = new ZFile();
				f.setFilename(upFileFileName);
				f.setUsername(u.getUsername());
				f.setUploadtime(upTime);
				f.setDir(url+fileName);
				
				ZFileDao fDao = new ZFileDaoImp();
				if(fDao.addFile(f)){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("success", true);
					map.put("msg", "上传成功");
					result = map;
				}else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("success", true);
					map.put("msg", "上传失败");
					result = map;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("请先登录,才能上传文件!");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("msg", "请先登录,才能上传文件");
			result = map;
		}
		return SUCCESS;
	}
//	/**
//	 * 2.文件下载
//	 * @return
//	 * @throws Exception
//	 */
//	public InputStream getFileDown() throws Exception{
//		System.out.println("\n——文件下载——");
//		
//		ZFileDao fDao = new ZFileDaoImp();
//		ZFile f = fDao.getFileById(Integer.parseInt(id));
//		if(f!=null){
//			fileName = f.getUploadtime()+"_"+f.getFilename();
//			
//			return ServletActionContext.getServletContext().getResourceAsStream("UpFileDir/"+fileName);
//		}else {
//			System.out.println("id不合法");
//			return null;
//		}
//		
//	}
	/**
	 * 3.获取文件列表
	 * @return
	 */
	public String getFileList() throws Exception{
		System.out.println("\n——获取文件列表——");
		
		ZFileDao fDao = new ZFileDaoImp();
		List<ZFile> list = fDao.getFileList(start,limit);
		
		Map<String, Object> m1 = new HashMap<>();
		m1.put("total", fDao.getFileCount());
		m1.put("data", list);
		Map<String, Object> m2 = new HashMap<>();
		m2.put("value", m1);
		
		result = m2;
		return SUCCESS;
	}
	
	/**
	 * 4.删除文件
	 * @return
	 */
	public String deleteFile() throws Exception{
		ZFileDao fDao = new ZFileDaoImp();

//		HttpSession session = (HttpSession) ServletActionContext.getRequest().getSession();
//		ZUser user = (ZUser) session.getAttribute("user");
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		
		if(user!=null){
			String err = "无法删除他人的文件,id为：";
			int key=0;		//判断有非法id的标志
			int key2=0;		//判断删除异常的标志
			
			for(int k=0;k<id.length;k++){
				ZFile f = fDao.getFileById(id[k]);
				if(f==null){
					Map<String, String> map = new HashMap<String, String>();
					map.put("result", "不合法id:"+id[k]);
					List<Map<String, String>> list = new ArrayList<>();
					list.add(map);
					result=list;
					return SUCCESS;
				}else {
					if(user.getUsername().equals(f.getUsername()) || user.getUsername().equals("admin")){		//要删除的文件的拥有者是自己
						if(fDao.deleteFile(id[k])){
							System.out.println("删除成功 id: "+id[k]);
						}else {
							key2++;
						}
					}else {
						err+=id[k]+" ";
						key++;
					}
				}
			}
			
			if(key>0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", err);
				List<Map<String, String>> list = new ArrayList<>();
				list.add(map);
				result=list;
			}else if(key2>0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "0");
				List<Map<String, String>> list = new ArrayList<>();
				list.add(map);
				result=list;
			}else{
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", "1");
				List<Map<String, String>> list = new ArrayList<>();
				list.add(map);
				result=list;
			}
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "请先登录,才能删除文件!");
			List<Map<String, String>> list = new ArrayList<>(); 
			list.add(map);
			result = list;
		}
		return SUCCESS;
	}
	
	public File getUpFile() {
		return upFile;
	}
	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}
	public String getUpFileFileName() {
		return upFileFileName;
	}
	public void setUpFileFileName(String upFileFileName) {
		this.upFileFileName = upFileFileName;
	}
	public String getUpFileContentType() {
		return upFileContentType;
	}
	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Integer[] getId() {
		return id;
	}
	public void setId(Integer[] id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
}
