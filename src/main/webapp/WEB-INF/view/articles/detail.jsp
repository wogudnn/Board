<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Board/css/layout.css" />
<script type="text/javascript" src="/Board/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			if(confirm("\"${articlesVO.articleSubject}.\"를 삭제하시겠습니까?")) {

				location.href="/Board/board/doDelete?articlesId=${articlesVO.articleId}"
			}
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="article">
			<div id="articleHeader">
				<p>${articlesVO.articleSubject}</p>
				<div id="articleInfo">
					<span>${articlesVO.userVO.userNickname }</span>
					<span>${articlesVO.createdDate}</span>
					<span><img src="/Board/img/eye-icon.png" />${articlesVO.hitCount}</span>
					<span><img src="/Board/img/heart-24-128.png" />${articlesVO.recommendCount}</span>
				</div>
				<div id="attachedFile">
					<span><img src="/Board/img/text-file-3-xxl.png" /><a href="">첨부파일.exe</a></span>
				</div>
			</div>
			<hr/>
			<div id="articleBody">
				${articlesVO.articleContent}
			</div>
		</div>
		<div id="articleFooter">
			<a href="javascript:void(0);" id="deleteBtn">삭제</a>
			<a href="/Board/board/modify">수정</a>
			<a href="/Board/board/list">목록보기</a>
		</div>
	</div>
</body>
</html>