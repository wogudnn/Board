package com.ktds.board.user.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.board.articles.vo.ArticlesVO;
import com.ktds.board.constants.Session;
import com.ktds.board.user.dao.UserDao;
import com.ktds.board.user.dao.UserDaoImpl;
import com.ktds.board.user.vo.UserVO;

public class UserBizImpl implements UserBiz{

	private UserDao userDao;
	
	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}
	
	@Override
	public boolean signUpuser(UserVO userVO) {
		
		return userDao.signUpuser(userVO) > 0;
	}
	@Override
	public boolean signIn(UserVO user, HttpServletRequest request) {
		
		UserVO userInfo = userDao.getUserBy(user);
		
		if( userInfo != null 
				&& userInfo.getUserId() != null
				&& userInfo.getUserId().length() > 0){
			HttpSession session =  request.getSession();
			session.setAttribute(Session.USER_INFO, userInfo);
			return true;
			
		}
		
		return false;
	}
	@Override
	public boolean isExsistUserEmail(String userEmail) {
		
		return userDao.countUserEmail(userEmail) > 0;
	}
}
