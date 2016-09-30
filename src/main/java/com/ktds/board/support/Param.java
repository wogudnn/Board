package com.ktds.board.support;

import javax.servlet.http.HttpServletRequest;

public class Param {

	public static String getStringParam(HttpServletRequest request, String paramName){
		//리퀘스트 객체와, 파라미터 이름, 값(값이 들어오지 않을경우 디폴트값을)을 리턴시킨다.
		return getStringParam(request, paramName, "");
		
	}
	
	public static String getStringParam(HttpServletRequest request, String paramName, String defaultValue){
		//파라미터 명칭을 값에 넣는다.
		String value = request.getParameter(paramName); 
		//값이 널이라면 디폴트 값을 리턴한다.
		if(value==null||value.length()==0) {
			value=defaultValue;
		}
		//값을 리턴시킨다.
		return value;
	}
	
	public static int getIntParam(HttpServletRequest request, String paramName) {
		//파라미터명과, 값을 리턴한다.
		return getIntParam(request, paramName, 0);
	}
	//파라미터가 인트값이 넘어왔을때 처리방법
	public static int getIntParam(HttpServletRequest request, String paramName, int defaultValue) {
		
		String value = getStringParam(request, paramName);
		
		try{
			//스트링타입의 값을 인트로 캐스팅한다.
			int intValue = Integer.parseInt(value);
			//성공적으로 캐스팅하면 값을 리턴한다.
			return intValue;
		}catch (NumberFormatException nfe) {
			//캐스팅에 실패해 넘버포맷예외가 발생하면 디폴트값을 리턴한다.
			return defaultValue;
		}
		
	}
}
