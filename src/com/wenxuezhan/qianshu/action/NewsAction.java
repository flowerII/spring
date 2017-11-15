package com.wenxuezhan.qianshu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wenxuezhan.qianshu.entity.News;
import com.wenxuezhan.qianshu.service.NewsService;
import com.wenxuezhan.qianshu.service.UserService;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class NewsAction extends ActionSupport implements ModelDriven<News>,ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 521095043400813666L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap; 
    private News news;

	private NewsService newsService;
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
	/** 
     * 构造方法 
     */  
    public NewsAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    }  
    
    public News getModel() {
		news=new News();
		return news;
	}
    
    public void setNews(News news) {
		this.news = news;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	public void setServletResponse(HttpServletResponse arg0) {  
        this.response=arg0;  
          
    }
	
	public String get_home_5() {
		List<News> newslist=newsService.get_home_5();
		dataMap.clear();
		dataMap.put("newslist", newslist);
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
			boolean flag=newsService.add(news);
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
	
	public String get_all() {
		List<News> newslist=newsService.get_all();
		dataMap.clear();
		dataMap.put("newslist", newslist);
		return SUCCESS;
	}
	
	public String delete() {
		int new_id=Integer.parseInt(request.getParameter("new_id"));		
		if(this.newsService.delete(new_id)) {
			dataMap.clear();
			dataMap.put("success",true);
		}else {
			dataMap.clear();
			dataMap.put("success",false);
		}
		return SUCCESS;
	}
	
	public String text() {
		int new_id=Integer.parseInt(request.getParameter("url"));
		System.out.println(new_id);
		News news=this.newsService.content(new_id);
		dataMap.clear();
		dataMap.put("success",true);
		dataMap.put("news",news);
		return SUCCESS;
	}
	
	public String content() {
		return "input";
	}
	
}
