package com.ktds.board.articles.dao;

import java.util.List;

import com.ktds.board.articles.vo.ArticlesVO;

public interface ArticlesDao {

	public int addNewArticles(ArticlesVO articlesVO);

	public List<ArticlesVO> getAllArticles();

	public ArticlesVO getArticleBy(String articleId);

	public int deleteArticle(String articlesId);


}
