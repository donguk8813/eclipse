package com.gachidata.dong;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gachidata.dong.board.mapper.BoardMapper;

@Controller
public class JspTest {

	@Resource(name="com.gachidata.dong.board.mapper.BoardMapper")
	BoardMapper mBoardMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(JspTest.class);

	
	@GetMapping("/test")
	private String jspTest() throws Exception{
		logger.info("{}",mBoardMapper.boardCount());
		
		
		return "test";
	}
}
