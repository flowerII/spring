package com.wenxuezhan.qianshu.action;

import java.io.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
*
*@author qianshu
*@date   2017年11月3日
*/
public class ArticleDisplayAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
	
	public void display() throws IOException {
		String url=request.getParameter("url");
		PrintWriter out;
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();  
		File file = new File(url);
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(suffix);
		StringBuffer stringbuffer=new StringBuffer();
		if(suffix.equals("txt")){
			try {
                String encoding="GBK";
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	stringbuffer.append(lineTxt+"\n");
                        //out.println(lineTxt);
                    }
                    out.println(stringbuffer);
                    read.close();
		        }else{
		            System.out.println("找不到指定的文件");
		        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
		}else if(suffix.equals("docx")){
			try {
	        	String str = "";
	            int i=0;
	        	InputStream is = new FileInputStream(file);
	            XWPFDocument doc = new XWPFDocument(is);
	            List<XWPFParagraph> paras = doc.getParagraphs();
	            for (XWPFParagraph para : paras) {
	            	i++;
	            	if(i==1){
	            		//out.println("                                   "+para.getText());
	            		stringbuffer.append(para.getText()+"\n");
	            		System.out.println(para.getText());
	            	}else{
	            		//out.println("    "+para.getText());
	            		stringbuffer.append(para.getText()+"\n");
	            		System.out.println(para.getText());
	            	}
	            }
	            out.println(stringbuffer);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}else{
			try {
				FileInputStream fis = new FileInputStream(file);
				HWPFDocument hwpfd = new HWPFDocument(fis);
				WordExtractor wordExtractor = new WordExtractor(hwpfd);
				String[] paragraph = wordExtractor.getParagraphText();
				for (int i = 0; i < paragraph.length; i++) {
					//out.println("        "+paragraph[i]);
					stringbuffer.append(paragraph[i]+"\n");
				}
				out.println(stringbuffer);
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		 this.response=arg0;  
	}

}
