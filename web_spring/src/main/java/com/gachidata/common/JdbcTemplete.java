package com.gachidata.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcTemplete {
	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplete.class);

	public void executeUpdate(String sql, Object... parameter) throws SQLException {
		
		ParameterSetting ps = new ParameterSetting() {

			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				for(int i=0;i<parameter.length;i++) {
					pstmt.setString(i+1, (String)parameter[i]);
				}
			}
		};
		
		executeUpdate(sql,ps);
		/*
		 * 
		 * Connection conn = null; PreparedStatement pstmt = null; try { conn =
		 * ConnectionManager.getConnection(); pstmt = conn.prepareStatement(sql);
		 * for(int i=0;i<parameter.length;i++) { pstmt.setString(i+1,
		 * (String)parameter[i]); }
		 * 
		 * pstmt.executeUpdate();
		 * 
		 * } finally { if (conn != null) conn.close(); if (pstmt != null) pstmt.close();
		 * }
		 */
	}
	
	public void executeUpdate(String sql, ParameterSetting ps) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			ps.setParameter(pstmt);
			pstmt.executeUpdate();

		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
	}


	public <T>T executeQuery(String sql, ResultSetMapping<T> rm, Object...parameter ) throws SQLException {
		
		ParameterSetting ps = new ParameterSetting() {

			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				for(int i=0;i<parameter.length;i++) {
					pstmt.setString(i+1, (String)parameter[i]);
				}
				
			}
			
		};
		
		return executeQuery(sql,rm,ps);
		
	}
	
	public <T>T executeQuery(String sql, ResultSetMapping<T> rm, ParameterSetting ps) throws SQLException {
		List<T> list = executeQueryList(sql,rm,ps);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
		
	}
	
	public <T>List<T> executeQueryList(String sql, ResultSetMapping<T> rm, Object...parameter) throws SQLException {
		ParameterSetting ps = new ParameterSetting() {
		
			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				for(int i=0;i<parameter.length;i++) {
					pstmt.setString(i+1, (String)parameter[i]);
				}
			}
		};
		
		return executeQueryList(sql, rm, ps);
		
	}
	

	public <T>List<T> executeQueryList(String sql, ResultSetMapping<T> rm, ParameterSetting ps) throws SQLException {
		List<T> foods = new ArrayList<T>();
		
		ResultSet res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			ps.setParameter(pstmt);
			res = pstmt.executeQuery();
			while(res.next()) {
				foods.add(rm.mapResultSet(res));
				logger.debug("food",rm.mapResultSet(res));
			}
		
			return foods; // 리펙토링
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (res != null)
				res.close();
			// System.out.println("\n close 상태 : "+close);
		}
	}

	
}
