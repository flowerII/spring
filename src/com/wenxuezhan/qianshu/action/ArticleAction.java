package com.wenxuezhan.qianshu.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wenxuezhan.qianshu.entity.Activity;
import com.wenxuezhan.qianshu.entity.Article;
import com.wenxuezhan.qianshu.service.ArticleService;
import com.wenxuezhan.qianshu.util.AricleDisaplay;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class ArticleAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 521095043400813666L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap;     
    private ArticleService articleService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    private String title;
	private String activity_id;
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

    public ArticleAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    }  

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	public void setServletResponse(HttpServletResponse arg0) {  
        this.response=arg0;  
          
    }

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	public String list(){
		int activity_id=Integer.parseInt(request.getParameter("activity"));
		int num=this.articleService.getArticlesPageNum(activity_id);
		
		int page=Integer.parseInt(request.getParameter("page"));
		List<Article> list=this.articleService.getArticleByActivityIDAndPage(page, activity_id);
		
		request.setAttribute("num", (int)num/10+1);
		request.setAttribute("articles", list);
	    return "articleList";	    
	}
	
	public String setPrize() {
		int article_id=Integer.parseInt(request.getParameter("article_id"));
		int article_prize=Integer.parseInt(request.getParameter("article_prize"));
		int flag=this.articleService.setPrize(article_id, article_prize);
		if(flag>0) {
			dataMap.clear();
			dataMap.put("success", true);
		}else {
			dataMap.clear();
			dataMap.put("success", false);
		}
		return SUCCESS;
	}
	
	public String delete() {
		int article_id=Integer.parseInt(request.getParameter("article_id"));
		if(this.articleService.delete(article_id)) {
			dataMap.clear();
			dataMap.put("success", true);
		}else {
			dataMap.clear();
			dataMap.put("success", false);
		}	
		return SUCCESS;
	}
	
	public String content() {
		int article_id=Integer.parseInt(request.getParameter("article"));
		Article article=this.articleService.get(article_id);
		request.setAttribute("article", article);
		StringBuffer stringbuffer=AricleDisaplay.display(article.getArticle_url());
		request.setAttribute("content", stringbuffer);
		//request.setAttribute("url", article.getArticle_url());
		return "content";
	}
	
	public String prizeIframe() {
		int activity_id=Integer.parseInt(request.getParameter("activity"));
		System.out.println(activity_id);
		
		return null;
	}
}
