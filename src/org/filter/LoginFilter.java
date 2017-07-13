package org.filter;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginFilter extends AbstractInterceptor{
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("----------");
		String aName = ServletActionContext.getActionMapping().getName();
		System.out.println("action name:"+aName);
		
		String actionName = arg0.getInvocationContext().getName();  
        System.out.println("actionName:"+actionName);
        // 获取action后附带参数  
        Map parameters = arg0.getInvocationContext().getParameters();
        System.out.println("parameters:"+parameters);
		
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		System.out.println("username in session is:"+session.get("username"));
        String result=arg0.invoke();
        
		return result;
	}
	
}
