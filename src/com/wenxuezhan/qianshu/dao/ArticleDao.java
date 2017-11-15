package com.wenxuezhan.qianshu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wenxuezhan.qianshu.entity.Article;

/**
* @author qianshu
* @date   2017年10月13日
*/
public class ArticleDao {
	
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public boolean add(Article article) {
		getSession().save(article);
		return true;
	}
	
	public int getArticlesPageNum(int activity_id) {
		String hql="select count(*) from Article a where a.activity_id=:activity_id order by a.article_id desc";
		return getSession().createQuery(hql).setParameter("activity_id", activity_id).list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticleByActivityIDAndPage(int page,int activity_id){
		String hql="from Article a where a.activity_id=:activity_id order by a.article_id desc";
		return getSession().createQuery(hql).setParameter("activity_id", activity_id).setFirstResult((page-1)*10).setProperties(10).list();
	}
	
	public int setPrize(int article_id,int article_prize) {
		String hql="update Article a set a.article_prize=:article_prize where a.article_id=:article_id";
		int flag=getSession().createQuery(hql).setParameter("article_prize", article_prize).setParameter("article_id", article_id).executeUpdate();
		return flag;
	}
	
	public boolean delete(int article_id){
		Article article=new Article();
		article.setArticle_id(article_id);
		getSession().delete(article);
		return true;
	} 
	
	public Article get(int article_id) {
		String hql="from Article a where a.article_id=:article_id";
		return (Article) getSession().createQuery(hql).setParameter("article_id", article_id).list().get(0);
	}

}
