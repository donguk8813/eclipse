package com.gachidata.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gachidata.vo.FoodVO;

class FoodDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(FoodDAOTest.class);
	
	
	private static FoodVO TEST_FOOD = new FoodVO("김치","빨강");
	private FoodDAO foodDAO;
	
	
	@BeforeEach
	void init() {
		foodDAO = new FoodDAO(); 
	}
	
	@Test
	void 푸드테스트() throws SQLException {
		FoodVO food = FoodDAOTest.TEST_FOOD;
		FoodVO dbFood; 
		
		foodDAO.deleteFood(food.getName());
		
		foodDAO.addFood(food);
		
		dbFood = foodDAO.selectOne(food.getName());
		
		assertEquals(food, dbFood);
		
		FoodVO updateFood = new FoodVO(food.getName(),"동욱");
		
		foodDAO.updateFood(updateFood);
		
		dbFood = foodDAO.selectOne(updateFood.getName());
		logger.debug("search {}",dbFood);
		
		assertEquals(updateFood, dbFood);
		
	}
	
	@Test
	void 푸드리스트테스트() throws SQLException {
		assertTrue(foodDAO.selectFoodList().size()>0 );
	}
	
	
	
	
}
