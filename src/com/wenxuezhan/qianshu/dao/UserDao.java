package com.wenxuezhan.qianshu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wenxuezhan.qianshu.entity.User;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class UserDao {
	
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<User> login(String user_account,String user_password) {
		String hql="from User u where u.user_account=:user_account and u.user_password=:user_password";
		List<User> userlist=getSession().createQuery(hql).setParameter("user_account", user_account).setParameter("user_password", user_password).list();
		return userlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> isRegistry(String user_account) {
		String hql="from User u where u.user_account=:user_account";
		List<User> userlist=getSession().createQuery(hql).setParameter("user_account", user_account).list();
		return userlist;
	}
	
	public boolean registry(User user) {
		user.setUser_name(user.getUser_name());
		getSession().save(user);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getall() {
		String hql="from User u where u.user_role!=5 order by u.user_role desc";
		return getSession().createQuery(hql).list();
	}
	
	public User getInfo(String user_account) {
		String hql="from User u where u.user_account=:user_account";
		User user=(User)getSession().createQuery(hql).setParameter("user_account", user_account).uniqueResult();
		return user;
	}
	
	public int alterInfo(User user) {
		String hql="update User u set u.user_name=:user_name, u.user_sno=:user_sno,u.user_description=:user_description where u.user_account=:user_account";
		int flag=getSession().createQuery(hql).setParameter("user_name", user.getUser_name()).setParameter("user_sno", user.getUser_sno()).setParameter("user_description", user.getUser_description())
				.setParameter("user_account", user.getUser_account()).executeUpdate();
		return flag;
	}
	
	public int getRole(String user_account) {
		String hql="select u.user_role from User u where u.user_account=:user_account";
		return (int)getSession().createQuery(hql).setParameter("user_account", user_account).list().get(0);
	}
	
	public int alterRole(User user) {
		String hql="update User u set u.user_role=:user_role where u.user_id=:user_id";
		int flag=getSession().createQuery(hql).setParameter("user_role", user.getUser_role()).setParameter("user_id", user.getUser_id()).executeUpdate();
		return flag;
	}

}
