package com.ktds.board.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoSupport {
	
	public int insert(Query query) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = query.query(conn);
			int insertCount = pstmt.executeUpdate();
			return insertCount;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(null,pstmt,conn);
		}
	}
	
	public Object selectOne(QueryAndResult queryAndResult) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = queryAndResult.query(conn);
			rs = pstmt.executeQuery();
			return queryAndResult.makeObject(rs);
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(rs,pstmt,conn);
		}
	}
	
	public List selectList(QueryAndResult queryAndResult) {
		oracleDriverClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = queryAndResult.query(conn);
			rs = pstmt.executeQuery();
			return (List)queryAndResult.makeObject(rs);
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			close(rs,pstmt,conn);
		}
	}
	
	
	
	
	private void oracleDriverClassLoad() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ARTICLES","ARTICLES");
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if ( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if ( pstmt != null ) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
}
