package com.ktds.board.articles.biz;

import java.util.List;

import com.ktds.board.articles.vo.ArticlesVO;

public interface ArticlesBiz {

	public boolean addNewArticle(ArticlesVO articlesVO);

	public List<ArticlesVO> getAllArticles();

	public ArticlesVO getArticleBy(String articleId);

	public boolean deleteArticle(String articlesId);


}
