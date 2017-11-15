package com.wenxuezhan.qianshu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wenxuezhan.qianshu.entity.News;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class NewsDao {
	
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> get_home_5() {
		String hql = "FROM News news order by news.new_id desc";  
	    Query query = getSession().createQuery(hql);     
	    query.setMaxResults(5);  
	    List<News> list = query.list();   
	    return list;  
	}
	
	public boolean add(News news) {
		getSession().save(news);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> get_all() {
		String hql = "FROM News news order by news.new_id desc";  
	    Query query = getSession().createQuery(hql);      
	    List<News> list =query.list();   
	    return list;  
	}
	
	public boolean delete(int new_id) {
		News news=new News();
		news.setNew_id(new_id);
		getSession().delete(news);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public News content(int new_id) {
		String hql = "FROM News news where news.new_id=:new_id";  
	    Query query = getSession().createQuery(hql).setParameter("new_id", new_id);      
	    List<News> list =query.list();
		return list.get(0);
	}

}
