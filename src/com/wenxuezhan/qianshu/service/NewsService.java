package com.wenxuezhan.qianshu.service;

import java.util.List;

import com.wenxuezhan.qianshu.dao.NewsDao;
import com.wenxuezhan.qianshu.entity.News;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class NewsService {
	
	private NewsDao newsDao;

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public List<News> get_home_5() {
		return newsDao.get_home_5();
	}
	
	public boolean add(News news) {
		return newsDao.add(news);
	}
	
	public List<News> get_all(){
		return newsDao.get_all();
	}
	
	public boolean delete(int new_id) {
		return newsDao.delete(new_id);
	}
	
	public News content(int new_id) {
		return newsDao.content(new_id);
	}

}
