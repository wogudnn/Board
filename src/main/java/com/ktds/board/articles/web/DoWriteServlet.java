package com.ktds.board.articles.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.board.articles.biz.ArticlesBiz;
import com.ktds.board.articles.biz.ArticlesBizImpl;
import com.ktds.board.articles.vo.ArticlesVO;
import com.ktds.board.constants.Session;
import com.ktds.board.support.Param;
import com.ktds.board.user.vo.UserVO;

public class DoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesBiz articlesBiz;
	
    public DoWriteServlet() {
        super();
        articlesBiz = new ArticlesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String articleSubject = Param.getStringParam(request, "articleSubject");
		String articleContent = Param.getStringParam(request, "articleContent");
	
		articleContent = articleContent.replaceAll("\n", "<br/>")
										.replaceAll("\r", "");
		
		ArticlesVO articlesVO = new ArticlesVO();
		articlesVO.setArticleSubject(articleSubject);
		articlesVO.setArticleContent(articleContent);
		
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute(Session.USER_INFO);
		
		articlesVO.setUserId(userInfo.getUserId());
		
		boolean isSuccess = articlesBiz.addNewArticle(articlesVO);
		if(isSuccess) {
			response.sendRedirect("/Board/board/list");
		}
		else{
			response.sendRedirect("/Board/board/write?error=1");
		}
		
	}

}
