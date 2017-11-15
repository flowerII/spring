package com.wenxuezhan.qianshu.service;

import java.util.List;

import com.wenxuezhan.qianshu.dao.ActivityDao;
import com.wenxuezhan.qianshu.entity.Activity;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class ActivityService {
	
	private ActivityDao activityDao;
	
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public List<Activity> get_home_5() {
		return activityDao.get_home_5();
	}
	
	public boolean add(Activity activity) {
		return activityDao.add(activity);
	}
	
	public List<Activity> not_publish(){
		return activityDao.not_publish();
	}
	
	public List<String> yearslist(){
		return activityDao.yearslist();
	}
	
	public List<String> yearslist_notpublicsh(){
		return activityDao.yearslist_notpublicsh();
	}
	
	public List<Object[]> get_by_year(String activity_year){
		return activityDao.get_by_year(activity_year);
	}
	
	public boolean delete(int activity_id) {
		return activityDao.delete(activity_id);
	}

	public Activity content(int activity_id) {
		// TODO Auto-generated method stub
		return activityDao.content(activity_id);
	}
	
	public List<Object[]> get_by_year_notpublish(String activity_year){
		return activityDao.get_by_year_notpublish(activity_year);
	}

}
