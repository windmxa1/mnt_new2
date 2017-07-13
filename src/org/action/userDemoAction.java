package org.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.dao.ZUserDao;
import org.model.ZUser;

import com.opensymphony.xwork2.ActionSupport;

public class userDemoAction extends ActionSupport{
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String data;
	private String userStore;
	
	public String add() throws Exception{
		System.out.println("----add.action----");
		
		ServletRequest request = ServletActionContext.getRequest();
		
		InputStream inputStream;

		try {
			inputStream = request.getInputStream();
			String strMessage = "";
			String strResponse = "";
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			while ((strMessage = reader.readLine()) != null) {
				strResponse += strMessage;
			}
			reader.close();
			inputStream.close();

//			System.out.println(strResponse);
			JSONObject jo = JSONObject.fromObject(strResponse);
			String username = jo.getString("username");
			String password = jo.getString("password");
			String name = jo.getString("name");
			System.out.println("username:"+username);
			System.out.println("password:"+password);
			System.out.println("name:"+name);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	//---------------------------------------------
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUserStore() {
		return userStore;
	}

	public void setUserStore(String userStore) {
		this.userStore = userStore;
	}
}
