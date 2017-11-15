package com.wenxuezhan.qianshu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wenxuezhan.qianshu.entity.Activity;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class ActivityDao {
	
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> get_home_5() {
		String hql = "FROM Activity act where act.activity_surepublish=0 order by act.activity_id desc";  
	    Query query = getSession().createQuery(hql);     
	    query.setMaxResults(5);  
	    List<Activity> list = query.list();   
	    return list;  
	}
	
	public boolean add(Activity activity) {
		getSession().save(activity);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> not_publish(){
		String hql = "FROM Activity act where act.activity_surepublish=0 order by act.activity_id desc";  
	    Query query = getSession().createQuery(hql);      
	    List<Activity> list = query.list();   
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> yearslist(){
		String hql = "select distinct act.activity_year FROM Activity act order by act.activity_id desc";  
	    Query query = getSession().createQuery(hql);      
	    List<String> list = query.list();   
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> yearslist_notpublicsh(){
		String hql = "select distinct act.activity_year FROM Activity act where act.activity_surepublish=0 order by act.activity_id desc";  
	    Query query = getSession().createQuery(hql);      
	    List<String> list = query.list();   
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> get_by_year(String activity_year){
		String hql = "select act.activity_id,act.activity_name FROM Activity act where act.activity_year=:activity_year order by act.activity_id desc";  
	    Query query = getSession().createQuery(hql).setParameter("activity_year", activity_year);      
	    List<Object[]> list = query.list();   
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> get_by_year_notpublish(String activity_year){
		String hql = "select act.activity_id,act.activity_name FROM Activity act where act.activity_year=:activity_year and act.activity_surepublish=0 order by act.activity_id desc";  
	    Query query = getSession().createQuery(hql).setParameter("activity_year", activity_year);      
	    List<Object[]> list = query.list();   
	    return list;
	}
	
	public boolean delete(int activity_id) {
		Activity activity=new Activity();
		activity.setActivity_id((activity_id));
		getSession().delete(activity);
		return true;
	}

	@SuppressWarnings("unchecked")
	public Activity content(int activity_id) {
		// TODO Auto-generated method stub
		String hql = "FROM Activity act where act.activity_id=:activity_id";  
	    Query query = getSession().createQuery(hql).setParameter("activity_id", activity_id);      
	    List<Activity> list =query.list();
		return list.get(0);
	}

}
