package com.ktds.board.user.dao;

import com.ktds.board.user.vo.UserVO;

public interface UserDao {

	public int signUpuser(UserVO userVO);

	public UserVO getUserBy(UserVO user);

	public int countUserEmail(String userEmail);
	
}
