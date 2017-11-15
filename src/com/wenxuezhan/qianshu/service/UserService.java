package com.wenxuezhan.qianshu.service;

import java.util.List;

import com.wenxuezhan.qianshu.dao.UserDao;
import com.wenxuezhan.qianshu.entity.User;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public List<User> login(String user_account,String user_password) {
		return userDao.login(user_account, user_password);
	}
	
	public List<User> isRegistry(String user_account) {
		return userDao.isRegistry(user_account);
	}
	
	public boolean registry(User user) {
		return userDao.registry(user);
	}
	
	public User getInfo(String user_account){
		return userDao.getInfo(user_account);
	}
	
	public int alterInfo(User user) {
		return userDao.alterInfo(user);
	}
	
	public int getRole(String user_account) {
		return userDao.getRole(user_account);
	}
	public List<User> getall() {
		return userDao.getall();
	}
	
	public int alterRole(User user) {
		return userDao.alterRole(user);
	}

}
