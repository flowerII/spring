package com.wenxuezhan.qianshu.service;

import java.util.List;

import com.wenxuezhan.qianshu.dao.ArticleDao;
import com.wenxuezhan.qianshu.entity.Article;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class ArticleService {
	
	private ArticleDao articleDao;
	
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public boolean add(Article article) {
		return articleDao.add(article);
	}
	
	public int getArticlesPageNum(int activity_id) {
		return articleDao.getArticlesPageNum(activity_id);
	}
	
	public List<Article> getArticleByActivityIDAndPage(int page,int activity_id){
		return articleDao.getArticleByActivityIDAndPage(page, activity_id);
	}
	
	public int setPrize(int article_id,int article_prize) {
		return articleDao.setPrize(article_id, article_prize);
	}
	public boolean delete(int article_id) {
		return articleDao.delete(article_id);
	}
	
	public Article get(int article_id) {
		return articleDao.get(article_id);
	}

}
