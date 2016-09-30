package com.ktds.board.articles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.board.articles.vo.ArticlesVO;
import com.ktds.board.support.DaoSupport;
import com.ktds.board.support.Query;
import com.ktds.board.support.QueryAndResult;
import com.ktds.board.user.vo.UserVO;

public class ArticlesDaoImpl extends DaoSupport implements ArticlesDao {

	@Override
	public int addNewArticles(ArticlesVO articlesVO) {
		
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO ARTICLES ( ");
				query.append(" 					ATCL_ID ");
				query.append("					,ATCL_SUBJECT ");
				query.append("					,ATCL_CONTENT ");
				query.append(" 					,CRT_DT ");
				query.append(" 					,HIT_CNT ");
				query.append(" 					,RCMD_CNT ");
				query.append(" 					,USR_ID ");
				query.append(" 					,FILE_NM ) ");
				query.append(" VALUES			( ");
				query.append(" 					'AR-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-' || LPAD(ARTICLES_ID_SEQ.NEXTVAL,6,0) ");
				query.append(" 					,? ,?, SYSDATE, 0, 0, ?, ? ) ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, articlesVO.getArticleSubject());
				pstmt.setString(2, articlesVO.getArticleContent());
				pstmt.setString(3, articlesVO.getUserId());
				pstmt.setString(4, articlesVO.getFileName());
				
				return pstmt;
			}
		});
	}
	
	@Override
	public List<ArticlesVO> getAllArticles() {
		return selectList( new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	A.ATCL_ID ");
				query.append("			,A.ATCL_SUBJECT ");
				query.append("			,A.ATCL_CONTENT ");
				query.append("			,TO_CHAR(A.CRT_DT, 'YYYY-MM-DD') CRT_DT ");
				query.append("			,A.HIT_CNT ");
				query.append("			,A.RCMD_CNT ");
				query.append("			,A.USR_ID ");
				query.append("			,A.FILE_NM ");
				query.append("			,U.USR_NICK_NM ");
				query.append(" FROM		ARTICLES A ");
				query.append(" 			, USR U ");
				query.append(" WHERE	U.USR_ID = A.USR_ID ");
				
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				
				List<ArticlesVO> articles = new ArrayList<ArticlesVO>();
				ArticlesVO articlesVO = null;
				UserVO userVO = null;
				while(rs.next()){
					articlesVO = new ArticlesVO();
					articlesVO.setArticleId(rs.getString("ATCL_ID"));
					articlesVO.setArticleSubject(rs.getString("ATCL_SUBJECT"));
					articlesVO.setArticleContent(rs.getString("ATCL_CONTENT"));
					articlesVO.setCreatedDate(rs.getString("CRT_DT"));
					articlesVO.setHitCount(rs.getInt("HIT_CNT"));
					articlesVO.setRecommendCount(rs.getInt("RCMD_CNT"));
					articlesVO.setUserId(rs.getString("USR_ID"));
					articlesVO.setFileName(rs.getString("FILE_NM"));
					userVO = articlesVO.getUserVO();
					userVO.setUserNickname(rs.getString("USR_NICK_NM"));
					
					articles.add(articlesVO);
				}
				
				return articles;
			}
		});
	}
	@Override
	public ArticlesVO getArticleBy(String articleId) {
		return (ArticlesVO) selectOne(new QueryAndResult() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	A.ATCL_ID ");
				query.append("			,A.ATCL_SUBJECT ");
				query.append("			,A.ATCL_CONTENT ");
				query.append("			,TO_CHAR(A.CRT_DT, 'YYYY-DD-MM') CRT_DT ");
				query.append("			,A.HIT_CNT ");
				query.append("			,A.RCMD_CNT ");
				query.append("			,A.USR_ID ");
				query.append("			,A.FILE_NM ");
				query.append("			,U.USR_NICK_NM ");
				query.append(" FROM		ARTICLES A ");
				query.append(" 			, USR U ");
				query.append(" WHERE	U.USR_ID = A.USR_ID ");
				query.append(" AND		A.ATCL_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, articleId);
				return pstmt;
			}
			
			@Override
			public Object makeObject(ResultSet rs) throws SQLException {
				ArticlesVO articlesVO = null;
				UserVO userVO = null;
				if(rs.next()){
					articlesVO = new ArticlesVO();
					articlesVO.setArticleId(rs.getString("ATCL_ID"));
					articlesVO.setArticleSubject(rs.getString("ATCL_SUBJECT"));
					articlesVO.setArticleContent(rs.getString("ATCL_CONTENT"));
					articlesVO.setCreatedDate(rs.getString("CRT_DT"));
					articlesVO.setHitCount(rs.getInt("HIT_CNT"));
					articlesVO.setRecommendCount(rs.getInt("RCMD_CNT"));
					articlesVO.setUserId(rs.getString("USR_ID"));
					articlesVO.setFileName(rs.getString("FILE_NM"));
					userVO = articlesVO.getUserVO();
					userVO.setUserNickname(rs.getString("USR_NICK_NM"));
				}
				return articlesVO;
			}
		});
	}
	@Override
	public int deleteArticle(String articlesId) {
		
		return insert(new Query() {
			
			@Override
			public PreparedStatement query(Connection conn) throws SQLException {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE ");
				query.append(" FROM		ARTICLES ");
				query.append(" WHERE	ATCL_ID = ? ");
				
				PreparedStatement pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, articlesId);
				
				return pstmt;
			}
		});
	}

}
