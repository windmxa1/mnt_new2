package org.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.tool.R;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginFilter extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getInvocationContext().getName();
		Map<String, Object> session = ActionContext.getContext().getSession();
//		System.out.println(session.get("user"));
		System.out.println(invocation.getInvocationContext().getName());
		if (actionName.equals("login")) {// 登录不做拦截，但要做记录，所以需要进入拦截器
			return invocation.invoke();
		}
		if (session.get("user") == null) {
			ActionContext.getContext().put("result",
					R.getJson(-999, "未登录", false));
			return Action.ERROR;
		}
		// System.out.println("user:" + session.get("user"));
		return invocation.invoke();
	}

}
