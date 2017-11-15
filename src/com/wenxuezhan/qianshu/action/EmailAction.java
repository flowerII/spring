package com.wenxuezhan.qianshu.action;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.mail.util.MailSSLSocketFactory;

public class EmailAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977587205625613331L;
	
	//将会被Struts2序列化为JSON字符串的对象  
    private Map<String, Object> dataMap;  
    private HttpServletRequest request;
      
    public EmailAction() {    
    	//初始化Map对象  
        dataMap = new HashMap<String, Object>();
    } 
	
	public Map<String, Object> getDataMap() {  
        return dataMap;  
    }
	
	public String get_Code() {
		String email=request.getParameter("user_account");
	    int a = (int)(Math.random()*(9999-1000+1))+1000;
		HttpSession session2=request.getSession();
		session2.setAttribute("Validcode", a);		  
		try {
			String from = "1479676948@qq.com";
			String host = "smtp.qq.com";  
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			properties.put("mail.smtp.auth", "true");
			MailSSLSocketFactory sf;
			try {
				sf = new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
			      properties.put("mail.smtp.ssl.enable", "true");
			      properties.put("mail.smtp.ssl.socketFactory", sf);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			      			      
			Session session = Session.getInstance(properties,new Authenticator(){
			    public PasswordAuthentication getPasswordAuthentication()
			    {
			         return new PasswordAuthentication("1479676948@qq.com", "nbfkjcrkculajgbb"); //�������ʼ��û���������
			    }
			});
		    try{
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(email));
		         message.setSubject("您正在注册五邑大学文学交流网站！！！");
		         message.setText("验证码："+a+",请勿泄漏，如非本人操作请忽略！！");
		         Transport.send(message);
		         dataMap.clear();
				 dataMap.put("success", true);
				 dataMap.put("message", "验证码已发送至您的邮箱，请注意查收");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		} catch (Exception e1) {
		e1.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
}
