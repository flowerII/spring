package com.wenxuezhan.qianshu.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wenxuezhan.qianshu.entity.Article;
import com.wenxuezhan.qianshu.entity.User;
import com.wenxuezhan.qianshu.service.ArticleService;
import com.wenxuezhan.qianshu.service.UserService;

public class UploadArticleAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977587205625613331L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap;  
    private HttpServletRequest request;
    private UserService userService;
    private ArticleService articleService;
    public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    
    private String title;
    private int activity_id;
      
    public UploadArticleAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    } 
	
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	public String add_article() throws IOException {
		HttpSession session=request.getSession();
		String user_account=(String)session.getAttribute("user_account");
		
		User user=this.userService.getInfo(user_account);
		
		String realPath  = this.request.getContextPath()+"/article/"+activity_id;

		String url=null;
        if (image != null) {
            File savefile = new File(new File(realPath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
            url=realPath+"/"+imageFileName;
            
            Article a=new Article();
            a.setActivity_id(activity_id);
            a.setArticle_name(title);
            a.setArticle_url(url);
            a.setUser_id(user.getUser_id());
            a.setUser_name(user.getUser_name());
            
            this.articleService.add(a);
        }
        return SUCCESS;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
