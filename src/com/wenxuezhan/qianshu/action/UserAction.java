package com.wenxuezhan.qianshu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wenxuezhan.qianshu.entity.User;
import com.wenxuezhan.qianshu.service.UserService;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class UserAction extends ActionSupport implements ModelDriven<User>,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 521095043400813666L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap;     
    private UserService userService;
	private User user;
	private HttpServletRequest request;
	/** 
     * 构造方法 
     */  
    public UserAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    }  

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	} 

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		user = new User();
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	
	public String login(){		
		List<User> userlist=userService.login(user.getUser_account(), user.getUser_password());
		if(userlist.size()>0){
			HttpSession session=request.getSession();
			session.setAttribute("user_account", user.getUser_account());
			dataMap.clear();
			dataMap.put("success", true);
			dataMap.put("message", "登录成功！！");
			dataMap.put("user", userlist.get(0));
			return SUCCESS;
		}else{
			dataMap.clear();
			dataMap.put("success", false);
			dataMap.put("message", "登录失败！！账号或密码错误");
			return SUCCESS;
		}
	}
	
	/**
	 * 注册
	 * @return
	 */
    public String registry(){		
    	HttpSession session=request.getSession();
		String Validcode=String.valueOf(session.getAttribute("Validcode"));
		String code=request.getParameter("code");
		
		if(!Validcode.equals(code)) {
			dataMap.clear();
			dataMap.put("success", false);
			dataMap.put("message", "验证码错误！！");
			return SUCCESS;
		}else {
			List<User> userlist=userService.isRegistry(user.getUser_name());
			if(userlist.size()>0){
				dataMap.clear();
				dataMap.put("success", false);
				dataMap.put("message", "账号已存在，请重新注册！！");
				return SUCCESS;
			}else{
				if(userService.registry(user)) {
					dataMap.clear();
					dataMap.put("success", true);
					dataMap.put("message", "注册成功！！");
					return SUCCESS;
				}else {
					dataMap.clear();
					dataMap.put("success", false);
					dataMap.put("message", "注册失败！！请重新注册！");
					return SUCCESS;
				}
				
			}
		}
	}
    
    /**
     * 个人中心
     */
	public String getInfo() {
		HttpSession session=request.getSession();
		String user_account=(String)session.getAttribute("user_account");
		User user=this.userService.getInfo(user_account);
		dataMap.clear();
		dataMap.put("success", true);
		dataMap.put("user", user);
		return SUCCESS;
	}
	
	/**
	 * 修改个人资料
	 * @return
	 */
	public String alterInfo() {
		HttpSession session=request.getSession();
		String user_account=(String)session.getAttribute("user_account");
		user.setUser_account(user_account);
		if(this.userService.alterInfo(user)>0) {
			dataMap.clear();
			dataMap.put("success", true);
			dataMap.put("message", "修改成功");
			return SUCCESS;
		}else {
			dataMap.clear();
			dataMap.put("success", false);
			dataMap.put("message", "修改失败，请重新尝试");
			return SUCCESS;
		}		
	}
	
	/**
	 * 退出
	 */
	public String logout() {
		HttpSession session=request.getSession();
		session.setAttribute("user_account", "");
		dataMap.clear();
		dataMap.put("success", true);
		return SUCCESS;
	}
	
	/**
	 * 获得所有用户(除系统管理员)
	 */
	public String getall() {
		List<User> userlist=this.userService.getall();
		dataMap.clear();
		dataMap.put("success", true);
		dataMap.put("list", userlist);
		return SUCCESS;
	}
	
	/**
	 * 修改用户权限
	 */
	public String alterRole() {
		HttpSession session=request.getSession();
		String user_account=(String)session.getAttribute("user_account");
		int role=this.userService.getRole(user_account);
		System.out.println(user.getUser_id());
		if(role!=5) {
			dataMap.clear();
			dataMap.put("success", false);
			return SUCCESS;
		}else {
			this.userService.alterRole(user);
			dataMap.clear();
			dataMap.put("success", true);
			return SUCCESS;
		}
	}
}
