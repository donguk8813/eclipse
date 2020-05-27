package com.gachidata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gachidata.common.ConnectionManager;
import com.gachidata.common.JdbcTemplete;
import com.gachidata.common.ResultSetMapping;
import com.gachidata.vo.FoodVO;



public class FoodDAO {
	private static final Logger logger = LoggerFactory.getLogger(FoodDAO.class);	//window - preference - java - editor - templete 


	public void addFood(FoodVO food) throws SQLException {
		String sql = "insert into t_food values(?,?)";
		JdbcTemplete templete = new JdbcTemplete();
		templete.executeUpdate(sql,food.getName(),food.getColor());
	}
	
	/* ParameterSetting interface 버전
	public void addFood(FoodVO food) throws SQLException {
		String sql = "insert into t_food values(?,?)";
		JdbcTemplete templete = new JdbcTemplete();
		ParameterSetting ps = new ParameterSetting() {

			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, food.getName());
				pstmt.setString(2, food.getColor());
				
			}
			
		};	
		templete.executeUpdate(sql,ps);
	}
	*/
	
	public void updateFood(FoodVO food) throws SQLException {
		String sql = "update t_food set color = ? where name= ?";	
		JdbcTemplete templete = new JdbcTemplete();
		templete.executeUpdate(sql,food.getColor(), food.getName());
	}
	
	
	public void deleteFood(String name) throws SQLException {
		String sql = "delete t_food where name = ?";	
		JdbcTemplete templete = new JdbcTemplete();
		templete.executeUpdate(sql,name);
	}

	
	public FoodVO selectOne(String name) throws SQLException {
		String sql = "select * from t_food where name = ?";
		ResultSetMapping<FoodVO> rm = new ResultSetMapping<FoodVO>() {
			@Override
			public FoodVO mapResultSet(ResultSet res) throws SQLException {
				return new FoodVO(res.getString(1), res.getString(2));
			}
			
		};
		
		JdbcTemplete templete = new JdbcTemplete();
		return templete.executeQuery(sql, rm, name );
	}

	public Map<String, FoodVO> selectAll(){
		Map<String, FoodVO> foods = new HashMap<String,FoodVO>();
		String sql = "select * from t_food";
		ResultSet res = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) { 
				FoodVO temp_food = new FoodVO(res.getString(1), res.getString(2));
				foods.put(res.getString(1), temp_food); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("FoodDAO selectAll method 오류");
			logger.debug("FoodDAO selectAll method 오류");
		}
		return foods;
	}

	public List<FoodVO> selectFoodList() throws SQLException {
		String sql = "select * from t_food ";
		ResultSetMapping<FoodVO> rm = new ResultSetMapping<FoodVO>() {
			@Override
			public FoodVO mapResultSet(ResultSet res) throws SQLException {
				
				return new FoodVO(res.getString(1), res.getString(2));
			}
			
		};
		
		JdbcTemplete templete = new JdbcTemplete();
		return templete.executeQueryList(sql, rm);
	}
	
	

	


}
