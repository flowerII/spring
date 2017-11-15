package com.wenxuezhan.qianshu.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport implements ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977587205625613331L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap;  
    private HttpServletResponse response;
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
      
    public UploadAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    } 
	
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	public String up_picture() throws IOException {
		String realPath  = ServletActionContext.getRequest().getRealPath("")+"/upload";
		String savedir  = ServletActionContext.getRequest().getContextPath()+"/upload";

		String url=null;
        PrintWriter pw=null;
        if (image != null) {
            File savefile = new File(new File(realPath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
            pw = response.getWriter();  
            url="http://120.77.168.12:8080"+savedir+"/"+imageFileName;
            pw.write(url);
            pw.flush();  
            pw.close();
        }
        return null;
	}

	public void setServletResponse(HttpServletResponse arg0) {  
	        this.response=arg0;  
	          
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
}
