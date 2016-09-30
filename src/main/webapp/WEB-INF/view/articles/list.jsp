<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Board/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/Board/css/grid.css" />
<script type="text/javascript" src="/Board/js/jquery-3.1.1.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="list">
			<table class="grid">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
				<c:if test="${empty articles}">
					<tr>
						<td colspan="6">등록된 게시글이 없습니다.</td> 
					</tr>
					
				</c:if>
				
				<c:forEach items="${articles}" var="article">
					<tr>
					<c:set var="number" value="${fn:split(article.articleId, '-')[2]}" />
					<fmt:parseNumber var="number" type="number" value="${number}"/>
						<td>${number}</td>
						<td><a href="/Board/board/detail?articleId=${article.articleId}">${article.articleSubject}</a></td>
						<td>${article.userVO.userNickname}</td>
						<td>${article.createdDate}</td>
						<td>${article.hitCount}</td>
						<td>${article.recommendCount}</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top: 5px;">
				<div class="left">
					<a href="/Board/board/write">글쓰기</a>
				</div>
				<div class="right">
					<form id="searchForm" name="searchForm">
						<select id="searchType" name="searchType">
							<option value="1">제목+내용</option>
							<option value="2">제목</option>
							<option value="3">내용</option>
							<option value="4">작성자</option>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword" />
						<input type="button" id="searchBtn" value="검색" />
					</form>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>