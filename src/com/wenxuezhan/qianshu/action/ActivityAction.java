package com.wenxuezhan.qianshu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wenxuezhan.qianshu.entity.Activity;
import com.wenxuezhan.qianshu.service.ActivityService;
import com.wenxuezhan.qianshu.service.UserService;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class ActivityAction extends ActionSupport implements ModelDriven<Activity>,ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 521095043400813666L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap;     
    private Activity activity;
    private ActivityService activityService;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ActivityAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    }  

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	public void setServletResponse(HttpServletResponse arg0) {  
        this.response=arg0;  
          
    }
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }

	public Activity getModel() {
		activity=new Activity();
		return activity;
	}
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String get_home_5() {
		List<Activity> activities=activityService.get_home_5();
		dataMap.clear();
		dataMap.put("activities",activities);
		return SUCCESS;
	}
	
	public String add() throws IOException {
		HttpSession session=request.getSession();
		String user_account=(String)session.getAttribute("user_account");
		int role=this.userService.getRole(user_account);
		PrintWriter pw=null;
		pw = response.getWriter();
		if(role!=5) {
			dataMap.clear();
			dataMap.put("success", false);
			dataMap.put("message", "您没有此权限！！");
			pw.write("false");
			pw.flush();  
	        pw.close();
			return SUCCESS;
		}else {
			Calendar date = Calendar.getInstance();
	        String year = String.valueOf(date.get(Calendar.YEAR));
	        activity.setActivity_year(year);
			boolean flag=activityService.add(activity);
			if(flag) {
				dataMap.clear();
				dataMap.put("success",true);
				pw.write("ok");
				pw.flush();  
		        pw.close();
				return SUCCESS;
			}else {
				dataMap.clear();
				dataMap.put("success",false);
				pw.write("false");
				pw.flush();  
		        pw.close();
				return SUCCESS;
			}
		}
		
	}
	
	/**
	 * 未发布活动
	 */
	public String not_publish() {
		List<Activity> activities=activityService.not_publish();
		dataMap.clear();
		dataMap.put("activities",activities);
		return SUCCESS;
	}
	
	/**
	 * 活动年份
	 */
	public String yearslist() {
		List<String> list=this.activityService.yearslist();
		System.out.println(list.toString());
		Activity a;
		List<Activity> activitylist=new ArrayList<Activity>();
		for(String link : list){
			a=new Activity();
			a.setActivity_year(link);
			activitylist.add(a);
		} 
		
		dataMap.clear();
		dataMap.put("yearslist",activitylist);
		return SUCCESS;
	}
	
	/**
	 * 根据年份获取活动
	 */
	public String get_by_year() {
		Gson gs=new Gson();
		String activity_year=request.getParameter("activity_year");
		List<Object[]> list=this.activityService.get_by_year(activity_year);
		JsonArray activitylist=new JsonArray();
		JsonObject obj;
		for(Object[] link : list){  
			int activity_id =  (int) link[0];  
			String activity_name = (String) link[1];   
			obj= new JsonObject();
			obj.addProperty("activity_id", activity_id);
			obj.addProperty("activity_name", activity_name);
			activitylist.add(obj);
		} 
		
		dataMap.clear();
		dataMap.put("activitylist",gs.toJson(activitylist));
		return SUCCESS;
	}
	
	public String get_by_year_notpublish() {
		Gson gs=new Gson();
		String activity_year=request.getParameter("activity_year");
		List<Object[]> list=this.activityService.get_by_year_notpublish(activity_year);
		JsonArray activitylist=new JsonArray();
		JsonObject obj;
		for(Object[] link : list){  
			int activity_id =  (int) link[0];  
			String activity_name = (String) link[1];   
			obj= new JsonObject();
			obj.addProperty("activity_id", activity_id);
			obj.addProperty("activity_name", activity_name);
			activitylist.add(obj);
		} 
		
		dataMap.clear();
		dataMap.put("activitylist",gs.toJson(activitylist));
		return SUCCESS;
	}
	
	/**
	 * 删除
	 */
	public String delete() {
		int activity_id=Integer.parseInt(request.getParameter("activity_id"));

		if(this.activityService.delete(activity_id)) {
			dataMap.clear();
			dataMap.put("success", true);
		}else {
			dataMap.clear();
			dataMap.put("success", false);
		}	
		return SUCCESS;
	}
	
	/**
	 * 未发布活动年份
	 */
	public String yearslist_notpublicsh() {	
		List<String> yearlist=this.activityService.yearslist_notpublicsh();
		
		Gson gs=new Gson();
		JsonArray list=new JsonArray();
		JsonObject obj;
		for(String years : yearlist){  
			String activity_year = years;   
			obj= new JsonObject();
			obj.addProperty("activity_year", activity_year);
			list.add(obj);
		} 
		dataMap.clear();
		dataMap.put("success", true);
		dataMap.put("list", gs.toJson(list));
		return SUCCESS;
	}
	
	public String content() {
		return "input";
	}
	
	public String text() {
		int activity_id=Integer.parseInt(request.getParameter("url"));
		Activity activity=this.activityService.content(activity_id);
		dataMap.clear();
		dataMap.put("success",true);
		dataMap.put("activity",activity);
		return SUCCESS;
	}
}
