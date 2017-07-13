package org.filter;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.model.ZUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class authInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getInvocationContext().getName();

		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getContextPath();
		System.out
				.println(pName + "--"
						+ new Timestamp(System.currentTimeMillis()) + "--"
						+ actionName);

		return invocation.invoke(); // 放行

		/*
		 * HttpSession session = ServletActionContext.getRequest().getSession();
		 * System.out.println(session.getAttribute("aList")); List<String> aList
		 * = (List) session.getAttribute("aList"); aList.add("getMapInfo"); for
		 * (String a : aList) { if (actionName.equals(a)) { return
		 * invocation.invoke(); // 放行 } } List<Map<String, String>> result = new
		 * ArrayList<>(); Map<String, String> message = new HashMap<String,
		 * String>(); message.put("description", "无权限访问");
		 * message.put("message", "error"); result.add(message);
		 * ActionContext.getContext().put("result", result); //
		 * session.put("result", result);
		 * System.out.println(JSONArray.fromObject(result).toString());
		 * 
		 * return Action.ERROR;
		 */
	}
}
