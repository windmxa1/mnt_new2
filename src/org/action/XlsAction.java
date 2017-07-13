package org.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class XlsAction extends ActionSupport{
	private List result;
	private List<String> ids;
	
	public String xlsExport() throws Exception {
		System.out.println("\n——导出xls——");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
		String t = df.format(new Date());
		
		ServletRequest request = ServletActionContext.getRequest();
		String path =request.getRealPath("/");		//项目在服务器的路径
		
		//清除以往的服务器中的临时xls文件
//		File folder = new File(path);
//		File[] files =folder.listFiles();
//		for(File file:files){
//			if(file.getName().endsWith(".xls")){
//				System.out.println("deleting file:"+file.getName());
//				file.delete();
//			}
//		}
		
		WritableWorkbook book= Workbook.createWorkbook(new File(path+t+".xls"));//xls文件名
		
		WritableSheet sheet=book.createSheet("第一页",0); //第一页工作表
		try {
			sheet.addCell(new Label(0, 0, "编号"));
			sheet.addCell(new Label(1, 0, "地址"));
			sheet.addCell(new Label(2, 0, "设备序列号"));
			sheet.addCell(new Label(3, 0, "设备状态"));
			sheet.addCell(new Label(4, 0, "告警"));
			sheet.addCell(new Label(5, 0, "IP"));
			int cnt=1;	//第二行
			for(String line:ids){
				JSONObject jo = JSONObject.fromObject(line);
				String bianhao = jo.getString("编号");
				String dizhi = jo.getString("地址");
				String xuliehao = jo.getString("设备序列号");
				String zhuangtai = jo.getString("设备状态");
				String gaojing = jo.getString("告警");
				String ip = jo.getString("IP");
				String array[]={bianhao,dizhi,xuliehao,zhuangtai,gaojing,ip};
				
				for(int i=0;i<6;i++)
					sheet.addCell(new Label(i, cnt, array[i]));
				cnt++;	
			}
			book.write();
			book.close();
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "http://localhost:8080/mnt_new/"+t+".xls");
			List<Map<String, String>> list = new ArrayList<>();
			list.add(map);
			result = list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String pathTest() throws Exception{
		ServletRequest request = ServletActionContext.getRequest();
		System.out.println(request.getRealPath("/"));
		return SUCCESS;
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
