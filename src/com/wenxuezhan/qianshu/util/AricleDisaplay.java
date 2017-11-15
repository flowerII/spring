package com.wenxuezhan.qianshu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
*
*@author qianshu
*@date   2017年11月4日
*/
public class AricleDisaplay {
	
	public static StringBuffer display(String url) {  
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
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
		return stringbuffer;
	}

}
