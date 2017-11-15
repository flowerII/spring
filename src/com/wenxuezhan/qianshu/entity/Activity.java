package com.wenxuezhan.qianshu.entity;

/** 
* @author  hua'er 
* @time    2017年4月17日 
*/
public class Activity {
	private int activity_id;
	private String activity_name;
	private String activity_starttime;
	private String activity_endtime;
	private String activity_year;
	private String activity_description;
	private int activity_surepublish;
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	
	public String getActivity_starttime() {
		return activity_starttime;
	}
	public void setActivity_starttime(String activity_starttime) {
		this.activity_starttime = activity_starttime;
	}
	public String getActivity_endtime() {
		return activity_endtime;
	}
	public void setActivity_endtime(String activity_endtime) {
		this.activity_endtime = activity_endtime;
	}
	public String getActivity_description() {
		return activity_description;
	}
	public void setActivity_description(String activity_description) {
		this.activity_description = activity_description;
	}
	public String getActivity_year() {
		return activity_year;
	}
	public void setActivity_year(String activity_year) {
		this.activity_year = activity_year;
	}
	public int getActivity_surepublish() {
		return activity_surepublish;
	}
	public void setActivity_surepublish(int activity_surepublish) {
		this.activity_surepublish = activity_surepublish;
	}

}
