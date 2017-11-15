package com.wenxuezhan.qianshu.entity;
/** 
* @author  hua'er 
* @time    2017年4月17日 
*/
public class News {
	private int new_id;
	private int user_id;
	private String new_time;
	private String new_name;
	private String new_content;
	public int getNew_id() {
		return new_id;
	}
	public void setNew_id(int new_id) {
		this.new_id = new_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNew_content() {
		return new_content;
	}
	public void setNew_content(String new_content) {
		this.new_content = new_content;
	}
	public String getNew_time() {
		return new_time;
	}
	public void setNew_time(String new_time) {
		this.new_time = new_time;
	}
	public String getNew_name() {
		return new_name;
	}
	public void setNew_name(String new_name) {
		this.new_name = new_name;
	}

}
