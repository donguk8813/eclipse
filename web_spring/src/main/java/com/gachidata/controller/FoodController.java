package com.gachidata.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gachidata.dao.FoodDAO;
import com.gachidata.vo.FoodVO;

@Controller
@RequestMapping("/FoodController")
public class FoodController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FoodDAO foodDAO = null;

	
	@Override
	@RequestMapping(value="/", method=RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
		foodDAO = new FoodDAO();
		//resp.setContentType("text/html;charset=utf-8");
		
		
		try {
			req.setAttribute("foodMap",foodDAO.selectFoodList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("/view/foodlist.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	@RequestMapping(value="/",method=RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html;charset=UTF-8");
		
		foodDAO = new FoodDAO();
		String menu = req.getParameter("hidden");
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		
		
		FoodVO food = new FoodVO(name, color);
		
		switch(menu) {
		case "insert" : 
			try {
				foodDAO.addFood(food);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "delete" : 
			try {
				foodDAO.deleteFood(food.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:;
		}
		doGet(req,resp);
	}	
}
