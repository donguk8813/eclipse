package com.gachidata.dong.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gachidata.dong.board.domain.CommentVO;
import com.gachidata.dong.board.mapper.CommentMapper;

@Service("com.gachidata.dong.board.service.CommentService")
public class CommentService {
	
	@Resource(name="com.gachidata.dong.board.mapper.CommentMapper")
	private CommentMapper commentMapper;
	
	public List<CommentVO> commentList(int bno) throws Exception{
		return commentMapper.commentList(bno);
	}
	
	public int commentInsert(CommentVO comment) throws Exception{
		return commentMapper.commentInsert(comment);
	}
	
	public int commentUpdate(CommentVO comment) throws Exception{
		return commentMapper.commentUpdate(comment);
	}
	
	public int commentDelete(int cno) throws Exception{
		return commentMapper.commentDelete(cno);
	}
	
}
