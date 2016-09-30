package com.ktds.board.articles.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.board.articles.biz.ArticlesBiz;
import com.ktds.board.articles.biz.ArticlesBizImpl;
import com.ktds.board.support.Param;

public class DoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticlesBiz articlesBiz;
    public DoDeleteServlet() {
        super();
        articlesBiz = new ArticlesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articlesId = Param.getStringParam(request, "articlesId");
		boolean isSuccess = articlesBiz.deleteArticle(articlesId);
		if(isSuccess) {
			response.sendRedirect("/Board/board/list");
		}
		else{
			response.sendRedirect("/Board/board/detail?errorCode=1");
		}
		
		
	}

}
