package com.wenxuezhan.qianshu.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author qianshu
 *
 * @date   2017年10月3日
 */
public class AuthenticatedInterceptor extends MethodFilterInterceptor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2997422352853647135L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		Object username=session.get("user_account");
		System.out.println(username.toString());
		if(username!=null){
			return arg0.invoke();
		}else{
			return "login";
		}
	}

}
