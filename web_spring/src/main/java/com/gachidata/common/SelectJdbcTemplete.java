package com.gachidata.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gachidata.dao.FoodDAO;
import com.gachidata.vo.FoodVO;

public abstract class SelectJdbcTemplete {
	private static final Logger logger = LoggerFactory.getLogger(SelectJdbcTemplete.class);

	public Object executeQuery(String sql) throws SQLException {
			
		ResultSet res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		conn = ConnectionManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		setParameter(pstmt);
		res = pstmt.executeQuery();
		
		if (!res.next()) {
			return null;	
		}
		return mapResultSet(res);	//리펙토링
		}
		finally {
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(res!=null) res.close();
			//System.out.println("\n close 상태 : "+close);		
		}
	}


	public abstract Object mapResultSet(ResultSet res) throws SQLException;

	public abstract void setParameter( PreparedStatement pstmt) throws SQLException;

}
