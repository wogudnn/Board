package com.ktds.board.articles.biz;

import java.util.List;

import com.ktds.board.articles.dao.ArticlesDao;
import com.ktds.board.articles.dao.ArticlesDaoImpl;
import com.ktds.board.articles.vo.ArticlesVO;

public class ArticlesBizImpl implements ArticlesBiz {
	
	public ArticlesDao articlesDao;
	
	public ArticlesBizImpl() {
		articlesDao = new ArticlesDaoImpl();
	}

	@Override
	public boolean addNewArticle(ArticlesVO articlesVO) {

		return articlesDao.addNewArticles(articlesVO) > 0;
	}
	@Override
	public List<ArticlesVO> getAllArticles() {
		
		return articlesDao.getAllArticles();
	}
	@Override
	public ArticlesVO getArticleBy(String articleId) {
		return articlesDao.getArticleBy(articleId);
	}
	@Override
	public boolean deleteArticle(String articlesId) {
		return articlesDao.deleteArticle(articlesId) > 0;
	}
}
