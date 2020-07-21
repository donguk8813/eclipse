package com.gachidata.dong.board.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gachidata.dong.board.domain.CommentVO;
import com.gachidata.dong.board.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	
	@Resource(name="com.gachidata.dong.board.service.CommentService")
	private CommentService commentService;
	
	@GetMapping("/list/{bno}")
	@ResponseBody
	public List<CommentVO> commentList(@PathVariable int bno) throws Exception{
		logger.info("bno : {} {}",bno, "dong");
		return commentService.commentList(bno);
	}
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public int commentInsert(@RequestParam int bno, @RequestParam String content, @RequestParam String writer ) throws Exception{
		CommentVO comment = new CommentVO();
		comment.setBno(bno);
		comment.setContent(content);
		comment.setWriter(writer);
		
		logger.info("bno {} content {} writer {}",bno, content, writer);
		
		return commentService.commentInsert(comment);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int commentUpdate(@RequestParam int cno, @RequestParam String content ) throws Exception{
		CommentVO comment = new CommentVO();
		comment.setCno(cno);
		comment.setContent(content);
		
		return commentService.commentUpdate(comment);
	}
	
	@RequestMapping("/delete/{cno}")
	@ResponseBody
	public int commentDelete(@PathVariable int cno) throws Exception{
//		logger.info("cno : {}",cno);
		
		return commentService.commentDelete(cno);
	}
	
	
	
	
	
}
